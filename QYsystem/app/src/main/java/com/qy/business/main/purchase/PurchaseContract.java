package com.qy.business.main.purchase;

import com.qy.business.bean.IconBean;
import com.qy.business.main.base.BaseModel;
import com.qy.business.main.base.BasePresenter;
import com.qy.business.main.base.BaseView;

import java.util.List;

import rx.Observable;

/**
 * Created by zhangyu on 2016/7/26.
 */
public interface PurchaseContract {
    interface Model extends BaseModel{
        Observable<List<IconBean>> getFunctionIcons();
    }
    interface View extends BaseView{
        void showFunctionList(List<IconBean> list);
    }
    abstract class Presenter extends BasePresenter<Model,View> {
        public abstract void getFunctionIcons();
    }
}
