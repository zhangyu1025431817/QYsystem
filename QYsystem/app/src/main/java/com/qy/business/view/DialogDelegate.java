package com.qy.business.view;

/**
 * Created by zhangyu on 2016/7/6.
 */
public interface DialogDelegate {


    void showProgressDialog(boolean canCancel, String msg);


    void showNormalDialog(String option, String msg);
    void showWarningDialog(String option, String msg, OnDialogListener listener);
    void showSuccessDialog(String option, String msg, OnDialogListener listener);
    void showErrorDialog(String option, String msg);

    void stopProgressWithSuccess(String option, String msg, OnDialogListener listener);
    void stopProgressWithFailed(String option, String msg);
    void stopProgressWithWarning(String option, String msg, OnDialogListener listener);

    void clearDialog();

    interface OnDialogListener{
        void onClick();
    }
}
