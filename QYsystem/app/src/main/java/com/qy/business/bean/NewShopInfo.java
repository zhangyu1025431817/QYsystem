package com.qy.business.bean;

public class NewShopInfo {

	private String user_id;
	private String user_name;
	private String contact;
	private String shop_name;
	private String shoplogo;
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
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getShoplogo() {
		return shoplogo;
	}
	public void setShoplogo(String shoplogo) {
		this.shoplogo = shoplogo;
	}

	@Override
	public String toString() {
		return "NewShopInfo{" +
				"user_id='" + user_id + '\'' +
				", user_name='" + user_name + '\'' +
				", contact='" + contact + '\'' +
				", shop_name='" + shop_name + '\'' +
				", shoplogo='" + shoplogo + '\'' +
				'}';
	}
}
