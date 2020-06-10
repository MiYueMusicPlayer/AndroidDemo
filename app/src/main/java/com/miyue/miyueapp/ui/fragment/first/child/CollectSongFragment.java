package com.miyue.miyueapp.ui.fragment.first.child;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gongw.remote.MessageEvent;
import com.gongw.remote.communication.host.CommandSender;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.miyue.miyueapp.adapter.CollectMusicAdapter;
import com.miyue.miyueapp.base.BaseFragment;
import com.miyue.miyueapp.entity.CommandResult;
import com.miyue.miyueapp.entity.MusicEntity;
import com.miyue.miyueapp.entity.MusicInfo;

import com.miyue.sqj.miyue.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.miyue.miyueapp.util.ToastUtils.showToast;


public class CollectSongFragment extends BaseFragment implements BaseQuickAdapter.OnItemChildClickListener{
    @BindView(R.id.recycler_collectedsongs)
    RecyclerView vRecyclerView;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;
    private CollectMusicAdapter mMusicAdapter;
    public static   List<MusicInfo> musicList = new ArrayList<>();
    private static CollectSongFragment mCollectSongFragment;
    private CommandResult<MusicInfo> mInfoCommandResult;
    private Gson mGson;
    public static CollectSongFragment getInstance() {
        if (mCollectSongFragment == null) {
            mCollectSongFragment = new CollectSongFragment();
        }
        return mCollectSongFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isRegistBrocast(true);
        mGson=new Gson();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collect_songs, null);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }


    @Override
    protected void initData() {
        vRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMusicAdapter = new CollectMusicAdapter(musicList,getActivity(),false);
        mMusicAdapter.setEmptyView(R.layout.fragment_nocollect,vRecyclerView);
        vRecyclerView.setAdapter(mMusicAdapter);
        mMusicAdapter.setOnItemChildClickListener(this);



        swipeRefreshLayout.setColorSchemeColors(Color.GREEN);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initMusic();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 500);

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        initMusic();
    }

    private void initMusic() {
        mInfoCommandResult = new CommandResult<>();
        mInfoCommandResult.action = CommandResult.ACTION_REQUEST_COLLECTEDMUSIC;
        CommandSender.sendMsg(mGson.toJson(mInfoCommandResult));
    }

    @Override
    public void onEvent(MessageEvent event) {
        super.onEvent(event);
        if (TextUtils.equals(event.what, CommandResult.ACTION_REQUEST_COLLECTEDMUSIC)) {
            mInfoCommandResult = mGson.fromJson((String) event.obg, new TypeToken<CommandResult<MusicInfo>>() {
            }.getType());
           List<MusicInfo> musicinfos = mInfoCommandResult.infos;
           musicList= mInfoCommandResult.infos;
            mMusicAdapter.setNewData(musicinfos);
        } else if (TextUtils.equals(event.what, CommandResult.ACTION_UPDATE_SONGINFO)) {
            if (event.obg == null || musicList == null) {
                return;
            }
            for (MusicInfo musicInfo : musicList) {
                if (TextUtils.equals(musicInfo.getFileName(), ((MusicInfo) event.obg).getFileName())) {
                    musicInfo.setPlaying(true);
                } else {
                    musicInfo.setPlaying(false);
                }
            }
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                   mMusicAdapter.notifyDataSetChanged();
                }
            });
        }

    }


    @Override
    public void initListener() {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        playall(mMusicAdapter.getData(),position);
    }
    public  static void playall(final List<MusicInfo>musicInfoList,final int index){
        Gson mGson=new Gson();
        CommandResult<MusicInfo> commandResult = new CommandResult<>();
        commandResult.infos = musicInfoList;
        commandResult.musicIndex = index;
        commandResult.action = CommandResult.ACTION_MUSIC;
        CommandSender.sendMsg(mGson.toJson(commandResult));
        showToast("正在播放"+musicInfoList.get(index).getTitle(),Gravity.CENTER);

    }

}
