package com.qy.business.bean;



/**
 * 是否绑定了动态口令卡的返回
 */
public class ISBindBean {
	private String s;//是否绑定了账户  1已经绑定  -1未绑定
	private int status;
	private String msg;
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	
}
