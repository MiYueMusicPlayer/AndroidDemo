package com.miyue.miyueapp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;


import com.gongw.remote.MessageEvent;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2019-09-28.
 */

public abstract class BaseFragment extends SupportFragment {
    private boolean mIsRegistBrocast;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initListener();


    }
    protected abstract void initData();
    protected abstract void initListener();



    //注册广播
    protected void isRegistBrocast(boolean isRegist) {
        if(isRegist) {
            mIsRegistBrocast = isRegist;
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mIsRegistBrocast) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event) {
    /* Do something */
}
}
