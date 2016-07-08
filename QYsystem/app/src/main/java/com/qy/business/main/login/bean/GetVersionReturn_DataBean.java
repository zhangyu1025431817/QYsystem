package com.qy.business.main.login.bean;

public class GetVersionReturn_DataBean {
	private String s;//1服务端有apk   -1服务端没有apk
	private String apk_id;
	private String apk_version;
	private String uploadtime;
	private String filepath;
	private String remark;
	private String is_checked;
	private String user_id;
	private String user_name;
	
	public String getApk_id() {
		return apk_id;
	}
	public void setApk_id(String apk_id) {
		this.apk_id = apk_id;
	}
	public String getApk_version() {
		return apk_version;
	}
	public void setApk_version(String apk_version) {
		this.apk_version = apk_version;
	}
	public String getUploadtime() {
		return uploadtime;
	}
	public void setUploadtime(String uploadtime) {
		this.uploadtime = uploadtime;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIs_checked() {
		return is_checked;
	}
	public void setIs_checked(String is_checked) {
		this.is_checked = is_checked;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	
	

}
