package com.qy.business.main.login;

import com.qy.business.main.base.BaseModel;
import com.qy.business.main.base.BasePresenter;
import com.qy.business.bean.NewShopInfo;
import com.qy.business.main.base.BaseProgressView;

import java.util.List;

import rx.Subscription;

/**
 * Created by zhangyu on 2016/5/16.
 */
public interface LoginContract {
    interface Model extends BaseModel{
        Subscription loadUserData(String account, String password, String ime, LoginModel.OnLoginListener listener);
        void loadNewShopData(LoginModel.OnLoadNewShopDataListener listener);
    }
    interface View extends BaseProgressView {
        void showNewShop(List<NewShopInfo> list);
        String getAccount();
        String getPassword();

    }
    abstract class Presenter extends BasePresenter<Model,View>{
        /**
         * 登录
         */
        public abstract void login();
        /**
         * 显示新进驻的供应商
         */
        public abstract void showNewShop();
    }
}
