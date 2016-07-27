package com.qy.business.main.service;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
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
    protected ImageAdapter mAdapter;

    @Override
    public View getContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_qy_service, null);
    }

    @Override
    public void init() {
        if (isPrepared && isVisible) {
            mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.rb_remit_add_money:
                            showRemitAddMoney();
                            break;
                        case R.id.rb_add_money_online:
                            showAddMoneyOnline();
                            break;
                    }
                }
            });
            mRadioGroup.check(R.id.rb_remit_add_money);

        /*recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));*/
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
    }

    /**
     * 汇款加款
     */
    private void showRemitAddMoney() {
        mViewLeft.setVisibility(View.VISIBLE);
        mViewLeft.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_out));
        mViewRight.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_in));
        mViewRight.setVisibility(View.GONE);
    }

    /**
     * 在线加款
     */
    private void showAddMoneyOnline() {
        mViewRight.setVisibility(View.VISIBLE);
        mViewRight.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_out));
        mViewLeft.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_in));
        mViewLeft.setVisibility(View.GONE);

    }

    @Override
    public void showFunctionList(List<IconBean> list) {
        mAdapter.addAll(list);
    }
}
