package com.qy.business.bean;

import java.util.LinkedList;

public class GetAdresslist_DataBean {
	private LinkedList<GetAdresslist_dbBean> list;
	private LinkedList<GetAdresslist_delBean> del;
	public LinkedList<GetAdresslist_dbBean> getList() {
		return list;
	}
	public void setList(LinkedList<GetAdresslist_dbBean> list) {
		this.list = list;
	}
	public LinkedList<GetAdresslist_delBean> getDel() {
		return del;
	}
	public void setDel(LinkedList<GetAdresslist_delBean> del) {
		this.del = del;
	}
	
}
