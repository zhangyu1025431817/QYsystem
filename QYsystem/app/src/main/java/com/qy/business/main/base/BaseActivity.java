package com.qy.business.main.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.qy.business.tools.TUtil;

import butterknife.ButterKnife;

/**
 * Created by zhangyu on 2016/3/11.
 */
public abstract class BaseActivity<T extends BasePresenter,M extends BaseModel> extends AppCompatActivity{

    protected T mPresenter;
    protected M mModel;
    public abstract int getLayoutId();
    public abstract void initView();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            // 透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            // 透明导航栏
////			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        //反射拿到Presenter实例
        mPresenter = TUtil.getT(this,0);
        mModel = TUtil.getT(this,1);
        if (this instanceof BaseView) mPresenter.setVM(this, mModel);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null) {
            mPresenter.onDestroy();
        }
        ButterKnife.unbind(this);
    }
}
