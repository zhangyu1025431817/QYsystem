package com.qy.business.bean;

import java.io.Serializable;

public class Shop implements Serializable{
	private String user_id;//
	private String user_name;
	private String limit_areas;
	private String shop_address;
	private String shoplogo;
	private String shop_name;
	private String b_scope;

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

	public String getLimit_areas() {
		return limit_areas;
	}

	public void setLimit_areas(String limit_areas) {
		this.limit_areas = limit_areas;
	}

	public String getShop_address() {
		return shop_address;
	}

	public void setShop_address(String shop_address) {
		this.shop_address = shop_address;
	}

	public String getShoplogo() {
		return shoplogo;
	}

	public void setShoplogo(String shoplogo) {
		this.shoplogo = shoplogo;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public String getB_scope() {
		return b_scope;
	}

	public void setB_scope(String b_scope) {
		this.b_scope = b_scope;
	}
}
