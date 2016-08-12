package com.qy.business.main.login;

import android.content.Context;

import com.qy.business.R;
import com.qy.business.bean.LoginReturnBean;
import com.qy.business.bean.LoginReturnDataBean;
import com.qy.business.main.MyApplication;
import com.qy.business.network.MySubscriber;
import com.qy.business.network.NetWorkRequest;
import com.qy.business.tools.AESHelper;
import com.qy.business.tools.SPUtils;

import rx.Subscription;

/**
 * Created by zhangyu on 2016/5/11.
 */
public class LoginModel implements LoginContract.Model {
    private Context context = MyApplication.getContext();

    public interface OnLoginListener {
        void onError(String stateCode, String msg);

        void onSucceed();
    }


    @Override
    public Subscription loadUserData(String account, String password, String ime, final OnLoginListener listener) {
        final String imei_AES = AESHelper.Encrypt(ime);
        final String userNameStr_AES = AESHelper.Encrypt(account);
        final String passwordStr_AES = AESHelper.Encrypt(password);
        return NetWorkRequest.login(userNameStr_AES, passwordStr_AES, imei_AES, "").subscribe(new MySubscriber<LoginReturnBean>() {
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                listener.onError("-1", e.getMessage());
            }

            @Override
            public void onNext(LoginReturnBean bean) {
                super.onNext(bean);
                if (bean == null) {
                    listener.onError("-1", context.getResources().getString(R.string.json_error));
                }
                if ("1".equals(bean.getStatus())) {
                    LoginReturnDataBean dataBean = bean.getData();
                    if (dataBean != null) {
                        SPUtils.put(context, "username", dataBean.getUser_name());
                        SPUtils.put(context, "userid", dataBean.getUser_id());
                        SPUtils.put(context, "money", dataBean.getUser_money());
                        SPUtils.put(context, "lastlogintime", dataBean.getLast_time());
                        SPUtils.put(context, "username_aes", userNameStr_AES);
                        SPUtils.put(context, "password", passwordStr_AES);
                        SPUtils.put(context, "imei", imei_AES);
                    }
                    listener.onSucceed();
                } else {
                    listener.onError(bean.getStatus(), bean.getMsg());
                }
            }
        });
    }
}
