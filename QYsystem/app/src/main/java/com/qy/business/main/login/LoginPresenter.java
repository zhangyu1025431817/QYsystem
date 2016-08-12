package com.qy.business.main.login;

import android.app.Activity;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.qy.business.main.MyApplication;

/**
 * Created by zhangyu on 2016/5/11.
 */
public class LoginPresenter extends LoginContract.Presenter{

    @Override
    public void login() {
        if(mView.getAccount() == null || mView.getPassword() == null){
            return;
        }
        //获取设备id pad没有电话卡就获取android_id
        String imei = ((TelephonyManager) MyApplication.getContext().getSystemService(Activity.TELEPHONY_SERVICE)).getDeviceId();
        if (imei == null || imei.isEmpty()) {
            imei = Settings.Secure.getString(MyApplication.getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        }
        mRxManager.add(mModel.loadUserData(mView.getAccount(), mView.getPassword(), imei, new LoginModel.OnLoginListener() {
            @Override
            public void onError(String stateCode, String msg) {
                mView.showProgressError(stateCode,msg);
            }

            @Override
            public void onSucceed() {
                mView.showSucceed();
            }
        }));
    }

    @Override
    public void onStart() {

    }
}
