package com.qy.business.main.main;

import com.qy.business.main.base.BaseModel;
import com.qy.business.main.base.BasePresenter;
import com.qy.business.main.base.BaseProgressView;

/**
 * Created by zhangyu on 2016/7/8.
 */
public interface MainContract {
    interface Model extends BaseModel {

    }
    interface View extends BaseProgressView {


    }
    abstract class Presenter extends BasePresenter<Model,View> {

    }
}
