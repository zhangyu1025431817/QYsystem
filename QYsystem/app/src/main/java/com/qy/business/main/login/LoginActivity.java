package com.qy.business.main.login;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qy.business.R;
import com.qy.business.main.base.BaseActivity;
import com.qy.business.main.login.bind.BindPhoneActivity;
import com.qy.business.main.login.regist.RegisterActivity;
import com.qy.business.tools.T;
import com.qy.business.view.DialogDelegate;
import com.qy.business.view.SweetAlertDialogDelegate;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by zhangyu on 2016/5/9.
 */
public class LoginActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View {
    //手机号未绑定，该账号已绑定该设备
    private static final String DEVICE_NOT_BIND = "2";
    //手机号已绑定，该账号已绑定其他设备
    private static final String ACCOUNT_BIND_ANOTHER_DEVICE_PHONE_BIND = "3";
    //手机号未绑定，该账号已绑定其他设备
    private static final String ACCOUNT_BIND_ANOTHER_DEVICE_PHONE_NOT_BIND = "4";
    //手机号已绑定，该设备已绑定其他账号
    private static final String DEVICE_BIND_ANOTHER_ACCOUNT_PHONE_BIND = "5";
    //手机号未绑定，该设备已绑定其他账号
    private static final String DEVICE_BIND_ANOTHER_ACCOUNT_PHONE_NOT_BIND = "6";
    //手机号已绑定，设备未绑定
    private static final String DEVICE_NOT_BIND_PHONE_BIND = "7";
    //手机号设备都未绑定
    private static final String DEVICE_NOT_BIND_PHONE_NOT_BIND = "8";
    //未设置安全密码
    private static final String SAFE_PASSWORD_NOT_SET = "9";
    //未绑定手机号
    private static final String PHONE_NOT_BIND= "10";

    private static final String TAG = LoginActivity.class.getSimpleName();
    @Bind(R.id.et_account)
     EditText mEtAccount;
    @Bind(R.id.et_password)
     EditText mEtPassword;
    @Bind(R.id.tv_register)
    TextView mTvRegister;
    @Bind(R.id.btn_login)
    Button mBtnLogin;
    private Context mContext;
    private DialogDelegate mDialogDelegate;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        initData();
    }
    @OnClick(R.id.tv_register)
    public void onRegister(){
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }
    @OnClick(R.id.btn_login)
    public void onLogin(){
        if (isAccountValid() && isPasswordValid()) {
            mDialogDelegate.showProgressDialog(false, getResources().getString(R.string._login_ing));
            mPresenter.login();
        }
    }
    public void initData() {
        mDialogDelegate = new SweetAlertDialogDelegate(this);
        mContext = this;


    }

    @Override
    public void showProgressError(String stateCode, String msg) {

        if (DEVICE_NOT_BIND.equals(stateCode) || DEVICE_NOT_BIND_PHONE_NOT_BIND.equals(stateCode)) {
            mDialogDelegate.showWarningDialog(msg, msg, new DialogDelegate.OnDialogListener() {
                @Override
                public void onClick() {
                    startActivity(new Intent(LoginActivity.this, BindPhoneActivity.class));
                }
            });
        } else if (ACCOUNT_BIND_ANOTHER_DEVICE_PHONE_BIND.equals(stateCode)) {

        } else if (ACCOUNT_BIND_ANOTHER_DEVICE_PHONE_NOT_BIND.equals(stateCode)) {

        } else if (DEVICE_BIND_ANOTHER_ACCOUNT_PHONE_BIND.equals(stateCode)) {

        } else if (DEVICE_BIND_ANOTHER_ACCOUNT_PHONE_NOT_BIND.equals(stateCode)) {

        } else if (DEVICE_NOT_BIND_PHONE_BIND.equals(stateCode)) {

        } else if (SAFE_PASSWORD_NOT_SET.equals(stateCode)) {
            mDialogDelegate.stopProgressWithWarning("安全密码", "请设置安全密码", new DialogDelegate.OnDialogListener() {
                @Override
                public void onClick() {

                }
            });
        } else if(PHONE_NOT_BIND.equals(stateCode)) {


        }else{
            mDialogDelegate.stopProgressWithFailed(msg, msg);
            startActivity(new Intent(LoginActivity.this, BindPhoneActivity.class));
        }
       // showSucceed();
    }

    @Override
    public void showDialogError(String option, String msg) {
        mDialogDelegate.showErrorDialog(option,msg);
    }

    @Override
    public void showSucceed() {
        mDialogDelegate.clearDialog();
       // startActivity(new Intent(this, MainActivity.class));
        startActivity(new Intent(this, BindPhoneActivity.class));
    }


    @Override
    public String getAccount() {
        return mEtAccount.getText().toString();
    }

    private boolean isAccountValid() {
        String account = mEtAccount.getText().toString();
        if (account.isEmpty()) {
            T.showShort(mContext, R.string.account_password_not_null);
            return false;
        }
        return true;
    }

    private boolean isPasswordValid() {
        String password = mEtPassword.getText().toString();
        if (password.isEmpty()) {
            T.showShort(mContext, R.string.account_password_not_null);
            return false;
        }
        return true;
    }

    @Override
    public String getPassword() {
        return mEtPassword.getText().toString();
    }

}
