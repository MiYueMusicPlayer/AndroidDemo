    package com.gongw.remote.communication.host;

    import android.util.Log;
    import android.widget.Toast;

    import com.gongw.remote.MessageEvent;
    import com.gongw.remote.RemoteConst;
    import com.gongw.remote.communication.CommunicationKey;


    import org.greenrobot.eventbus.EventBus;
    import org.json.JSONException;
    import org.json.JSONObject;

    import java.io.IOException;
    import java.io.InputStream;
    import java.io.OutputStream;
    import java.net.ConnectException;
    import java.net.InetSocketAddress;
    import java.net.NoRouteToHostException;
    import java.net.Socket;
    import java.net.SocketTimeoutException;
    import java.nio.charset.Charset;
    import java.util.concurrent.RejectedExecutionException;
    import java.util.concurrent.RejectedExecutionHandler;
    import java.util.concurrent.SynchronousQueue;
    import java.util.concurrent.ThreadPoolExecutor;
    import java.util.concurrent.TimeUnit;

    /**
     * 用于发送命令
     * Created by gw on 2017/11/4.
     */
    public class CommandSender {
        public CallBack callBack;

        private static Socket socket;

        private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 10, 1, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new SendCommandThreadFactory(), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                throw new RejectedExecutionException();
            }
        });

        public  void setCallBack(CallBack callBack){
            this.callBack=callBack;
        }
        public  void startAndReceive(String ip, String roomName){
            if(socket!=null){
                removeTask();
            }
            addTask(new CommandRunnable(ip,roomName));
        }

        public  static void  sendMsg(final String msg) {
            if (socket != null && socket.isConnected()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            socket.getOutputStream().write(byteMerger(msg.getBytes(),CommunicationKey.EOF.getBytes()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }
        public static   byte[] byteMerger(byte[] byte_1, byte[] byte_2){
            byte[] byte_3 = new byte[byte_1.length+byte_2.length];
            System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
            System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
            return byte_3;
        }

        private  class CommandRunnable implements Runnable {

            String ip;
            String name;

            public CommandRunnable(String ip, String roomName) {
                this.ip = ip;
                this.name = roomName;
            }

            @Override
            public void run() {
                try {
                    if (socket == null) {
                        socket = new Socket();
                        InetSocketAddress address=new InetSocketAddress(ip, RemoteConst.COMMAND_RECEIVE_PORT);
                        socket.connect(address);
                    }
                    //读取应答内容
                    int i = 0;
                    InputStream is = socket.getInputStream();
                    byte[] buffer = new byte[1024 * 800];

                    while (true) {
                        buffer[i] = (byte) is.read();
                        if (buffer[i] == -1) {
                            break;
                        }
                        boolean isFind = true;
                        byte[] separatorBytes = CommunicationKey.EOF.getBytes();
                        if (i + 1 >= separatorBytes.length) {
                            for (int n = 0; n < separatorBytes.length; n++) {
                                if (buffer[i + 1 - separatorBytes.length + n] != separatorBytes[n]) {
                                    isFind = false;
                                    break;
                                }
                            }
                        } else {
                            isFind = false;
                        }
                        if (!isFind) {
                            i++;
                        } else {
                            String response = new String(buffer, 0, i + 1, Charset.defaultCharset()).replace(CommunicationKey.EOF, "");
                            i = 0;
                            try {
                                JSONObject testjson = new JSONObject(response);
                                MessageEvent messageEvent = new MessageEvent();
                                messageEvent.what = testjson.getString("action");
                                messageEvent.obg = response;
                                EventBus.getDefault().post(messageEvent);
//                                if(callBack!=null){
//                                    callBack.getData(messageEvent);
//                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if (e instanceof SocketTimeoutException) {
                        //  toastMsg("连接超时，正在重连");


                    } else if (e instanceof NoRouteToHostException) {
                        MessageEvent messageEvent = new MessageEvent();
                        messageEvent.what = "error";
                        EventBus.getDefault().post(messageEvent);

                    } else if (e instanceof ConnectException) {
                        //  toastMsg("连接异常或被拒绝，请检查");

                    }
                }
            }
        }


        public  void removeTask() {
            try {
                if(socket!=null) {
                    socket.close();
                    socket = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        private  void addTask(CommandRunnable runnable){
            try{
                threadPool.execute(runnable);
            }catch (RejectedExecutionException e){
                e.printStackTrace();
            }
        }
        private void requestData(String action) {
            JSONObject object =new JSONObject();
            try {
                object.put("action", action);
                CommandSender.sendMsg(object.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
