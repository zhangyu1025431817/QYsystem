package com.qy.business.bean;

import java.io.Serializable;

public class SkuData implements Serializable{

	private String goodssku_id;
	private String goods_id;
	private String attr_sku;
	private String attr_skuname;
	private String cost_price;
	private String price;
	private String stock;
	private String attr_code;
	private String is_del;
	private String pro_time;
	public String getGoodssku_id() {
		return goodssku_id;
	}
	public void setGoodssku_id(String goodssku_id) {
		this.goodssku_id = goodssku_id;
	}
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getAttr_sku() {
		return attr_sku;
	}
	public void setAttr_sku(String attr_sku) {
		this.attr_sku = attr_sku;
	}
	public String getAttr_skuname() {
		return attr_skuname;
	}
	public void setAttr_skuname(String attr_skuname) {
		this.attr_skuname = attr_skuname;
	}
	public String getCost_price() {
		return cost_price;
	}
	public void setCost_price(String cost_price) {
		this.cost_price = cost_price;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public String getAttr_code() {
		return attr_code;
	}
	public void setAttr_code(String attr_code) {
		this.attr_code = attr_code;
	}
	public String getIs_del() {
		return is_del;
	}
	public void setIs_del(String is_del) {
		this.is_del = is_del;
	}
	public String getPro_time() {
		return pro_time;
	}
	public void setPro_time(String pro_time) {
		this.pro_time = pro_time;
	}
	
	
}
