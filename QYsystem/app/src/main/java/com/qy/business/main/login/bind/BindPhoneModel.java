package com.qy.business.main.login.bind;

import android.content.Context;

import com.qy.business.bean.ISBindBean;
import com.qy.business.bean.SafeVerify;
import com.qy.business.main.MyApplication;
import com.qy.business.network.NetWorkRequest;
import com.qy.business.tools.AESHelper;
import com.qy.business.tools.SPUtils;

import rx.Observable;

/**
 * Created by zhangyu on 2016/5/12.
 */
public class BindPhoneModel implements BindPhoneContract.Model {
    private Context context = MyApplication.getContext();

    @Override
    public Observable<ISBindBean> getMessageCode(String userName,String password,String phoneNumber) {
        final String userName_AES = AESHelper.Encrypt(userName);
        final String password_AES = AESHelper.Encrypt(password);
        return NetWorkRequest.getMessageCode(userName_AES, password_AES, phoneNumber);

    }

    @Override
    public Observable<SafeVerify> commit(String userName,String password,String phoneNumber, String code, String safePassword) {
        final String userName_AES = AESHelper.Encrypt(userName);
        final String password_AES = AESHelper.Encrypt(password);
        String imei = (String) SPUtils.get(context, "imei", "");
        safePassword =  AESHelper.Encrypt(safePassword);
        return NetWorkRequest.checkPhonePayPassword(userName_AES,password_AES,imei,phoneNumber,code,safePassword);
    }
}
