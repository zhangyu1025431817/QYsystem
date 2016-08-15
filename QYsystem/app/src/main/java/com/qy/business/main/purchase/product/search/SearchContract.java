package com.qy.business.main.purchase.product.search;

import com.qy.business.bean.SearchKey;
import com.qy.business.main.base.BaseModel;
import com.qy.business.main.base.BasePresenter;
import com.qy.business.main.base.BaseView;

import java.util.List;

import rx.Observable;

/**
 * Created by zhangyu on 2016/8/15.
 */
public interface SearchContract {
    interface Model extends BaseModel{
        Observable<List<SearchKey>> getHotKeyList();
    }
    interface View extends BaseView {
        void showHotKey(List<SearchKey> list);
    }
    abstract class Presenter extends BasePresenter<Model,View>{
        abstract void getHotKeyList();
    }

}
