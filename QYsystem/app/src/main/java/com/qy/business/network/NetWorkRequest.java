package com.qy.business.network;

import com.qy.business.bean.GoodsBean;
import com.qy.business.bean.ISBindBean;
import com.qy.business.bean.LoginReturnBean;
import com.qy.business.bean.NewShopBean;
import com.qy.business.bean.RegionListBean;
import com.qy.business.bean.RegisterGetBackBean;
import com.qy.business.bean.SafeVerify;
import com.qy.business.bean.ShopBean;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
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

    public static Observable<NewShopBean> loadNewShopData() {
        return Network.getApiService().loadNewShopData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<RegionListBean> getRegion(String cid) {
        return Network.getApiService().getRegion(cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public static Observable<ISBindBean> getMessageCode(final String userName, final String password, final String phone) {
        return Network.getApiService().getMessageCode(userName, password,phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public static Observable<SafeVerify> checkPhonePayPassword(String userName, String password, String imei, String code, String phone, String safePwd) {
       return Network.getApiService().checkPhonePayPassword(userName, password, imei, code, phone,safePwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<RegisterGetBackBean> register(String userName, String password, String province, String city, String area, String identityId, String fullName,
                                                           String phone, String com_name, String com_address, String cer_num, String comtype_id, String maintype) {
        return Network.getApiService().register(userName, password, province, city, area, identityId, fullName, phone, com_name, com_address, cer_num, comtype_id, maintype)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<ShopBean> getShopList(int page, int limit, String supplyId, String cateId
            , String brandId, String areaId, String keyword) {
        return Network.getApiService().getShopList(page, limit, supplyId, cateId, brandId, areaId, keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public static Observable<GoodsBean> getGoodsList(int page, int limit, String supplyId, String cateId
            , String brandId, String areaId, String keyword) {
        return Network.getApiService().getGoodsList(page, limit, supplyId, cateId, brandId, areaId, keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
