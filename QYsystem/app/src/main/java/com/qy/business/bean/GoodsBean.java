package com.qy.business.bean;

/**
 * Created by zhangyu on 2016/8/1.
 */
public class GoodsBean {
    private String status;
    private String msg;
    private GoodsList data;

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

    public GoodsList getData() {
        return data;
    }

    public void setData(GoodsList data) {
        this.data = data;
    }

}
