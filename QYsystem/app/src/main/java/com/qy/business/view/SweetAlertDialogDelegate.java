package com.qy.business.view;

import android.content.Context;
import android.os.CountDownTimer;

import com.qy.business.activity.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by zhangyu on 2016/7/6.
 */
public class SweetAlertDialogDelegate implements DialogDelegate {
    private SweetAlertDialog pDialog;
    private CountDownTimer mTimer;
    private int mCount = -1;
    private Context mContext;
    public SweetAlertDialogDelegate(Context context){
        mContext = context;
    }
    @Override
    public void showProgressDialog(boolean canCancel, String msg) {
        pDialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText(msg);
        pDialog.setCancelable(canCancel);
        pDialog.show();
        if (mTimer == null) {
            mTimer = new CountDownTimer(800 * 7, 800) {
                @Override
                public void onTick(long millisUntilFinished) {
                    mCount++;
                    switch (mCount) {
                        case 0:
                            pDialog.getProgressHelper().setBarColor(mContext.getResources().getColor(R.color.blue_btn_bg_color));
                            break;
                        case 1:
                            pDialog.getProgressHelper().setBarColor(mContext.getResources().getColor(R.color.material_deep_teal_50));
                            break;
                        case 2:
                            pDialog.getProgressHelper().setBarColor(mContext.getResources().getColor(R.color.success_stroke_color));
                            break;
                        case 3:
                            pDialog.getProgressHelper().setBarColor(mContext.getResources().getColor(R.color.material_deep_teal_20));
                            break;
                        case 4:
                            pDialog.getProgressHelper().setBarColor(mContext.getResources().getColor(R.color.material_blue_grey_80));
                            break;
                        case 5:
                            pDialog.getProgressHelper().setBarColor(mContext.getResources().getColor(R.color.warning_stroke_color));
                            break;
                        case 6:
                            pDialog.getProgressHelper().setBarColor(mContext.getResources().getColor(R.color.success_stroke_color));
                            break;
                    }
                }

                @Override
                public void onFinish() {
                    //  pDialog.dismissWithAnimation();
                }
            };
        }
        mTimer.start();
    }

    @Override
    public void showNormalDialog(String option, String msg) {
        new SweetAlertDialog(mContext, SweetAlertDialog.NORMAL_TYPE)
                .setTitleText(option)
                .setContentText(msg)
                .show();
    }

    @Override
    public void showWarningDialog(String option, String msg, final OnDialogListener listener) {
        new SweetAlertDialog(mContext, SweetAlertDialog.NORMAL_TYPE)
                .setTitleText(option)
                .setConfirmText("确定")
                .setCancelText("取消")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        listener.onClick();
                    }
                })
                .setContentText(msg)
                .show();
    }

    @Override
    public void showSuccessDialog(String option, String msg, final OnDialogListener listener) {
        new SweetAlertDialog(mContext, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText(option)
                .setConfirmText("确定")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        listener.onClick();
                    }
                })
                .setContentText(msg)
                .show();
    }

    @Override
    public void showErrorDialog(String option, String msg) {
        new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE)
                .setTitleText(option)
                .setConfirmText("确定")
                .setContentText(msg)
                .show();
    }

    @Override
    public void stopProgressWithSuccess(String option,String msg, OnDialogListener listener) {
        if (pDialog != null) {
            mTimer.cancel();
            pDialog.setTitleText(msg)
                    .setTitleText(option)
                    .setConfirmText("确定")
                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

        }
        mCount = 0;
    }

    @Override
    public void stopProgressWithFailed(String option,String msg) {
        if (pDialog != null) {
            mTimer.cancel();
            pDialog.setTitleText(msg)
                    .setTitleText(option)
                    .setConfirmText("确定")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                        }
                    })
                    .changeAlertType(SweetAlertDialog.ERROR_TYPE);


        }
        mCount = 0;
    }

    @Override
    public void stopProgressWithWarning(String option, String msg, final OnDialogListener listener) {
        if (pDialog != null) {
            mTimer.cancel();
            pDialog.setTitleText(msg)
                    .setConfirmText("确定")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            listener.onClick();
                        }
                    })
                    .setCancelText("取消")
                    .showCancelButton(true)
                    .changeAlertType(SweetAlertDialog.WARNING_TYPE);
        }
        mCount = 0;
    }

    @Override
    public void clearDialog() {
        if(pDialog != null){
            pDialog.dismissWithAnimation();
        }
    }
}
