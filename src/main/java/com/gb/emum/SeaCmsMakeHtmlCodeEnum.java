package com.gb.emum;

public enum SeaCmsMakeHtmlCodeEnum {
	
	T_0("zxgk","%s在线观看免费版"),  
	T_1("wzbbt","%s完整版BT下载"),  
	T_2("ed2k","%sed2k磁力链接"),  
	T_3("kmvgq","%smkv高清磁力链接"),  
	T_4("bdyhd","%s百度云HD720P资源下载 全集高清rmvb网盘链接"),  
	T_5("mp4cx","%sMP4抢先BT种子"),  
	T_6("1080p","%s1080p种子迅雷资源"),  
	T_7("qjbf","%s全集播放地址"),  
	T_8("xzcl","%s迅雷磁力链接"),  
	T_9("btzz","%sBT种子高清720P资源"),  
	T_10("wzbgq","%s完整版高清下载 全集bt磁力链接中字mp4资源链接"),  
	T_11("gyxl","%s国语迅雷bT种子链接1080P下载 全集大结局bt磁力链接"),  
	T_12("mp4gy","%smp4国语百度云盘链接 %s集百度720p网盘BT资源"),  
	T_13("qjxl","%s全集迅雷高清下载 黄宗泽完整版thunder迅雷720P链接"),  
	T_14("bdybd","%s百度云BD/1080P链接 (大结局)高清网盘mkv资源"),  
	T_15("wzbzz","%s完整版种子bt迅雷下载 全集720p中字免费磁力链接"),  
	T_16("qjmf","%s全集免费观看mp4高清百度云网盘资源下载"),  
	T_17("bdyp","%s【BD1080P高清】百度云盘完整资源下载"),  
	T_18("bdyzy","[%s]百度云盘[1080P][720P]百度云资源下载"),  
	T_19("bdywp","%s百度云网盘迅雷完整下载资源[BD/HD高清/1.33G]"),  
	T_20("720p","%s百度云资源720p|1080p百度云盘"),  
	T_21("xinwen","%s");  

	private String code;

	private String msg;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	private SeaCmsMakeHtmlCodeEnum(String code,String msg){
		this.code=code;
		this.msg=msg;
	}
	
	 public static boolean checkCode(String code) {  
	        for (SeaCmsMakeHtmlCodeEnum c : SeaCmsMakeHtmlCodeEnum.values()) {  
	            if (c.getCode().equals(code)) {  
	                return true;  
	            }  
	        }  
	        return false;  
	 }
	 
}
