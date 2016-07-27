package com.qy.business.main.purchase;

import com.qy.business.bean.IconBean;
import com.qy.business.local.DataProvider;

import java.util.List;

import rx.Observable;

/**
 * Created by zhangyu on 2016/7/26.
 */
public class PurchaseModel implements PurchaseContract.Model {
    @Override
    public Observable<List<IconBean>> getFunctionIcons() {
        return DataProvider.getFunctionIcons(DataProvider.SYSTEM_PURCHASE);
    }
}
