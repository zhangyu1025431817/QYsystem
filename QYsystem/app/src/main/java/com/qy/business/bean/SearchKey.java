package com.qy.business.bean;

import java.io.Serializable;

/**
 * Created by zhangyu on 2016/8/15.
 */
public class SearchKey implements Serializable{
    private String name;
    private int type;

    public SearchKey(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public SearchKey() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
