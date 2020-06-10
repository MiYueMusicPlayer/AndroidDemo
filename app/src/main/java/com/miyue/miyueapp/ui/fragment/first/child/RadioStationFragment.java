package com.miyue.miyueapp.ui.fragment.first.child;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gongw.remote.MessageEvent;
import com.gongw.remote.communication.host.CommandSender;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.miyue.miyueapp.adapter.CollectMusicAdapter;
import com.miyue.miyueapp.base.BaseFragment;
import com.miyue.miyueapp.entity.CommandResult;
import com.miyue.miyueapp.entity.MusicInfo;

import com.miyue.sqj.miyue.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.miyue.miyueapp.util.ToastUtils.showToast;

public class RadioStationFragment extends BaseFragment implements BaseQuickAdapter.OnItemChildClickListener{
    @BindView(R.id.recyclerview)
    RecyclerView vRecyclerView;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;

    private CollectMusicAdapter mMusicAdapter;

    public List<MusicInfo> musicList = new ArrayList<>();

    private static RadioStationFragment mCollectSongFragment;
    private CommandResult<MusicInfo> mInfoCommandResult;
    private Gson mGson;

    public static RadioStationFragment getInstance() {
        if (mCollectSongFragment == null) {
            mCollectSongFragment = new RadioStationFragment();
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
        View view = inflater.inflate(R.layout.fragment_recyclerview_with_refresh, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            mGson=new Gson();
            initMusic();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    //    initMusic();
    }

    @Override
    protected void initData() {
        vRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMusicAdapter = new CollectMusicAdapter(musicList,getContext(),false);
        vRecyclerView.setAdapter(mMusicAdapter);
        mMusicAdapter.setEmptyView(R.layout.fragment_nocollect,vRecyclerView);
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
        mMusicAdapter.setOnItemChildClickListener(this);
    }

    @Override
    protected void initListener() {
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

    private void initMusic() {
        mInfoCommandResult = new CommandResult<>();
        mInfoCommandResult.action = CommandResult.ACTION_REQUEST_COLLECTEDRADIOS;
        CommandSender.sendMsg(mGson.toJson(mInfoCommandResult));
    }

    @Override
    public void onEvent(MessageEvent event) {
        super.onEvent(event);
        if (TextUtils.equals(event.what, CommandResult.ACTION_REQUEST_COLLECTEDRADIOS)) {
            mInfoCommandResult = mGson.fromJson((String) event.obg, new TypeToken<CommandResult<MusicInfo>>() {
            }.getType());
            musicList.clear();
            List<MusicInfo> musicinfos = mInfoCommandResult.infos;
            if (musicinfos.size() > 0) {
              mMusicAdapter.setNewData(musicinfos);
            }
        }
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
}
