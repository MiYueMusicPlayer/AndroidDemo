package com.miyue.miyueapp.entity;

import java.util.List;

public class CommandResult<T> {
    public static final String ACTION_SONG_INFO = "action.request.songinfo";//接收的音乐信息
    public static final String ACTION_MUSIC = "action.request.music";//替换当前播放列表
    public static final String ACTION_SWITCHMUSIC = "action.request.switchMusic";//切换歌曲
    public static final String ACTION_CHANGEVOLUME = "action.request.changeVolume";//调节音量
    public static final String ACTION_REQUESTMUSICLIST = "action.request.musiclist";//请求当前播放列表
    public static final String ACTION_SEEKTOPOSITION = "action.request.seektoposition";//快进快退歌曲进度
    public static final String ACTION_SWITCHPLAYTYPE = "action.request.switchplaytype";//切换播放方式（0列表循环，1单曲循环，2随机播放）
    public static final String ACTION_PLAYALL = "action.play.songlist";//播放某一歌单中的所有歌曲
    public static final String ACTION_PLAYERPOSITION = "action.response.playerposition";//播放进度
    public static final String ACTION_RESPONSEMUSICLIST = "action.response.currentMusicList";//返回当前播放列表
    public static final String ACTION_PLAYORPAUSE = "action.request.playorpause";

    public static final String ACTION_PLAYLIST_ADD="action.playlist.add"; //添加到播放列表
    public static final String ACTION_REQUEST_BOARDMUSICINFOS = "action.request.boardMusicInfos";//获取单个歌单中的音乐数据

    public static final String ACTION_REQUEST_COLLECTEDSONGLIST = "action.request.collectedSonglist";//返回歌单
    public static final String ACTION_REQUEST_COLLECTEDBOARDS = "action.request.collectedBoards";//返回榜单
    public static final String ACTION_REQUEST_COLLECTEDRADIOS = "action.request.collectedRadios";//返回收藏电台
    public static final String ACTION_REQUEST_COLLECTEDMUSIC = "action.request.collectedMusic";//返回收藏电台
    public static final String ACTION_UPDATE_SONGINFO = "action_update_songinfo";//返回收藏电台
    public static final String TIME_FIX_STOP = "action.timefix.stop";//定时关闭
    public static final String ACTION_CREATE_SONGLIST = "action.create.songlist";//创建歌单
    public static final String ACTION_COLLECT_MUSICS = "action.collect.musics";//收藏单个歌曲或多个歌曲
    public static final String ACTION_DELETE_COLLECTEDMUSIC = "action.delete.collectedmusic";//删除收藏的单个或多个歌曲
    public static final String ACTION_DELETE_SONGLIST = "action.delete.songlist";//删除歌单
    public static final String ACTION_DELETE_MUSICINFO_LIST ="action.request.deletemusic";//删除歌曲列表中的歌

    public static final String ACTION_DELETE_COLLECTEDRADIOS = "action.delete.collectedradios";//删除电台
    public static final String ACTION_DELETE_COLLECTEDBOARDS = "action.delete.collectedboards";//删除榜单

    public static final String ACTION_COLLECT_RADIOS2 = "action.collect.radios2";//收藏电台
    public static final String ACTION_COLLECT_BOARDS = "action.collect.boards";//收藏榜单，目前只支持收藏云音乐热歌榜
    public static final String ACTION_COLLECT_BOARDS2 = "action.collect.boards2";//酷狗收藏榜单
    public static final String ACTION_AUX_GETSTATUS = "action.aux.getstatus";//获取aux状态
    public static final String ACTION_BLUETOOTH_GETSTATUS = "action.bluetooth.getstatus";//获取蓝牙状态
    public static final String ACTION_COAXIAL_GETSTATUS = "action.spdif.getstatus";//获取数字同轴状态

    public static final String ACTION_AUX_SWITCH = "action.aux.switch";//AUX开关操作
    public static final String ACTION_AUX_VOLUME = "action.aux.volume";//AUX音量调节
    public static final String ACTION_BLUETOOTH_SWITCH = "action.bluetooth.switch";//蓝牙开关
    public static final String ACTION_COXAIAL_SWITCH = "action.coaxial.switch";//蓝牙开关
    public static final String ACTION_ROOM_REFRESH = "action.room.refresh";//切换房间更新对应界面信息

    public static final String ACTION_RESPONSE_CLOCKLIST = "action.response.clocklist";//返回闹钟列表
    public static final String ACTION_RESPONSE_UPDATELIST = "action.response.updatelist";//更新闹钟列表
    public static final String ACTION_RESPONSE_DELETECLOCK = "action.response.deleteclock";//删除闹钟
    public static final String ACTION_RESPONSE_CLOCKMUSICS = "action.response.clockmusics";//获取闹钟音乐列表
    public static final String ACTION_RESPONSE_CLOCKUPDATE = "action.response.clockupdate";//获取闹钟开关状态
    public static final String ACTION_RESPONSE_TIMEFIX = "action.timer.pause.status";//获取定时状态
    public static final String ACTION_GETDLNA="action.dlna.get";
    public static final String ACTION_SWITCH_ChildSONG_FRAGMENT = "action.switch.child_fragment.detial";//切换到儿歌多多详情
    public static final String ACTION_RESPONSE_DEVICESINFO = "action.response.devicesinfo";//获取设备信息
    public static final String ACTION_RESPONSE_RENAMEDEVICE = "action.response.renamedevice";//修改设备名
    public static final String ACTION_RESPONSE_GETAUXBLUESTATE = "action.response.getauxbluestate";//通知获取蓝牙aux状态
    public static final String ACTION_QUERY_MODEL = "action.query.modules";
    public static final String ACTION_UDP = "action.option.udp.socket";
    public static final String ACTION_SEARCHDEVICE = "action.search.new";//搜索到新设备;
    public static final String ACTION_SETNET= "action.option.setnet";//组网;
    public static final String ACTION_STOPTALK= "action.request.stoptalking";//关闭对讲

    public Long id;
    public Integer flag;
    public String action;
    public long musicInfoId;
    public String songlistInfoId;
    public int volume;
    public String message;
    public int result = 200;
    public T info;
    public List<T> infos;
    public int deviceType;
    public String ip;

    public Integer position;//歌曲进度
    public Integer duration;//歌曲时长
    public Integer musicIndex;//当前播放的歌曲在列表位置
    public Float volumeValue;//音量值
    public Integer playType;//播放方式
    public String songlistName;//歌单名称
    public boolean openBluetooth;//蓝牙开关状态
    public Integer bluetoothStatus;//蓝牙状态
    public boolean openSpdif;
    public int songSrc;
    public String icon_url;
    public String songlist_id;
    public boolean openAux;//AUX开关状态
    public boolean isPlaying;//AUX开关状态
    public boolean isFromDlna;
    public List<Integer>positions;
    public String album;
    public String songId;
    public String singer;
    public String pic;
    public String fileUrl;
    public String version;


}
