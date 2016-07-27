package com.qy.business.main.home;

import com.qy.business.bean.Ad;
import com.qy.business.bean.IconBean;
import com.qy.business.local.DataProvider;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhangyu on 2016/7/25.
 */
public class HomeModel implements HomeContract.Model {
    @Override
    public Observable<List<Ad>> getAd() {
        return Observable.create(new Observable.OnSubscribe<List<Ad>>() {
            @Override
            public void call(Subscriber<? super List<Ad>> subscriber) {
                    subscriber.onNext(getAdList());
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<IconBean>> getFunctionIcons() {
        return DataProvider.getFunctionIcons(DataProvider.SYSTEM_HOME);
    }
    public static List<Ad> getAdList(){
        ArrayList<Ad> arr = new ArrayList<>();
        arr.add(new Ad("","http://www.bilibili.com/topic/v2/1004.html"));
        arr.add(new Ad("","http://www.bilibili.com/topic/1003.html"));
        arr.add(new Ad("","http://yoo.bilibili.com/html/activity/cq2015/index.html"));
        arr.add(new Ad("","http://www.bilibili.com/html/activity-acsociety.html"));
        return arr;
    }
}
