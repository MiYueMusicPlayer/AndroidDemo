package com.miyue.miyueapp.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2019-10-08.
 */

public interface IOnclickItemListener {
    void onClickItem(RecyclerView.Adapter adapter, View itemView, int position);
    void onChildClickItem(View view,int position);
}
