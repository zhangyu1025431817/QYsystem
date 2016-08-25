package com.qy.business.bean;

import java.util.LinkedList;

/**
 * Created by zhangyu on 2016/8/1.
 */
public class Goods {
    private String goods_id;
    private String goods_name;
    private String top_photos;
    private String goods_unit;
    private String goods_price;
    private LinkedList<SkuData> sku_list;

    public LinkedList<SkuData> getSku_list() {
        return sku_list;
    }

    public void setSku_list(LinkedList<SkuData> sku_list) {
        this.sku_list = sku_list;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getTop_photos() {
        return top_photos;
    }

    public void setTop_photos(String top_photos) {
        this.top_photos = top_photos;
    }

    public String getGoods_unit() {
        return goods_unit;
    }

    public void setGoods_unit(String goods_unit) {
        this.goods_unit = goods_unit;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }
}
