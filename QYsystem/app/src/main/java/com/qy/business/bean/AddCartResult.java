package com.qy.business.bean;

/**
 * Created by zhangyu on 2016/8/16.
 */
public class AddCartResult {
    private String status;
    private String msg;
    private Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{
        String  sumprice;
        int count;

        public String getSumprice() {
            return sumprice;
        }

        public void setSumprice(String sumprice) {
            this.sumprice = sumprice;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
