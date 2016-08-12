package com.qy.business.main.login.regist;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.qy.business.R;
import com.qy.business.main.base.BaseActivity;
import com.qy.business.bean.Region_all;
import com.qy.business.tools.StringUtils;
import com.qy.business.tools.T;
import com.qy.business.view.DialogDelegate;
import com.qy.business.view.SweetAlertDialogDelegate;

import java.util.List;

import butterknife.Bind;
import butterknife.OnItemSelected;

/**
 * Created by zhangyu on 2016/5/13.
 */
public class RegisterActivity extends BaseActivity<RegisterPresenter,RegisterModel> implements RegisterMvp.View{
    @Bind(R.id.et_user_name)
    EditText mEtName;
    @Bind(R.id.et_password)
    EditText mEtPwd;
    @Bind(R.id.et_confirm_password)
    EditText mEnsurePwd;
    @Bind(R.id.et_company_name)
    EditText mEtCompanyName;
    @Bind(R.id.et_legal_person_name)
    EditText mEtLegalPersonName;
    @Bind(R.id.et_contact_address)
    EditText mEtContactAddress;
    @Bind(R.id.sp_province)
    Spinner mSpProvince;
    @Bind(R.id.sp_city)
    Spinner mSpCity;
    @Bind(R.id.sp_area)
    Spinner mSpArea;
    @Bind(R.id.cb_register)
    CheckBox mCbRegister;
    @Bind(R.id.cb_supplier)
    CheckBox mCbSupply;
    @Bind(R.id.cb_shopkeeper)
    CheckBox mCbSale;
    @Bind(R.id.id_tool_bar)
    Toolbar mToolbar;
    String[] mProvinceNames, mCityNames, mAreaNames;
    String[] mProvinceIds, mCityIds, mAreaIds;
    private String mProvinceId, mCityId, mAreaId, mManageType;
    private DialogDelegate mDialogDelegate;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.icon_return);
        mDialogDelegate = new SweetAlertDialogDelegate(this);
        loadProvinceInfo();
    }

    @OnItemSelected(R.id.sp_province)
    void onProvinceItemSelected(int position) {
        mProvinceId = mProvinceIds[position];
        mPresenter.loadCityInfo(mProvinceId);
    }
    @OnItemSelected(R.id.sp_city)
    void onCityItemSelected(int position){
        mCityId = mCityIds[position];
        mPresenter.loadAreaInfo(mCityId);
    }
    @OnItemSelected(R.id.sp_area)
    void onAreaItemSelected(int position){
        mAreaId = mAreaIds[position];
    }

    private void loadProvinceInfo() {
        mPresenter.loadProvinceInfo();
    }

    @Override
    public void showProgressError(String stateCode, String msg) {
        mDialogDelegate.stopProgressWithFailed(msg,msg);
    }

    @Override
    public void showDialogError(String option, String msg) {

    }

    @Override
    public void showSucceed() {
        mDialogDelegate.stopProgressWithSuccess("注册成功", "注册成功", new DialogDelegate.OnDialogListener() {
            @Override
            public void onClick() {
                mDialogDelegate.clearDialog();
                finish();
            }
        });
    }


    @Override
    public void showProvince(List<Region_all> list) {
        mProvinceNames = new String[list.size()];
        mProvinceIds = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            mProvinceIds[i] = list.get(i).getRegion_id();
            mProvinceNames[i] = list.get(i).getRegion_name();
        }
        ArrayAdapter adapter = new ArrayAdapter<CharSequence>(RegisterActivity.this, R.layout.item_spinner, mProvinceNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// 下拉列表中每个条目的样式
        mSpProvince.setAdapter(adapter);

    }

    @Override
    public void showCity(List<Region_all> list) {
        mCityNames = new String[list.size()];
        mCityIds = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            mCityIds[i] = list.get(i).getRegion_id();
            mCityNames[i] = list.get(i).getRegion_name();
        }
        ArrayAdapter adapter = new ArrayAdapter<CharSequence>(RegisterActivity.this, R.layout.item_spinner, mCityNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// 下拉列表中每个条目的样式
        mSpCity.setAdapter(adapter);

    }

    @Override
    public void showArea(List<Region_all> list) {
        mAreaNames = new String[list.size()];
        mAreaIds = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            mAreaIds[i] = list.get(i).getRegion_id();
            mAreaNames[i] = list.get(i).getRegion_name();
        }
        ArrayAdapter adapter = new ArrayAdapter<CharSequence>(RegisterActivity.this, R.layout.item_spinner, mAreaNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// 下拉列表中每个条目的样式
        mSpArea.setAdapter(adapter);
    }

    public void agreement(View v) {

    }


    public void submit(View view) {
        String name = mEtName.getText().toString().trim();
        byte[] buff = name.getBytes();
        if (buff.length > 16 || !StringUtils.userNameValidation(name) || buff.length == 0 || buff.length < 4) {
            T.showShort(this, "用户名输入格式有误");
            return;
        }
        String password = mEtPwd.getText().toString().trim();
        if (password.isEmpty()) {
            T.showShort(this, "请输入密码!");
            return;
        } else if (password.length() < 6) {
            T.showShort(this, "请输入不少于六位数密码!");
            return;
        }
        String ensurePwd = mEnsurePwd.getText().toString().trim();
        if (ensurePwd.isEmpty()) {
            T.showShort(this, "请确认密码!");
            return;
        } else if (!password.equals(ensurePwd)) {
            T.showShort(this, "两次输入密码不同!");
            return;
        }
        String company = mEtCompanyName.getText().toString().trim();
        if (company.length() < 4) {
            T.showShort(this, "请输入正确公司名称!");
            return;
        }
        String manageAddress = "";

        String licenseNumber = "";

        String identityCard = "";

        String phoneNumber = "";
        String legalPerson = mEtLegalPersonName.getText().toString().trim();
        if (!StringUtils.chinesename(legalPerson)) {
            T.showShort(this, "请输入有效的法人姓名!");
            return;
        }

        String contactAddress = mEtContactAddress.getText().toString().trim();
        if (contactAddress.length() < 4) {
            T.showShort(this, "请输入有效的联系地址!");
            return;
        }
        String type = "1";
        if (mCbSupply.isChecked() && mCbSale.isChecked()) {
            type = "3";
        } else if (mCbSupply.isChecked() && !mCbSale.isChecked()) {
            type = "1";
        } else if (!mCbSupply.isChecked() && mCbSale.isChecked()) {
            type = "2";
        } else {
            T.showShort(this, "请选择经营类型!");
            return;
        }
        if (!mCbRegister.isChecked()) {
            T.showShort(this, "请先阅读并同意 奇易用户注册协议!");
            return;
        }

        mDialogDelegate.showProgressDialog(false,"正在注册");
        mPresenter.register(name, password, mProvinceId, mCityId, mAreaId, identityCard, legalPerson, phoneNumber, company, contactAddress, licenseNumber, type, "");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
