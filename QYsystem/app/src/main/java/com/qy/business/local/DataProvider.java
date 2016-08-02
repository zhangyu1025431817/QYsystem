package com.qy.business.local;

import android.content.res.TypedArray;

import com.qy.business.R;
import com.qy.business.bean.IconBean;
import com.qy.business.bean.ProductCategory;
import com.qy.business.main.MyApplication;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhangyu on 2016/7/26.
 */
public class DataProvider {
    public static final int SYSTEM_HOME = 0;
    public static final int SYSTEM_SELL = 1;
    public static final int SYSTEM_PURCHASE = 2;
    public static final int QY_SERVICE = 3;

    public static Observable<List<IconBean>> getFunctionIcons(final int type) {
        return Observable.create(new Observable.OnSubscribe<List<IconBean>>() {
            @Override
            public void call(Subscriber<? super List<IconBean>> subscriber) {
                TypedArray iconArray = null;
                String[] iconName = null;
                switch (type) {
                    case SYSTEM_HOME:
                        iconArray = MyApplication.getContext().getResources().obtainTypedArray(R.array.system_icon);
                        iconName = MyApplication.getContext().getResources().getStringArray(R.array.system_icon_name);
                        break;
                    case SYSTEM_SELL:
                        iconArray = MyApplication.getContext().getResources().obtainTypedArray(R.array.sell_icon);
                        iconName = MyApplication.getContext().getResources().getStringArray(R.array.sell_icon_name);
                        break;
                    case SYSTEM_PURCHASE:
                        iconArray = MyApplication.getContext().getResources().obtainTypedArray(R.array.purchase_icon);
                        iconName = MyApplication.getContext().getResources().getStringArray(R.array.purchase_icon_name);
                        break;
                    case QY_SERVICE:
                        iconArray = MyApplication.getContext().getResources().obtainTypedArray(R.array.bank_icon);
                        iconName = MyApplication.getContext().getResources().getStringArray(R.array.bank_icon_name);
                        break;
                }
                List<IconBean> list = new ArrayList<>();
                int len = iconArray.length();
                for (int i = 0; i < len; i++) {
                    IconBean bean = new IconBean();
                    bean.setResId(iconArray.getResourceId(i, 0));
                    bean.setName(iconName[i]);
                    list.add(bean);
                }
                subscriber.onNext(list);
                iconArray.recycle();

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
    public static List<ProductCategory> productCategoryList(){
        List<ProductCategory> list = new ArrayList<>();
        list.add(new ProductCategory("52","新鲜蔬菜"));
        list.add(new ProductCategory("14","时令水果"));
        list.add(new ProductCategory("5","休闲食品"));
        list.add(new ProductCategory("6","饼干糖果"));
        list.add(new ProductCategory("7","饮料酒水"));
        list.add(new ProductCategory("8","牛奶乳品"));
        list.add(new ProductCategory("9","粮油"));
        return list;
    }
}