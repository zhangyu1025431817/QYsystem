package com.qy.business.main.login.bean;

import java.util.LinkedList;


public class RegionListBean {

	int status;
	String msg;
	LinkedList<Region_all> data = new LinkedList<Region_all>();
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
	public LinkedList<Region_all> getData() {
		return data;
	}
	public void setData(LinkedList<Region_all> data) {
		this.data = data;
	}
	
}
