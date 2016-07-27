package com.qy.business.main.home;

import com.qy.business.main.base.BaseModel;
import com.qy.business.main.base.BasePresenter;
import com.qy.business.main.base.BaseView;
import com.qy.business.bean.Ad;
import com.qy.business.bean.IconBean;

import java.util.List;

import rx.Observable;

/**
 * Created by zhangyu on 2016/7/25.
 */
public interface HomeContract {
    interface Model extends BaseModel{
        Observable<List<Ad>> getAd();
        Observable<List<IconBean>> getFunctionIcons();
    }
    interface View extends BaseView{
        void showAd(List<Ad> list);
        void showFunctionList(List<IconBean> list);
    }
    abstract class Presenter extends BasePresenter<Model,View>{
        public abstract void getAd();
        public abstract void getFunctionIcons();
    }
}
