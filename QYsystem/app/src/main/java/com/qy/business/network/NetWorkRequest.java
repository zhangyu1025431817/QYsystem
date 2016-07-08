package com.qy.business.network;

import com.qy.business.main.login.bean.CommonBean;
import com.qy.business.main.login.bean.ISBindBean;
import com.qy.business.main.login.bean.LoginReturnBean;
import com.qy.business.main.login.bean.NewShopBean;
import com.qy.business.main.login.bean.RegionListBean;
import com.qy.business.main.login.bean.Region_all;
import com.qy.business.main.login.bean.RegisterGetBackBean;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zhangyu on 2016/6/15.
 */
public class NetWorkRequest {
    private static final String SERVICE_RESPONSE_ERROR = "服务器异常";

    public static Observable<LoginReturnBean> login(String userName, String password, String imei, String dpass) {
      return Network.getApiService().login(userName, password, imei, dpass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<NewShopBean>  loadNewShopData() {
       return Network.getApiService().loadNewShopData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static void getRegion(String cid, final Subscriber<List<Region_all>> subscriber) {
        Network.getApiService().getRegion(cid)
                .map(new Func1<RegionListBean, List<Region_all>>() {
                    @Override
                    public List<Region_all> call(RegionListBean bean) {
                        if (bean == null) {
                            subscriber.onError(new Throwable(SERVICE_RESPONSE_ERROR));
                            return null;
                        }
                        if (bean.getStatus() == 1) {
                            return bean.getData();
                        } else {
                            subscriber.onError(new Throwable(bean.getMsg()));
                            return null;
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static void getMessageCode(final String userName, final String password, String safePassword, final String phone, final Subscriber<ISBindBean> subscriber) {
        Network.getApiService().verifyPassword(userName, password, safePassword)
                .flatMap(new Func1<CommonBean, Observable<ISBindBean>>() {
                    @Override
                    public Observable<ISBindBean> call(CommonBean bean) {
                        if(bean == null ){
                            return  Observable.error(new Throwable(SERVICE_RESPONSE_ERROR));
                        }
                        if(bean.getStatus() != 1){
                            return  Observable.error(new Throwable(bean.getMsg()));
                        }
                        return  Network.getApiService().getMessageCode(userName, password, phone);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static void commitBindPhone(String userName, String password, String imei, String code, String phone, Subscriber<ISBindBean> subscriber) {
        Network.getApiService().commitBindPhone(userName, password, imei, code, phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public static void register(String userName, String password, String province, String city, String area, String identityId, String fullName,
                                String phone, String com_name, String com_address, String cer_num, String comtype_id, String maintype,
                                Subscriber<RegisterGetBackBean> subscriber){
        Network.getApiService().register(userName,password,province,city,area,identityId,fullName,phone,com_name,com_address,cer_num,comtype_id,maintype)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
