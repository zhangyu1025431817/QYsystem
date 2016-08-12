package com.qy.business.main.login;

import com.qy.business.main.base.BaseModel;
import com.qy.business.main.base.BasePresenter;
import com.qy.business.main.base.BaseProgressView;

import rx.Subscription;

/**
 * Created by zhangyu on 2016/5/16.
 */
public interface LoginContract {
    interface Model extends BaseModel{
        Subscription loadUserData(String account, String password, String ime, LoginModel.OnLoginListener listener);
    }
    interface View extends BaseProgressView {
        String getAccount();
        String getPassword();

    }
    abstract class Presenter extends BasePresenter<Model,View>{
        /**
         * 登录
         */
        public abstract void login();

    }
}
