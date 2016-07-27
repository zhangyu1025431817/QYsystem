package com.qy.business.main.login.bind;

import android.content.Context;

import com.qy.business.main.MyApplication;
import com.qy.business.bean.ISBindBean;
import com.qy.business.network.MySubscriber;
import com.qy.business.network.NetWorkRequest;
import com.qy.business.tools.AESHelper;
import com.qy.business.tools.SPUtils;

/**
 * Created by zhangyu on 2016/5/12.
 */
public class BindPhoneModel implements BindPhoneContract.Model {
    private Context context = MyApplication.getContext();
    private static final String TAG = "BindPhoneModel";

    public interface OnGetMessageListener {
        void onSucceed();

        void onError(String msg);
    }

    public interface OnBindListener {
        void onSucceed();

        void onError(String msg);
    }

    @Override
    public void getMessageCode(String safePassword, String phoneNumber, final OnGetMessageListener listener) {
        final String userName_AES = (String) SPUtils.get(context, "username_aes", "");
        final String password_AES = (String) SPUtils.get(context, "password", "");
        final String safePassword_AES = AESHelper.Encrypt(safePassword);
        NetWorkRequest.getMessageCode(userName_AES, password_AES, safePassword_AES, phoneNumber, new MySubscriber<ISBindBean>() {
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                listener.onError(e.getMessage());
            }

            @Override
            public void onNext(ISBindBean bean) {
                super.onNext(bean);
                if (bean == null) {
                    listener.onError("服务器返数据异常");
                    return;
                }
                if (bean.getStatus() == 1) {
                    listener.onSucceed();
                } else {
                    listener.onError(bean.getMsg());
                }
            }
        });

    }

    @Override
    public void commit(String phoneNumber, String code, final OnBindListener listener) {
        String userName_AES = (String) SPUtils.get(context, "username_aes", "");
        String password_AES = (String) SPUtils.get(context, "password", "");
        String imei = (String) SPUtils.get(context, "imei", "");

        NetWorkRequest.commitBindPhone(userName_AES, password_AES, imei, code, phoneNumber, new MySubscriber<ISBindBean>() {
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                listener.onError(e.getMessage());
            }

            @Override
            public void onNext(ISBindBean bean) {
                super.onNext(bean);
                if (bean == null) {
                    listener.onError("网络连接异常");
                    return;
                }
                if (bean.getStatus() == 1) {
                    listener.onSucceed();
                } else {
                    listener.onError(bean.getMsg());
                }
            }
        });
    }
}
