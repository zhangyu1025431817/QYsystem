package com.qy.business.main.service;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.qy.business.R;
import com.qy.business.bean.IconBean;
import com.qy.business.main.base.BaseFragment;
import com.qy.business.main.service.adapter.ImageAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by zhangyu on 2016/7/18.
 */
public class ServiceFragment extends BaseFragment<ServicePresenter, ServiceModel> implements ServiceContract.View {
    @Bind(R.id.layout_left)
    View mViewLeft;
    @Bind(R.id.layout_right)
    View mViewRight;
    @Bind(R.id.radio_group)
    RadioGroup mRadioGroup;
    @Bind(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    @Bind(R.id.rb_remit_add_money)
    RadioButton mRbRemitAddMoney;
    @Bind(R.id.rb_add_money_online)
    RadioButton mRbAddMoneyOnline;
    protected ImageAdapter mAdapter;

    @Override
    public View getContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_qy_service, null);
    }

    @OnClick(R.id.rb_remit_add_money)
    void onLeftClick(View v){
        showRemitAddMoney();
    }
    @OnClick(R.id.rb_add_money_online)
    void onRightClick(View v){
        showAddMoneyOnline();
    }
    @Override
    public void init() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(mAdapter = new ImageAdapter(getActivity()));
        SpaceDecoration decoration = new SpaceDecoration(2);
        recyclerView.setVerticalScrollBarEnabled(false);
        recyclerView.addItemDecoration(decoration);
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        mPresenter.getFunctionIcons();
    }



    /**
     * 汇款加款
     */
    private void showRemitAddMoney() {
        mViewLeft.setVisibility(View.VISIBLE);
        mViewLeft.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_out));
        mViewRight.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_in));
        mViewRight.setVisibility(View.INVISIBLE);
    }

    /**
     * 在线加款
     */
    private void showAddMoneyOnline() {
        mViewRight.setVisibility(View.VISIBLE);
        mViewRight.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_out));
        mViewLeft.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_in));
        mViewLeft.setVisibility(View.INVISIBLE);

    }

    @Override
    public void showFunctionList(List<IconBean> list) {
        mAdapter.addAll(list);
    }
}
