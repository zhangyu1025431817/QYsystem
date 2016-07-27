package com.qy.business.bean;


/**
 * 登录返回数据的bean
 */
public class LoginReturnBean {
	private String status;
	private String msg;
	private LoginReturnDataBean data;
	private GetVersionReturn_DataBean apk;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public LoginReturnDataBean getData() {
		return data;
	}
	public void setData(LoginReturnDataBean data) {
		this.data = data;
	}
	public GetVersionReturn_DataBean getApk() {
		return apk;
	}
	public void setApk(GetVersionReturn_DataBean apk) {
		this.apk = apk;
	}
	
	

}
