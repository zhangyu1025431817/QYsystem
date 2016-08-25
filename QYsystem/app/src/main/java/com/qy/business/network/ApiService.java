package com.qy.business.network;

import com.qy.business.bean.AddCartResult;
import com.qy.business.bean.CommonBean;
import com.qy.business.bean.GoodsBean;
import com.qy.business.bean.ISBindBean;
import com.qy.business.bean.LoginReturnBean;
import com.qy.business.bean.NewShopBean;
import com.qy.business.bean.RegionListBean;
import com.qy.business.bean.RegisterGetBackBean;
import com.qy.business.bean.SafeVerify;
import com.qy.business.bean.ShopBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhangyu on 2016/6/15.
 */
public interface ApiService {
    @GET(ApiUrl.login)
    Observable<LoginReturnBean> login(@Query("username") String username, @Query("userpass") String password, @Query("imei") String imei, @Query("dpass") String dpass);

    @GET(ApiUrl.newSupplier)
    Observable<NewShopBean> loadNewShopData();

    @GET(ApiUrl.provinceInfo)
    Observable<RegionListBean> getRegion(@Query("cid") String cid);

    @GET(ApiUrl.verifyPassword)
    Observable<CommonBean> verifyPassword(@Query("username") String username, @Query("userpass") String password, @Query("safepass") String safepass);

    @GET(ApiUrl.messageCode)
    Observable<ISBindBean> getMessageCode(@Query("username") String username, @Query("userpass") String password, @Query("phone") String phone);

    @GET(ApiUrl.checkphonepaypassword)
    Observable<SafeVerify> checkPhonePayPassword(@Query("username") String username, @Query("userpass") String password, @Query("imei") String imei, @Query("phone") String code,
                                                 @Query("code") String phone, @Query("pwd") String safePwd);

    @GET(ApiUrl.register)
    Observable<RegisterGetBackBean> register(@Query("username") String userName, @Query("userpass") String userpass, @Query("areas[]") String province
            , @Query("areas[]") String city, @Query("areas[]") String area, @Query("identity_id") String identity_id, @Query("fullname") String fullName
            , @Query("phone") String phone, @Query("com_name") String com_name, @Query("com_address") String com_address, @Query("cer_num") String cer_num
            , @Query("comtype_id") String comtype_id, @Query("maintype") String maintype);

    @GET(ApiUrl.shop)
    Observable<ShopBean> getShopList(@Query("page") int page, @Query("limit") int limit, @Query("supplygcate_id") String supplyId, @Query("cid") String cateId
            , @Query("brand_id") String brandId, @Query("area_id") String areaId, @Query("keyword") String keyword);

    @GET(ApiUrl.goods)
    Observable<GoodsBean> getGoodsList(@Query("page") int page, @Query("limit") int limit, @Query("supplygcate_id") String supplyId, @Query("cid") String cateId
            , @Query("brand_id") String brandId, @Query("area_id") String areaId, @Query("keyword") String keyword);
    @GET(ApiUrl.addToCart)
    Observable<AddCartResult> addToShoppingCart(@Query("username") String name,@Query("userpass") String password,@Query("imei") String imei
            ,@Query("id") String goodsId,@Query("sku") String sku,@Query("num") int number);
    @GET(ApiUrl.alipayOrder)
    Observable<String> getAliPayOrder(@Query("order_sn") String order);
}
