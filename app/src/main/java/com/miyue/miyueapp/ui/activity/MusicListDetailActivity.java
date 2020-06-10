package com.miyue.miyueapp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gongw.remote.MessageEvent;
import com.gongw.remote.communication.host.CommandSender;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.miyue.miyueapp.adapter.MusicListDetailAdapter;
import com.miyue.miyueapp.entity.CommandResult;
import com.miyue.miyueapp.entity.MusicInfo;
import com.miyue.sqj.miyue.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

import static com.miyue.miyueapp.util.ToastUtils.showToast;

public class MusicListDetailActivity extends SupportActivity implements BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    private MusicListDetailAdapter mMusicAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_recyclerview);
        ButterKnife.bind(this);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        mMusicAdapter = new MusicListDetailAdapter(new ArrayList<MusicInfo>());
        mMusicAdapter.setEmptyView(R.layout.fragment_nocollect, recyclerview);
        recyclerview.setAdapter(mMusicAdapter);
        mMusicAdapter.setOnItemChildClickListener(this);
        long id = getIntent().getLongExtra("id", 0);
        getMusicFromSonglist(id);
        EventBus.getDefault().register(this);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    public static void playall(final List<MusicInfo> musicInfoList, final int index) {
        Gson mGson = new Gson();
        CommandResult<MusicInfo> commandResult = new CommandResult<>();
        commandResult.infos = musicInfoList;
        commandResult.musicIndex = index;
        commandResult.action = CommandResult.ACTION_MUSIC;
        CommandSender.sendMsg(mGson.toJson(commandResult));
        showToast("正在播放" + musicInfoList.get(index).getTitle(), Gravity.CENTER);

    }

    /**
     * 获取指定歌单中的歌曲信息
     */
    private void getMusicFromSonglist(long songListInfoId) {
        CommandResult commandResult = new CommandResult();
        commandResult.action = CommandResult.ACTION_REQUEST_BOARDMUSICINFOS;
        commandResult.id = songListInfoId;
        CommandSender.sendMsg(new Gson().toJson(commandResult));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event) {
        /* Do something */
        if (event.what.equals(CommandResult.ACTION_REQUEST_BOARDMUSICINFOS)) {
            CommandResult<MusicInfo> mInfoCommandResult = new Gson().fromJson((String) event.obg, new TypeToken<CommandResult<MusicInfo>>() {
            }.getType());
            List<MusicInfo> musicInfos = mInfoCommandResult.infos;
            mMusicAdapter.setNewData(musicInfos);
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        playall(mMusicAdapter.getData(), position);
    }
}
