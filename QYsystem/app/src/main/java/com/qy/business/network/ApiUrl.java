package com.qy.business.network;

/**
 * Created by zhangyu on 2016/6/15.
 */
public class ApiUrl {
    public static final String ServiceUrl = "http://58.216.10.202:8008/";

    public static final String BASE_API = ServiceUrl+"pubbusiness/";
    public static final String BASE_NEW_SUPPLIER = ServiceUrl+"/business/";
    //新进驻商家
    public static final String newSupplier = "newsupplier.html";
    //登录
    public static final String login = "login.html";
    //获取验证码
    public static final String messageCode = "getcode.html";
    //验证安全码
    public static final String verifyPassword = "checksafepass.html";
    //绑定手机
    public static final String bindPhone = "bind.html";
    //获取省份信息
    public static final String provinceInfo = "getregion.html";
    //注册
    public static final String register = "register.html";
}
