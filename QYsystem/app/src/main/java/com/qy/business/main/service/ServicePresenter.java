package com.qy.business.main.service;

import com.qy.business.bean.IconBean;
import com.qy.business.network.MySubscriber;

import java.util.List;

/**
 * Created by zhangyu on 2016/7/26.
 */
public class ServicePresenter extends ServiceContract.Presenter {
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
