package com.miyue.miyueapp.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.miyue.miyueapp.entity.MusicInfo;
import com.miyue.sqj.miyue.R;

import java.util.List;

public class MusicListDetailAdapter extends BaseQuickAdapter<MusicInfo,BaseViewHolder> {

    public MusicListDetailAdapter( @Nullable List<MusicInfo> data) {
        super(R.layout.item_music, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MusicInfo item) {
        Glide.with(mContext).load(item.getPic()).placeholder(R.mipmap.music_default).error(R.mipmap.music_default).into((ImageView) helper.getView(R.id.iv_image));
        helper.setTextColor(R.id.music_name,item.isPlaying()?mContext.getResources().getColor(R.color.color_music_play): mContext.getResources().getColor(R.color.white));
        helper.setTextColor(R.id.music_artest,item.isPlaying()?mContext.getResources().getColor(R.color.color_music_play): mContext.getResources().getColor(R.color.textColor02));
        helper.setText(R.id.music_artest,item.getSinger());
        helper.setText(R.id.music_name,item.getTitle());
        helper.addOnClickListener(R.id.rl);
    }
}
