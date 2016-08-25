package com.qy.business.network;

/**
 * Created by zhangyu on 2016/6/15.
 */
public class ApiUrl {
    public static final String ServiceUrl = "http://58.216.10.195:8008/";

    public static final String BASE_API = ServiceUrl+"pubbusiness/";
    public static final String BASE_PERSONAL = ServiceUrl+"pubpersonal/";
    public static final String BASE_NEW_SUPPLIER = ServiceUrl+"/business/";
    //新进驻商家
    public static final String newSupplier = "newsupplier.html";
    //登录
    public static final String login = "loginnew.html";
    //获取验证码
    public static final String messageCode = "getcode.html";
    //验证安全码
    public static final String verifyPassword = "checksafepass.html";
    //安全验证
    public static final String checkphonepaypassword = "checkphonepaypassword.html";
    //获取省份信息
    public static final String provinceInfo = "getregion.html";
    //注册
    public static final String register = "registernew.html";
    //商家列标
    public static final String shop = "buyshop";
    //商品列标
    public static final String goods="buygoods";
    //加入购物车
    public static final String addToCart = "cartadd";
    //商品详情
    public static final String goodsDetail = "b2cgoodinfo";
    //提交订单
    public static final String commitOrder= "suppliersubmit";
    //请求支付宝订单
    public static final String alipayOrder = "payalipaynew";
}
