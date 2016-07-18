package com.qy.business.main.login.regist;

import com.qy.business.main.login.bean.Region_all;

import java.util.List;

/**
 * Created by zhangyu on 2016/5/16.
 */
public class RegisterPresenter extends RegisterMvp.Presenter {


    @Override
    public void loadProvinceInfo() {
        mRxManager.add(mModel.getRegion("", new RegisterMvp.Model.OnRequestListener() {
            @Override
            public void onSucceed(Object o) {
                List<Region_all> list = (List<Region_all>) o;
                mView.showProvince(list);
            }

            @Override
            public void onError(String msg) {

            }
        }));
    }

    @Override
    public void loadCityInfo(String id) {
        mRxManager.add(mModel.getRegion(id, new RegisterMvp.Model.OnRequestListener() {
            @Override
            public void onSucceed(Object o) {
                List<Region_all> list = (List<Region_all>) o;
                mView.showCity(list);
            }

            @Override
            public void onError(String msg) {
            }
        }));
    }

    @Override
    public void loadAreaInfo(String id) {
        mRxManager.add(mModel.getRegion(id, new RegisterMvp.Model.OnRequestListener() {
            @Override
            public void onSucceed(Object o) {
                List<Region_all> list = (List<Region_all>) o;
                mView.showArea(list);
            }

            @Override
            public void onError(String msg) {

            }
        }));
    }


    @Override
    public void register(String userName, String password, String province, String city, String area, String identityId, String fullName, String phone, String com_name, String com_address, String cer_num, String comtype_id, String maintype) {
        mRxManager.add(mModel.register(userName, password, province, city, area, identityId, fullName, phone, com_name, com_address, cer_num, comtype_id, maintype, new RegisterMvp.Model.OnRequestListener() {
            @Override
            public void onSucceed(Object o) {
                mView.showSucceed();
            }

            @Override
            public void onError(String msg) {
                mView.showProgressError(null, msg);
            }
        }));
    }


    @Override
    public void onStart() {

    }
}
