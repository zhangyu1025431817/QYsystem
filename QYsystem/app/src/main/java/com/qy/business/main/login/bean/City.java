package com.qy.business.main.login.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyu on 2016/5/13.
 */
public class City  {
    String region_id;
    String region_name;
    String region_type;
    List<Area> areas = new ArrayList<>();

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public String getRegion_type() {
        return region_type;
    }

    public void setRegion_type(String region_type) {
        this.region_type = region_type;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }
}
