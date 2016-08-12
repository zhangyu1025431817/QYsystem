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
import com.qy.business.main.main.MainActivity;
import com.qy.business.tools.SPUtils;
import com.qy.business.tools.T;
import com.qy.business.view.DialogDelegate;
import com.qy.business.view.SweetAlertDialogDelegate;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by zhangyu on 2016/5/9.
 */
public class LoginActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View {
    //账号不存在
    private static final String ACCOUNT_NOT_EXIST = "000201";
    //账号被锁定
    private static final String ACCOUNT_BEEN_LOCKED = "000202";
    //密码错误
    private static final String ACCOUNT_PASSWORD_ERROR = "000203";
    //主账户被锁定
    private static final String MAIN_ACCOUNT_BEEN_LOCKED = "000204";
    //未绑定手机号
    private static final String ACCOUNT_NOT_BIND_PHONE = "1003";
    //未设置安全密码
    private static final String ACCOUNT_NOT_SET_SAFE_PASSWORD = "1004";
    //登陆成功
    private static final String LOGIN_SUCCEED = "1";


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
    public void onRegister() {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    @OnClick(R.id.btn_login)
    public void onLogin() {
        if (isAccountValid() && isPasswordValid()) {
            mDialogDelegate.showProgressDialog(false, getResources().getString(R.string._login_ing));
            mPresenter.login();
        }
    }

    public void initData() {
        mDialogDelegate = new SweetAlertDialogDelegate(this);
        mContext = this;

        mEtAccount.setText((String) SPUtils.get(this, "username", ""));
    }

    @Override
    public void showProgressError(String stateCode, String msg) {

        if (ACCOUNT_NOT_EXIST.equals(stateCode)) {
            mDialogDelegate.stopProgressWithFailed("账号不存在", "请输入其他账号,或注册新账号");

        } else if (ACCOUNT_BEEN_LOCKED.equals(stateCode)) {
            mDialogDelegate.stopProgressWithFailed("账号被锁定", "请输入其他账号,或联系客服");
        } else if (ACCOUNT_PASSWORD_ERROR.equals(stateCode)) {
            mDialogDelegate.stopProgressWithFailed("密码错误", "请重新输入密码");
        } else if (MAIN_ACCOUNT_BEEN_LOCKED.equals(stateCode)) {
            mDialogDelegate.stopProgressWithFailed("主账户被锁定", "请输入其他账号,或联系客服");
        } else if (ACCOUNT_NOT_SET_SAFE_PASSWORD.equals(stateCode)) {
            mDialogDelegate.stopProgressWithWarning("安全密码", "设置安全密码?", new DialogDelegate.OnDialogListener() {
                @Override
                public void onClick() {
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, BindPhoneActivity.class);
                    intent.putExtra("userName", getAccount());
                    intent.putExtra("password", getPassword());
                    startActivity(intent);
                    mDialogDelegate.clearDialog();
                }
            });
        } else if (ACCOUNT_NOT_BIND_PHONE.equals(stateCode)) {
            mDialogDelegate.stopProgressWithWarning("手机号未绑定", "绑定手机号?", new DialogDelegate.OnDialogListener() {
                @Override
                public void onClick() {
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, BindPhoneActivity.class);
                    intent.putExtra("userName", getAccount());
                    intent.putExtra("password", getPassword());
                    startActivity(intent);
                    mDialogDelegate.clearDialog();
                }
            });

        } else {
            mDialogDelegate.stopProgressWithFailed(msg, msg);
        }
    }

    @Override
    public void showDialogError(String option, String msg) {
        mDialogDelegate.showErrorDialog(option, msg);
    }

    @Override
    public void showSucceed() {
        mDialogDelegate.clearDialog();
        startActivity(new Intent(this, MainActivity.class));
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDialogDelegate.clearDialog();
    }
}
