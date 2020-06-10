package com.miyue.miyueapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.miyue.miyueapp.entity.SongListInfo;
import com.miyue.sqj.miyue.R;


import java.util.List;

public class CollectSongListAdapter extends BaseQuickAdapter<SongListInfo,BaseViewHolder>{

    private List<SongListInfo> mSongListInfos;

    private int type;
    private Context context;

    public CollectSongListAdapter(@Nullable List<SongListInfo> data, int type, Context context) {
        super(R.layout.item_songlist, data);
        this.type=type;
        this.context=context;
    }
    @Override
    protected void convert(BaseViewHolder helper, SongListInfo item) {
        Glide.with(context).load(item.getIconUrl()).error(R.mipmap.icon_default_song_list).placeholder(R.mipmap.icon_default_song_list).into((ImageView) helper.getView(R.id.songlist_image));
      helper.setText(R.id.songlist_name,item.getSonglistTitle());
        helper.setText(R.id.songlist_number,"共"+item.getSongNum()+"首歌");
        helper.addOnClickListener(R.id.rl_item_layout);
    }

}
