package com.qy.business.main.login.bind;

import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.qy.business.activity.R;
import com.qy.business.main.base.BaseActivity;
import com.qy.business.tools.StringUtils;
import com.qy.business.tools.T;
import com.qy.business.view.DialogDelegate;
import com.qy.business.view.SweetAlertDialogDelegate;

import butterknife.Bind;

/**
 * Created by zhangyu on 2016/5/12.
 */
public class BindPhoneActivity extends BaseActivity<BindPhonePresenter, BindPhoneModel> implements View.OnClickListener, BindPhoneContract.View {

    private EditText mEtSafePassword;
    private EditText mEtPhoneNumber;
    private EditText mEtMessageCode;
    private Button mBtnGetCode;
    @Bind(R.id.id_tool_bar)
    Toolbar mToolbar;
    private CountDownTimer mTimer;
    private int mCount = 60;
    private DialogDelegate mDialogDelegate;

    public void initData() {

        mDialogDelegate = new SweetAlertDialogDelegate(this);
        mTimer = new CountDownTimer(1000 * 60, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mBtnGetCode.setEnabled(false);
                mBtnGetCode.setText(mCount + getString(R.string.get_again));
                mCount--;
            }

            @Override
            public void onFinish() {
                mBtnGetCode.setText(getString(R.string.get_authentication_code));
                mBtnGetCode.setEnabled(true);
                mCount = 60;
            }
        };
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_bind_phone;
    }

    @Override
    public void initView() {
        findViewById(R.id.btn_commit).setOnClickListener(this);
        mEtSafePassword = (EditText) findViewById(R.id.et_safe_password);
        mEtPhoneNumber = (EditText) findViewById(R.id.et_phone_number);
        mEtMessageCode = (EditText) findViewById(R.id.et_message_code);
        mBtnGetCode = (Button) findViewById(R.id.btn_message_code);

        mBtnGetCode.setOnClickListener(this);
        mToolbar.setTitle("手机绑定");
        setSupportActionBar(mToolbar);


        initData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_message_code:
                if (isPasswordValid() && isPhoneNumberValid()) {
                    mTimer.start();
                    mPresenter.getMessageCode();
                }
                break;
            case R.id.btn_commit:
                if (isPasswordValid() && isPhoneNumberValid() && isMessageCodeValid()) {
                    mDialogDelegate.showProgressDialog(false, getResources().getString(R.string._commit_ing));
                    mPresenter.commit();
                }
                break;
        }

    }

    @Override
    public String getSafePassword() {

        return mEtSafePassword.getText().toString();
    }

    @Override
    public String getPhoneNumber() {

        return mEtPhoneNumber.getText().toString();
    }

    @Override
    public String getMessageCode() {

        return mEtMessageCode.getText().toString();
    }

    private boolean isPasswordValid() {
        String safePassword = mEtSafePassword.getText().toString();
        if (safePassword.isEmpty()) {
            T.showShort(this, R.string.safe_password_not_be_null);
            return false;
        }
        return true;
    }

    private boolean isPhoneNumberValid() {
        String phoneNumber = mEtPhoneNumber.getText().toString();
        if (phoneNumber.isEmpty()) {
            T.showShort(this, R.string.phone_number_not_be_null);
            return false;
        } else if (!StringUtils.checkPhone(phoneNumber)) {
            T.showShort(this, R.string.please_input_correct_phone_number);
            return false;
        }

        return true;
    }

    private boolean isMessageCodeValid() {
        String messageCode = mEtMessageCode.getText().toString();
        if (messageCode.isEmpty()) {
            T.showShort(this, R.string.message_code_not_be_null);
            return false;
        }
        return true;
    }

    @Override
    public void showProgressError(String stateCode, String msg) {
        mDialogDelegate.stopProgressWithFailed(msg,msg);
    }

    @Override
    public void showDialogError(String option, String msg) {
        mDialogDelegate.showErrorDialog(option, msg);
    }

    @Override
    public void showSucceed() {
        mDialogDelegate.stopProgressWithSuccess("手机绑定", getResources().getString(R.string.bind_success), new DialogDelegate.OnDialogListener() {
            @Override
            public void onClick() {
                BindPhoneActivity.this.finish();
            }
        });
    }
}
