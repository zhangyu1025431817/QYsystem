package com.qy.business.main.login.bind;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.qy.business.R;
import com.qy.business.main.base.BaseActivity;
import com.qy.business.tools.StringUtils;
import com.qy.business.tools.T;
import com.qy.business.view.DialogDelegate;
import com.qy.business.view.SweetAlertDialogDelegate;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by zhangyu on 2016/5/12.
 */
public class BindPhoneActivity extends BaseActivity<BindPhonePresenter, BindPhoneModel> implements  BindPhoneContract.View {
    @Bind(R.id.et_phone_number)
     EditText editTextPhoneNumber;
    @Bind(R.id.et_message_code)
     EditText editTextMessageCode;
    @Bind(R.id.btn_message_code)
    TextView btnGetCode;
    @Bind(R.id.et_safe_password)
    EditText editTextSafePassword;
    @Bind(R.id.et_confirm_safe_password)
    EditText editTextConfirmSafePassword;
    private boolean isSafePwdShow = false;
    private boolean isSafePwdConfirmShow = false;
    private String mUserName;
    private String mPassword;

    private CountDownTimer mTimer;
    private int mCount = 60;
    private DialogDelegate mDialogDelegate;

    public void initData() {

        Intent intent = getIntent();
        mUserName = intent.getStringExtra("userName");
        mPassword = intent.getStringExtra("password");

        mDialogDelegate = new SweetAlertDialogDelegate(this);
        mTimer = new CountDownTimer(1000 * 60, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                btnGetCode.setEnabled(false);
                btnGetCode.setText(mCount + getString(R.string.get_again));
                mCount--;
            }

            @Override
            public void onFinish() {
                btnGetCode.setText(getString(R.string.get_authentication_code));
                btnGetCode.setEnabled(true);
                mCount = 60;
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
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
        initData();
    }
    @OnClick(R.id.btn_message_code)
    public void getMsgCode(){
        if (isPhoneNumberValid()) {
            mTimer.start();
            mPresenter.getMessageCode();
        }
    }
    @OnClick(R.id.btn_commit)
    public void commit(){
        if (isPhoneNumberValid() && isMessageCodeValid()&&isSafePasswordValid()) {
            mDialogDelegate.showProgressDialog(false, getResources().getString(R.string._commit_ing));
            mPresenter.commit();
        }
    }
    @OnClick(R.id.btn_return)
    public void onReturn(){
        finish();
    }
    @OnClick(R.id.iv_safe_password_show)
    public void showSafePassword(){
        if(!isSafePwdConfirmShow){
            //如果选中，显示密码
            editTextSafePassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }else{
            //否则隐藏密码
            editTextSafePassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        isSafePwdConfirmShow= !isSafePwdConfirmShow;
    }
    @OnClick(R.id.iv_safe_confirm_password_show)
    public void showSafePasswordConfirm(){
        if(!isSafePwdShow){
            //如果选中，显示密码
            editTextConfirmSafePassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }else{
            //否则隐藏密码
            editTextConfirmSafePassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        isSafePwdShow = !isSafePwdShow;
    }

    @Override
    public String getUserName() {
        return mUserName;
    }

    @Override
    public String getPassword() {
        return mPassword;
    }

    @Override
    public String getSafePassword() {
        return editTextSafePassword.getText().toString().trim();
    }

    @Override
    public String getPhoneNumber() {

        return editTextPhoneNumber.getText().toString();
    }

    @Override
    public String getMessageCode() {

        return editTextMessageCode.getText().toString();
    }

    private boolean isPhoneNumberValid() {
        String phoneNumber = editTextPhoneNumber.getText().toString();
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
        String messageCode = editTextMessageCode.getText().toString();
        if (messageCode.isEmpty()) {
            T.showShort(this, R.string.message_code_not_be_null);
            return false;
        }
        return true;
    }

    private boolean isSafePasswordValid(){
        String safePassword = editTextSafePassword.getText().toString().trim();
        String confirmPassword = editTextConfirmSafePassword.getText().toString().trim();
        if(safePassword.isEmpty()){
            T.showShort(this, R.string.safe_password_not_be_null);
            return false;
        }
        if(safePassword.length() < 6){
            T.showShort(this, R.string.safe_password_not_less_than_six);
            return false;
        }
        if(!safePassword.equals(confirmPassword)){
            T.showShort(this, R.string.safe_password_confirm_not_same);
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
        mDialogDelegate.stopProgressWithSuccess("安全验证", getResources().getString(R.string.bind_success), new DialogDelegate.OnDialogListener() {
            @Override
            public void onClick() {
                mDialogDelegate.clearDialog();
                BindPhoneActivity.this.finish();
            }
        });
    }
}
