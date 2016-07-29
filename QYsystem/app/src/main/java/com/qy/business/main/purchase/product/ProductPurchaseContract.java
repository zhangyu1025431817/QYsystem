package com.qy.business.main.purchase.product;

import com.qy.business.main.base.BaseModel;
import com.qy.business.main.base.BasePresenter;
import com.qy.business.main.base.BaseView;

/**
 * Created by zhangyu on 2016/7/28.
 */
public interface ProductPurchaseContract {
    interface Model extends BaseModel{

    }
    interface View extends BaseView{

    }
    abstract class Presenter extends BasePresenter<Model,View>{

    }
}
