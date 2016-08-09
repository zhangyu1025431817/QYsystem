package com.qy.business.bean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhangyu on 2016/8/8.
 */
public class GoodsDetail extends Goods {
    /** 图片 */
    private String busi_id;
    private List<String> photos = new ArrayList<String>();
    private LinkedList<DeliveryMode> freight;// 商家配送方式
    private LinkedList<SaleAttr> saleattr;
    private List<String> buyattr = new ArrayList<String>();
    private int peisong;// 实物商城配送方式
    private String phone;
    private String shop_name;
    /** 售价 */
    private String sale_price;

    public String getBusi_id() {
        return busi_id;
    }

    public void setBusi_id(String busi_id) {
        this.busi_id = busi_id;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public LinkedList<DeliveryMode> getFreight() {
        return freight;
    }

    public void setFreight(LinkedList<DeliveryMode> freight) {
        this.freight = freight;
    }

    public LinkedList<SaleAttr> getSaleattr() {
        return saleattr;
    }

    public void setSaleattr(LinkedList<SaleAttr> saleattr) {
        this.saleattr = saleattr;
    }

    public List<String> getBuyattr() {
        return buyattr;
    }

    public void setBuyattr(List<String> buyattr) {
        this.buyattr = buyattr;
    }

    public int getPeisong() {
        return peisong;
    }

    public void setPeisong(int peisong) {
        this.peisong = peisong;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }
}
