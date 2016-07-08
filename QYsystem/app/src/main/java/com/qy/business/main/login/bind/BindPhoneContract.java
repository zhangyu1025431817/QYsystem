package com.qy.business.main.login.bind;

import com.qy.business.main.base.BaseModel;
import com.qy.business.main.base.BasePresenter;
import com.qy.business.main.base.BaseProgressView;

/**
 * Created by zhangyu on 2016/5/16.
 */
public interface BindPhoneContract {
    interface Model extends BaseModel{
        /**
         * 获取短信验证码

         * @param phoneNumber 手机号
         * @param listener 回调
         */
        void getMessageCode(String safePassword,String phoneNumber,BindPhoneModel.OnGetMessageListener listener);

        /**
         * 提交绑定

         * @param phoneNumber 手机号
         * @param code 验证码
         * @param listener 回调
         */
        void commit(String phoneNumber,String code,BindPhoneModel.OnBindListener listener);
    }
    interface View extends BaseProgressView {
        String getSafePassword();
        String getPhoneNumber();
        String getMessageCode();
    }
    abstract class Presenter extends BasePresenter<Model,View>{
        public abstract void getMessageCode();
        public abstract void commit();
    }
}
