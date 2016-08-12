package com.qy.business.main.login.bind;

import com.qy.business.bean.ISBindBean;
import com.qy.business.bean.SafeVerify;
import com.qy.business.main.base.BaseModel;
import com.qy.business.main.base.BasePresenter;
import com.qy.business.main.base.BaseProgressView;

import rx.Observable;

/**
 * Created by zhangyu on 2016/5/16.
 */
public interface BindPhoneContract {
    interface Model extends BaseModel{
        /**
         * 获取短信验证码

         * @param phoneNumber 手机号
         */
        Observable<ISBindBean> getMessageCode(String userName,String password,String phoneNumber);

        /**
         * 提交绑定

         * @param phoneNumber 手机号
         * @param code 验证码
         */
        Observable<SafeVerify> commit(String userName,String password,String phoneNumber, String code, String safePassword);
    }
    interface View extends BaseProgressView {
        String getUserName();
        String getPassword();
        String getSafePassword();
        String getPhoneNumber();
        String getMessageCode();
    }
    abstract class Presenter extends BasePresenter<Model,View>{
        public abstract void getMessageCode();
        public abstract void commit();
    }
}
