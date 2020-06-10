package com.miyue.miyueapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;


public class SongListInfo implements Parcelable {



    private Long id;

    private String songlistTitle;
    private String iconUrl;
    private long createTime;
    private int dataSrc;
    private String songlistId;//网络歌单id
    private int songlistSrc;//歌单来源
    private String description;//描述
    private String wangyiUpdateTime;//网易排行榜更新时间
    private boolean isCollected;//针对网络歌单缓存，如为true则显示在榜单中
    private long collectTime;//针对网络歌单缓存，收藏的时间
    private int songNum;
    private List<MusicInfo> musicInfos;
    private int song_tag_id;
    private int special_tag_id;
    private int album_tag_id;
    private int has_child;

    public int getSong_tag_id() {
        return song_tag_id;
    }

    public void setSong_tag_id(int song_tag_id) {
        this.song_tag_id = song_tag_id;
    }

    public int getSpecial_tag_id() {
        return special_tag_id;
    }

    public void setSpecial_tag_id(int special_tag_id) {
        this.special_tag_id = special_tag_id;
    }

    public int getAlbum_tag_id() {
        return album_tag_id;
    }

    public void setAlbum_tag_id(int album_tag_id) {
        this.album_tag_id = album_tag_id;
    }

    public int getHas_child() {
        return has_child;
    }

    public void setHas_child(int has_child) {
        this.has_child = has_child;
    }

    public SongListInfo(String songlistTitle) {
        this.songlistTitle = songlistTitle;
    }

    public SongListInfo() {
    }

    public SongListInfo( String songlistTitle,Long id, int song_tag_id, int special_tag_id, int album_tag_id,String icon,String name) {
        this.id = id;
        this.songlistTitle = songlistTitle;
        this.song_tag_id = song_tag_id;
        this.special_tag_id = special_tag_id;
        this.album_tag_id = album_tag_id;
        iconUrl=icon;
        description=name;
    }

    public SongListInfo(String songlistTitle, String iconUrl, String songlistId) {
        this.songlistTitle = songlistTitle;
        this.iconUrl = iconUrl;
        this.songlistId = songlistId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSonglistTitle() {
        return this.songlistTitle;
    }

    public void setSonglistTitle(String songlistTitle) {
        this.songlistTitle = songlistTitle;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getSongNum() {
        return songNum;
    }

    public void setSongNum(int songNum) {
        this.songNum = songNum;
    }

    public String getSonglistId() {
        return songlistId;
    }

    public void setSonglistId(String songlistId) {
        this.songlistId = songlistId;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int getDataSrc() {
        return dataSrc;
    }

    public void setDataSrc(int dataSrc) {
        this.dataSrc = dataSrc;
    }

    public int getSonglistSrc() {
        return songlistSrc;
    }

    public void setSonglistSrc(int songlistSrc) {
        this.songlistSrc = songlistSrc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWangyiUpdateTime() {
        return wangyiUpdateTime;
    }

    public void setWangyiUpdateTime(String wangyiUpdateTime) {
        this.wangyiUpdateTime = wangyiUpdateTime;
    }

    public List<MusicInfo> getMusicInfos(){
     //   setMusicInfos(CollectedMusicDaoManager.getInstance().getMusicBySonglistId(id));
        return musicInfos;
    }

    public void setMusicInfos(List<MusicInfo> musicInfos) {
        this.musicInfos = musicInfos;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null){
            return false;
        }
        SongListInfo another = (SongListInfo) o;
        //音乐的Uri相同，则说明两者相同
        return songlistTitle.equals(another.songlistTitle);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(songlistTitle);
        dest.writeLong(createTime);
        dest.writeInt(songNum);
        dest.writeInt(album_tag_id);
        dest.writeInt(song_tag_id);
        dest.writeInt(special_tag_id);
        dest.writeInt(has_child);
    }

    public boolean getIsCollected() {
        return this.isCollected;
    }

    public void setIsCollected(boolean isCollected) {
        this.isCollected = isCollected;
    }

    public long getCollectTime() {
        return this.collectTime;
    }

    public void setCollectTime(long collectTime) {
        this.collectTime = collectTime;
    }

    protected SongListInfo(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        songlistTitle = in.readString();
        createTime = in.readLong();
        songNum = in.readInt();
       album_tag_id=in.readInt();
        song_tag_id=in.readInt();
        special_tag_id=in.readInt();
        has_child=in.readInt();
    }

    public static final Creator<SongListInfo> CREATOR = new Creator<SongListInfo>() {
        @Override
        public SongListInfo createFromParcel(Parcel in) {
            return new SongListInfo(in);
        }

        @Override
        public SongListInfo[] newArray(int size) {
            return new SongListInfo[size];
        }
    };

}
