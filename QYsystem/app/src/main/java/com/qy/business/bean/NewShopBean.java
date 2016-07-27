package com.qy.business.bean;

import java.util.LinkedList;

public class NewShopBean {

	private int status;
	private String msg;
	private LinkedList<NewShopInfo> data;
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
	public LinkedList<NewShopInfo> getData() {
		return data;
	}
	public void setData(LinkedList<NewShopInfo> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "NewShopBean{" +
				"status=" + status +
				", msg='" + msg + '\'' +
				", data=" + data +
				'}';
	}
}
