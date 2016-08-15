package com.qy.business.main.purchase.product.search;

import com.qy.business.bean.SearchKey;
import com.qy.business.network.MySubscriber;

import java.util.List;

/**
 * Created by zhangyu on 2016/8/15.
 */
public class SearchPresenter extends SearchContract.Presenter {
    @Override
    public void onStart() {

    }

    @Override
    void getHotKeyList() {
        mRxManager.add(mModel.getHotKeyList().subscribe(new MySubscriber<List<SearchKey>>(){
            @Override
            public void onNext(List<SearchKey> list) {
                super.onNext(list);
                mView.showHotKey(list);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.showHotKey(null);
            }
        }));
    }
}
