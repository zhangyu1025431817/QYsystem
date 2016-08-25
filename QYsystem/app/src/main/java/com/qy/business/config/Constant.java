package com.qy.business.config;

/**
 * Created by zhangyu on 2016/5/10.
 */
public class Constant {
    /**
     * 测试地址
     */
    public static final String URL = "http://58.216.10.195:8008";
    /**
     * 正式地址
     */
   // public static final String URL="http://mapi.6695.com:8008";
    public static final String BUSINESSES = "/businesses";
    public static final String PUBBUSINESS = "/pubbusiness";
    public static final String PERSONAL = "/personal";
    public static final String pubPersonal = "/pubpersonal";
    //新进驻商家
    public static final String newSupplier = URL+"/business/newsupplier.html";
    //登录
    public static final String login = URL+PUBBUSINESS+"/loginnew.html";
    //获取验证码
    public static final String messageCode = URL+PUBBUSINESS+"/getcode.html";
    //验证安全码
    public static final String verifyPassword = URL+PUBBUSINESS+"/checksafepass.html";
    //绑定手机
    public static final String bindPhone = URL+PUBBUSINESS+"/bind.html";
    //获取省份信息
    public static final String provinceInfo = URL+PUBBUSINESS+"/getregion.html";
}
