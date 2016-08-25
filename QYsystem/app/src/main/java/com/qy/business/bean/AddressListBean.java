package com.qy.business.bean;

/**
 * Created by zhangyu on 2016/8/25.
 */
public class AddressListBean {
    private String status;
    private String msg;
    private AddressBean data;

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

    public AddressBean getData() {
        return data;
    }

    public void setData(AddressBean data) {
        this.data = data;
    }
}
