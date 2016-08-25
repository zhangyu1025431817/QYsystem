package com.qy.business.main.purchase.product.pay;

import android.app.Activity;

import com.qy.business.bean.GetAdresslistBean;
import com.qy.business.bean.PayResult;
import com.qy.business.main.base.BaseModel;
import com.qy.business.main.base.BasePresenter;
import com.qy.business.main.base.BaseView;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.List;

import rx.Observable;

/**
 * Created by zhangyu on 2016/8/24.
 */
public interface OrderPayContract {
     interface Model extends BaseModel{
         RequestCall commitOrder(String order);
         Observable<PayResult> aliPay(String payInfo);
         Observable<GetAdresslistBean> getAddressList(String userName,String password,String imei,String binfo);
         void buildOrder(List<String> key, List<String> value);
     }
    interface View extends BaseView {
        void paySucceed();
        void payFailed(String msg);
        void showAddress(GetAdresslistBean bean);
    }
    abstract class Presenter extends BasePresenter<Model,View>{
        abstract void buildOrder(List<String> key, List<String> value);
        abstract void pay(Activity mContext,String order);
        abstract void getAddressList(String binfo);

    }
}
