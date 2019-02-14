package com.zzw.upc.base.entity;

public class Image {

	private long id;
	private String groupName;// 分组名
	private String remoteFilename;// 文件名
	private String rawFilename;// 原始文件名

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getRemoteFilename() {
		return remoteFilename;
	}

	public void setRemoteFilename(String remoteFilename) {
		this.remoteFilename = remoteFilename;
	}

	public String getRawFilename() {
		return rawFilename;
	}

	public void setRawFilename(String rawFilename) {
		this.rawFilename = rawFilename;
	}

}
