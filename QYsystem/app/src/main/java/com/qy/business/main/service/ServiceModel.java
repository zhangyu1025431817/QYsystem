package com.qy.business.main.service;

import com.qy.business.bean.IconBean;
import com.qy.business.local.DataProvider;

import java.util.List;

import rx.Observable;

/**
 * Created by zhangyu on 2016/7/26.
 */
public class ServiceModel implements ServiceContract.Model {
    @Override
    public Observable<List<IconBean>> getFunctionIcons() {
        return DataProvider.getFunctionIcons(DataProvider.QY_SERVICE);
    }
}
