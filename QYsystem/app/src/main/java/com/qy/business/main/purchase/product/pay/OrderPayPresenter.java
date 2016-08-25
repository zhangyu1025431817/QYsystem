package com.qy.business.main.purchase.product.pay;

import android.app.Activity;

import com.alipay.sdk.app.PayTask;
import com.qy.business.bean.PayResult;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhangyu on 2016/8/24.
 */
public class OrderPayPresenter extends OrderPayContract.Presenter {
    Activity mContext;

    @Override
    void buildOrder(List<String> key, List<String> value) {

    }

    @Override
    void pay(Activity context,String order) {
        mContext = context;
        mModel.commitOrder(order).execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                AliPay(response);
            }
        });
    }

    @Override
    void getAddressList(String binfo) {

    }

    @Override
    public void onStart() {

    }

    private void AliPay(final String s) {
        Observable.create(new Observable.OnSubscribe<PayResult>() {
            @Override
            public void call(Subscriber<? super PayResult> subscriber) {
                PayTask alipay = new PayTask(mContext);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(s, true);
                subscriber.onNext(new PayResult(result));
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PayResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(PayResult payResult) {
                        mView.paySucceed();
                    }
                });
    }
}
