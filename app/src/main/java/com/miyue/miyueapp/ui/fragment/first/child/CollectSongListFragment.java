package com.miyue.miyueapp.ui.fragment.first.child;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gongw.remote.MessageEvent;
import com.gongw.remote.communication.host.CommandSender;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.miyue.miyueapp.adapter.CollectSongListAdapter;
import com.miyue.miyueapp.base.BaseFragment;

import com.miyue.miyueapp.entity.CommandResult;
import com.miyue.miyueapp.entity.MusicInfo;
import com.miyue.miyueapp.entity.SongListInfo;

import com.miyue.miyueapp.ui.activity.MusicListDetailActivity;
import com.miyue.sqj.miyue.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


import static com.miyue.miyueapp.util.ToastUtils.showToast;

public class CollectSongListFragment extends BaseFragment implements BaseQuickAdapter.OnItemChildClickListener{

    @BindView(R.id.recyclerview)
    RecyclerView vRecyclerView;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;
   private CollectSongListAdapter mSonglistAdapter;
    private List<SongListInfo> songListInfos = new ArrayList<>();
    private static CollectSongListFragment mCollectSongListFragment;
    private Gson mGson;
    private CommandResult<SongListInfo> mInfoCommandResult;
    public static CollectSongListFragment getInstance() {
        if (mCollectSongListFragment == null) {
            mCollectSongListFragment = new CollectSongListFragment();
        }
        return mCollectSongListFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isRegistBrocast(true);
        mGson = new Gson();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            getData();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview_with_refresh, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public void getData() {
        mInfoCommandResult = new CommandResult<>();
        mInfoCommandResult.action = CommandResult.ACTION_REQUEST_COLLECTEDSONGLIST;
        CommandSender.sendMsg(mGson.toJson(mInfoCommandResult));
    }
    @Override
    public void initListener() {
        swipeRefreshLayout.setColorSchemeColors(Color.GREEN);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 500);

            }
        });
    }

    @Override
    protected void initData() {

        vRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSonglistAdapter = new CollectSongListAdapter(songListInfos, 1,getActivity());

     //   vRecyclerView.addItemDecoration(new SpaceItemDecoration(DensityUtil.dip2px(getActivity(), 1)));//设置item之间间隔
        vRecyclerView.setAdapter(mSonglistAdapter);
        mSonglistAdapter.setEmptyView(R.layout.fragment_nocollect,vRecyclerView);
        mSonglistAdapter.setOnItemChildClickListener(this);



    }

    @Override
    public void onEvent(MessageEvent event) {
        super.onEvent(event);
        if (TextUtils.equals(event.what, CommandResult.ACTION_REQUEST_COLLECTEDSONGLIST)) {
            mInfoCommandResult = mGson.fromJson((String) event.obg, new TypeToken<CommandResult<SongListInfo>>() {
            }.getType());
            final List<SongListInfo> songLists = mInfoCommandResult.infos;
            mSonglistAdapter.setNewData(songLists);
        }
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
        SongListInfo songListInfo= mSonglistAdapter.getData().get(position);
        Intent intent=new Intent(getActivity(),MusicListDetailActivity.class);
        intent.putExtra("id",songListInfo.getId());
        startActivity(intent);
    }
}
