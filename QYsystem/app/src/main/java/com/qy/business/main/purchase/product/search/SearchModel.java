package com.qy.business.main.purchase.product.search;

import com.qy.business.bean.SearchKey;
import com.qy.business.local.DataProvider;

import java.util.List;

import rx.Observable;

/**
 * Created by zhangyu on 2016/8/15.
 */
public class SearchModel implements SearchContract.Model{

    @Override
    public Observable<List<SearchKey>> getHotKeyList() {

        return DataProvider.getHotKeyList();
    }
}
