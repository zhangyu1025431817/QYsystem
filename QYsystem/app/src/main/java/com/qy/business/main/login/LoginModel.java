package com.qy.business.main.login;

import android.content.Context;

import com.google.gson.Gson;
import com.qy.business.activity.R;
import com.qy.business.config.Constant;
import com.qy.business.main.MyApplication;
import com.qy.business.main.login.bean.LoginReturnBean;
import com.qy.business.main.login.bean.LoginReturnDataBean;
import com.qy.business.main.login.bean.NewShopBean;
import com.qy.business.main.login.bean.NewShopInfo;
import com.qy.business.network.MySubscriber;
import com.qy.business.network.NetWorkRequest;
import com.qy.business.tools.AESHelper;
import com.qy.business.tools.SPUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.LinkedList;
import java.util.List;

import okhttp3.Call;
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

    public interface OnLoadNewShopDataListener {
        void onError(String msg);

        void onSucceed(List<NewShopInfo> list);
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
                listener.onError("-1", context.getResources().getString(R.string.please_check_net));
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

    @Override
    public void loadNewShopData(final OnLoadNewShopDataListener listener) {
//        NetWorkRequest.loadNewShopData(new MySubscriber<NewShopBean>() {
//            @Override
//            public void onError(Throwable e) {
//                super.onError(e);
//                listener.onError(context.getResources().getString(R.string.please_check_net));
//            }
//
//            @Override
//            public void onNext(NewShopBean newShopBean) {
//                super.onNext(newShopBean);
//                if (newShopBean == null) {
//                    listener.onError(context.getResources().getString(R.string.json_error));
//                }
//                if (newShopBean.getStatus() == 1) {
//                    LinkedList<NewShopInfo> infoList = newShopBean.getData();
//                    listener.onSucceed(infoList);
//                } else {
//                    listener.onError(newShopBean.getMsg());
//                }
//            }
//        });
        /**
         * 因为base URL 不一样，后台代码又不能该，为了不影响整体架构就单独写了一个请求
         */
        String url = Constant.URL + "/business/newsupplier.html";
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onError(context.getResources().getString(R.string.please_check_net));
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        NewShopBean bean = gson.fromJson(response, NewShopBean.class);
                        if (bean == null) {
                            listener.onError(context.getResources().getString(R.string.json_error));
                        }
                        if (bean.getStatus() == 1) {
                            LinkedList<NewShopInfo> infoList = bean.getData();
                            listener.onSucceed(infoList);
                        } else {
                            listener.onError(bean.getMsg());
                        }

                    }
                });
//                .execute(new Callback() {
//                    @Override
//                    public void onError(Request request, Exception e) {
//                        listener.onError(context.getResources().getString(R.string.please_check_net));
//                    }
//
//                    @Override
//                    public void onResponse(String response) {
//                        Gson gson = new Gson();
//                        NewShopBean bean = gson.fromJson(response, NewShopBean.class);
//                        if (bean == null) {
//                            listener.onError(context.getResources().getString(R.string.json_error));
//                        }
//                        if (bean.getStatus() == 1) {
//                            LinkedList<NewShopInfo> infoList = bean.getData();
//                            listener.onSucceed(infoList);
//                        } else {
//                            listener.onError(bean.getMsg());
//                        }
//                    }
//                });
    }


}
