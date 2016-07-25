package com.qy.business.main.home;

import android.content.res.TypedArray;

import com.qy.business.R;
import com.qy.business.main.MyApplication;
import com.qy.business.main.home.bean.Ad;
import com.qy.business.main.home.bean.IconBean;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhangyu on 2016/7/25.
 */
public class HomeModel implements HomeMvp.Model {
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
        return Observable.create(new Observable.OnSubscribe<List<IconBean>>() {
            @Override
            public void call(Subscriber<? super List<IconBean>> subscriber) {
                TypedArray iconArray = MyApplication.getContext().getResources().obtainTypedArray(R.array.system_icon);
                String[] iconName = MyApplication.getContext().getResources().getStringArray(R.array.system_icon_name);

                List<IconBean> list = new ArrayList<>();
                int len = iconArray.length();
                for (int i = 0; i < len; i++) {
                    IconBean bean = new IconBean();
                    bean.setResId(iconArray.getResourceId(i, 0));
                    bean.setName(iconName[i]);
                    list.add(bean);
                }
                iconArray.recycle();
                subscriber.onNext(list);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
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
