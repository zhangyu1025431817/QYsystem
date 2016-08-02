package com.qy.business.bean;

/**
 * Created by zhangyu on 2016/8/2.
 */
public class ProductCategory {
    String supplygcate_id;
    String supplygcate_name;

    public ProductCategory(String supplygcate_id, String supplygcate_name) {
        this.supplygcate_id = supplygcate_id;
        this.supplygcate_name = supplygcate_name;
    }

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
}
