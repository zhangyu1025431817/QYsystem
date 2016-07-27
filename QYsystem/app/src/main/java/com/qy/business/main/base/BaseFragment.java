package com.qy.business.main.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qy.business.tools.TUtil;

import butterknife.ButterKnife;

/**
 * Created by zhangyu on 2016/7/25.
 */
public abstract class BaseFragment<T extends BasePresenter,M extends BaseModel> extends Fragment {
    protected T mPresenter;
    protected M mModel;
    /** Fragment当前状态是否可见 */
    protected boolean isVisible;
    protected boolean isPrepared;
    public abstract View getContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);
    public abstract void init();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = TUtil.getT(this,0);
        mModel = TUtil.getT(this,1);
        if (this instanceof BaseView) mPresenter.setVM(this, mModel);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getContentView(inflater,container,savedInstanceState);
        ButterKnife.bind(this,view);
        isPrepared = true;
        init();
        return view;
    }


    /**
     * 懒加载
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }
    /**
     * 可见
     */
    protected void onVisible() {
        init();
    }


    /**
     * 不可见
     */
    protected void onInvisible() {


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isPrepared = false;
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mPresenter != null) {
            mPresenter.onDestroy();
        }
    }
}
