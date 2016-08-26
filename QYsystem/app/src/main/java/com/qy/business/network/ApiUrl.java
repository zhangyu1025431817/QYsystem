package com.qy.business.network;

/**
 * Created by zhangyu on 2016/6/15.
 */
public class ApiUrl {
    public static final String ServiceUrl = "http://58.216.10.195:8008/";

    public static final String BASE_PERSONAL = ServiceUrl+"pubpersonal/";
    public static final String BASE_NEW_SUPPLIER = ServiceUrl+"business/";
    //新进驻商家
    public static final String newSupplier = "pubbusiness/newsupplier.html";
    //登录
    public static final String login = "pubbusiness/loginnew.html";
    //获取验证码
    public static final String messageCode = "pubbusiness/getcode.html";
    //验证安全码
    public static final String verifyPassword = "pubbusiness/checksafepass.html";
    //安全验证
    public static final String checkphonepaypassword = "pubbusiness/checkphonepaypassword.html";
    //获取省份信息
    public static final String provinceInfo = "pubbusiness/getregion.html";
    //注册
    public static final String register = "pubbusiness/registernew.html";
    //商家列表
    public static final String shop = "pubbusiness/buyshop.html";
    //商品列表
    public static final String goods="pubbusiness/buygoods.html";
    //加入购物车
    public static final String addToCart = "cartadd.html";
    //商品详情
    public static final String goodsDetail = "pubpersonal/b2cgoodinfo.html";
    //提交订单
    public static final String commitOrder= "businesses/suppliersubmit.html";
    //请求支付宝订单
    public static final String alipayOrder = "payalipaynew.html";
}
