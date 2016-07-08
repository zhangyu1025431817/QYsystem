package com.qy.business.main.login.regist;

import com.qy.business.main.login.bean.Region_all;
import com.qy.business.main.login.bean.RegisterGetBackBean;
import com.qy.business.network.MySubscriber;
import com.qy.business.network.NetWorkRequest;
import com.qy.business.tools.AESHelper;

import java.util.List;

/**
 * Created by zhangyu on 2016/5/16.
 */
public class RegisterModel implements RegisterMvp.Model {
    @Override
    public void getRegion(String cid, final OnRequestListener listener){
        NetWorkRequest.getRegion(cid,new MySubscriber<List<Region_all>>(){
            @Override
            public void onNext(List<Region_all> bean) {
                super.onNext(bean);
                if(bean != null){
                    listener.onSucceed(bean);
                }
            }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                listener.onError(e.getMessage());
            }
        });


    }
    @Override
    public void register(String userName, String password, String province, String city, String area, String identityId, String fullName, String phone,
                         String com_name, String com_address, String cer_num, String comtype_id, String maintype,final OnRequestListener listener) {
        final String account_AES = AESHelper.Encrypt(userName);
        final String passwordStr_AES = AESHelper.Encrypt(password);

        NetWorkRequest.register(account_AES,passwordStr_AES,province,city,area,identityId,fullName,phone,com_name,com_address,cer_num,comtype_id,maintype,
                new MySubscriber<RegisterGetBackBean>(){
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        listener.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(RegisterGetBackBean registerGetBackBean) {
                        super.onNext(registerGetBackBean);
                        if(registerGetBackBean == null){
                            listener.onError("注册失败,服务器返回空!");
                            return;
                        }
                        if(!"1".equals(registerGetBackBean.getStatus())){
                            listener.onError(registerGetBackBean.getMsg());
                        }else{
                            listener.onSucceed(null);
                        }
                    }
                });
    }
}
