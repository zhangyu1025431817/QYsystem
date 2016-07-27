package com.qy.business.main.login.regist;

import com.qy.business.main.base.BaseModel;
import com.qy.business.main.base.BasePresenter;
import com.qy.business.main.base.BaseProgressView;
import com.qy.business.bean.Region_all;

import java.util.List;

import rx.Subscription;

/**
 * Created by zhangyu on 2016/5/16.
 */
public interface RegisterMvp {
    interface Model extends BaseModel{
        interface OnRequestListener {
            void onSucceed(Object o);

            void onError(String msg);
        }

        /**
         * 获取省市区信息
         *
         * @param listener
         */
        Subscription getRegion(String cid, OnRequestListener listener);

        /**
         * 注册
         */
        Subscription register(String userName, String password, String province, String city, String area, String identityId, String fullName, String phone, String com_name
                , String com_address, String cer_num, String comtype_id, String maintype, OnRequestListener listener);
    }

    interface View extends BaseProgressView {
        void showProvince(List<Region_all> list);

        void showCity(List<Region_all> list);

        void showArea(List<Region_all> list);
    }

    abstract class Presenter extends BasePresenter<Model,View>{
        public abstract void loadProvinceInfo();

        public abstract void loadCityInfo(String id);

        public abstract void loadAreaInfo(String id);

        public abstract void register(String userName, String password, String province, String city, String area, String identityId, String fullName, String phone, String com_name
                , String com_address, String cer_num, String comtype_id, String maintype);
    }
}
