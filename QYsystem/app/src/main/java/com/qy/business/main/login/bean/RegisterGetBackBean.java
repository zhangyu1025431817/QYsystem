package com.qy.business.main.login.bean;

import java.util.LinkedList;



public class RegisterGetBackBean {
	String status;
	String msg;
	//LocationInfo data = new LocationInfo();
	LinkedList<LocationInfo> data1 = new LinkedList<LocationInfo>();
//	public LocationInfo getData() {
//		return data;
//	}
//	public void setData(LocationInfo data) {
//		this.data = data;
//	}
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
	public LinkedList<LocationInfo> getData1() {
		return data1;
	}
	public void setData1(LinkedList<LocationInfo> data1) {
		this.data1 = data1;
	}
	
}
class LocationInfo{
	String inviter;
	String province;
	String city;
	String district;
	public String getInviter() {
		return inviter;
	}
	public void setInviter(String inviter) {
		this.inviter = inviter;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	
}