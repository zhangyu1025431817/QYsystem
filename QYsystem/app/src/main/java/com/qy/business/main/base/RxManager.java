package com.qy.business.main.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zhangyu on 2016/6/30.
 */
public class RxManager {
    /**
     * 管理订阅者
     */
    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public void add(Subscription sp){
        mCompositeSubscription.add(sp);
    }

    public void clear(){
        //取消订阅
        mCompositeSubscription.unsubscribe();
    }
}
