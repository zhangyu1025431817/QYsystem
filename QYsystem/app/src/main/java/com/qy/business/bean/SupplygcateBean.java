package com.qy.business.bean;

import java.util.LinkedList;

public class SupplygcateBean {

	private String supplygcate_id;
	private String supplygcate_name;
	private String goods_cate_ids;
	private String orderby;
	private String site_mark;
	private String picbiao;
	private String pictu;
	private LinkedList<CatesBean> cates;

	public String getSupplygcate_id() {
		return supplygcate_id;
	}

	public void setSupplygcate_id(String supplygcate_id) {
		this.supplygcate_id = supplygcate_id;
	}

	public String getSupplygcate_name() {
		return supplygcate_name;
	}

	public void setSupplygcate_name(String supplygcate_name) {
		this.supplygcate_name = supplygcate_name;
	}

	public String getGoods_cate_ids() {
		return goods_cate_ids;
	}

	public void setGoods_cate_ids(String goods_cate_ids) {
		this.goods_cate_ids = goods_cate_ids;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public String getSite_mark() {
		return site_mark;
	}

	public void setSite_mark(String site_mark) {
		this.site_mark = site_mark;
	}

	public LinkedList<CatesBean> getCates() {
		return cates;
	}

	public void setCates(LinkedList<CatesBean> cates) {
		this.cates = cates;
	}

	public String getPicbiao() {
		return picbiao;
	}

	public void setPicbiao(String picbiao) {
		this.picbiao = picbiao;
	}

	public String getPictu() {
		return pictu;
	}

	public void setPictu(String pictu) {
		this.pictu = pictu;
	}
	
	

}
