package com.qy.business.main.purchase.product.pay;

import android.content.Context;

import com.qy.business.bean.GetAdresslistBean;
import com.qy.business.bean.PayResult;
import com.qy.business.main.MyApplication;
import com.qy.business.network.ApiUrl;
import com.qy.business.tools.SPUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
import rx.Observable;

/**
 * Created by zhangyu on 2016/8/24.
 */
public class OrderPayModel implements OrderPayContract.Model {
    private Context context = MyApplication.getContext();
    @Override
    public RequestCall commitOrder(String order) {
           // return NetWorkRequest.getAliPayOder(order);
       return OkHttpUtils.get().url(ApiUrl.BASE_PERSONAL+ApiUrl.alipayOrder)
                .addParams("order_sn",order).build();
    }

    @Override
    public Observable<PayResult> aliPay(String payInfo) {
        return null;
    }

    @Override
    public Observable<GetAdresslistBean> getAddressList(String userName, String password, String imei, String binfo) {
        return null;
    }

    @Override
    public void buildOrder(List<String> key, List<String> value) {
        OkHttpUtils.post().url(ApiUrl.BASE_NEW_SUPPLIER + ApiUrl.commitOrder)
                .addParams("username", (String) SPUtils.get(context,"username_aes",""))
                .addParams("userpass", (String) SPUtils.get(context,"password",""))
                .addParams("imei", (String) SPUtils.get(context,"imei",""))
                .addParams(key.get(0), value.get(0))
                .addParams(key.get(1), value.get(1))
                .addParams(key.get(2), value.get(2))
                .addParams(key.get(3), value.get(3))
                .addParams(key.get(4), value.get(4))
                .addParams(key.get(5), value.get(5))
                .addParams(key.get(6), value.get(6))
                .addParams(key.get(7), value.get(7))
        .build().execute(new Callback() {
            @Override
            public Object parseNetworkResponse(Response response, int id) throws Exception {
                return null;
            }

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(Object response, int id) {

            }
        });
    }

}
