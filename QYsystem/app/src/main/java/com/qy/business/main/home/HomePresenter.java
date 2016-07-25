package com.qy.business.main.home;

import com.qy.business.main.home.bean.Ad;
import com.qy.business.main.home.bean.IconBean;
import com.qy.business.network.MySubscriber;

import java.util.List;

/**
 * Created by zhangyu on 2016/7/25.
 */
public class HomePresenter extends HomeMvp.Presenter{

    @Override
    public void getAd() {
        mRxManager.add(mModel.getAd().subscribe(new MySubscriber<List<Ad>>(){
            @Override
            public void onNext(List<Ad> ads) {
                mView.showAd(ads);
            }
        }));
    }

    @Override
    public void getFunctionIcons() {
        mRxManager.add(mModel.getFunctionIcons().subscribe(new MySubscriber<List<IconBean>>(){
            @Override
            public void onNext(List<IconBean> iconBeen) {
                mView.showFunctionList(iconBeen);
            }
        }));
    }

    @Override
    public void onStart() {

    }
}
