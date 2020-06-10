package com.miyue.miyueapp.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;


public class MusicInfo implements Parcelable {
    private Long id;
    private String fileName;
    private String title;
    private int duration;
    private String singer;
    private String album;
    private String year;
    private String type;
    private String size;
    private String fileUrl;
    private String pic;
    private int playType;
    private String songId;//歌曲网络ID
    private long songListId;//歌单ID
    private int songSrc;//
    private int isNetUrl;//是否是网络地址 0本地地址  1网络地址
    private long collectTime;//收藏时间戳
    private String composer;//作曲家
    private int dataSrc;//数据来源
    private String checked="0";
    private boolean isPlaying;
    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }



    protected MusicInfo(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        fileName = in.readString();
        title = in.readString();
        duration = in.readInt();
        singer = in.readString();
        album = in.readString();
        year = in.readString();
        type = in.readString();
        size = in.readString();
        fileUrl = in.readString();
        pic = in.readString();
        songId = in.readString();
        songListId = in.readLong();
        songSrc = in.readInt();
        isNetUrl = in.readInt();
        collectTime = in.readLong();
        composer = in.readString();
        checked=in.readString();
        playType=in.readInt();
    }

    public static final Creator<MusicInfo> CREATOR = new Creator<MusicInfo>() {
        @Override
        public MusicInfo createFromParcel(Parcel in) {
            return new MusicInfo(in);
        }

        @Override
        public MusicInfo[] newArray(int size) {
            return new MusicInfo[size];
        }
    };

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
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
        dest.writeString(fileName);
        dest.writeString(title);
        dest.writeInt(duration);
        dest.writeString(singer);
        dest.writeString(album);
        dest.writeString(year);
        dest.writeString(type);
        dest.writeString(size);
        dest.writeString(fileUrl);
        dest.writeString(pic);
        dest.writeString(songId);
        dest.writeLong(songListId);
        dest.writeInt(songSrc);
        dest.writeInt(isNetUrl);
        dest.writeLong(collectTime);
        dest.writeString(composer);
        dest.writeString(checked);
        dest.writeInt(playType);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTitle() {
//        if(TextUtils.isEmpty(title)){
            return title;
//        }
//        return title;
    }

    public int getPlaytype() {
        return playType;
    }

    public void setPlaytype(int playtype) {
        this.playType = playtype;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFileUrl() {
        if(TextUtils.isEmpty(fileUrl)){
            return "";
        }
        return fileUrl;
    }

    public MusicInfo(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public long getSongListId() {
        return songListId;
    }

    public void setSongListId(long songListId) {
        this.songListId = songListId;
    }

    public int getSongSrc() {
        return songSrc;
    }

    public void setSongSrc(int songSrc) {
        this.songSrc = songSrc;
    }

    public int getIsNetUrl() {
        return isNetUrl;
    }

    /**
     * 是否是网络地址 0本地地址  1网络地址
     * @param isNetUrl
     */
    public void setIsNetUrl(int isNetUrl) {
        this.isNetUrl = isNetUrl;
    }

    public long getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(long collectTime) {
        this.collectTime = collectTime;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public MusicInfo(String fileName, String title, int duration, String singer, String album, String year, String type, String size, String fileUrl, String pic, boolean isPlaying) {
        this.fileName = fileName;
        this.title = title;
        this.duration = duration;
        this.singer = singer;
        this.album = album;
        this.year = year;
        this.type = type;
        this.size = size;
        this.fileUrl = fileUrl;
        this.pic = pic;
    }

    public MusicInfo(String title, String singer) {
        super();
        this.fileName = null;
        this.title = title;
        this.duration = 0;
        this.singer = singer;
        this.album = null;
        this.year = null;
        this.type = null;
        this.size = null;
        this.fileUrl = null;
        this.pic = null;
    }

    public MusicInfo() {}

    @Override
    public String toString() {
        return "Song [fileName=" + fileName + ", title=" + title
                + ", duration=" + duration + ", singer=" + singer + ", album="
                + album + ", year=" + year + ", type=" + type + ", size="
                + size + ", fileUrl=" + fileUrl + "]";
    }




    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 集合数据去重
     * @param musicInfos
     */
    public static void deduplicate(List<MusicInfo> musicInfos){
        List<MusicInfo> tmpMusicinfos = new ArrayList<>();
        for (MusicInfo musicInfo : musicInfos){
            if(!tmpMusicinfos.contains(musicInfo)){
                tmpMusicinfos.add(musicInfo);
            }
        }
        musicInfos.clear();
        musicInfos.addAll(tmpMusicinfos);
    }

    public int getDataSrc() {
        return this.dataSrc;
    }

    public void setDataSrc(int dataSrc) {
        this.dataSrc = dataSrc;
    }

    public boolean getIsPlaying() {
        return this.isPlaying;
    }

    public void setIsPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    public int getPlayType() {
        return this.playType;
    }

    public void setPlayType(int playType) {
        this.playType = playType;
    }

}
