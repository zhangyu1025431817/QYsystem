package com.qy.business.main.base;

import com.qy.business.main.base.BaseView;

/**
 * Created by zhangyu on 2016/5/12.
 */
public interface BaseProgressView extends BaseView{
    void showProgressError(String stateCode,String msg);
    void showDialogError(String option,String msg);
    void showSucceed();
}
