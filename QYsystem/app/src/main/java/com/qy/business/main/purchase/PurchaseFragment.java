package com.qy.business.main.purchase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.qy.business.R;
import com.qy.business.bean.IconBean;
import com.qy.business.common.DividerGridItemDecoration;
import com.qy.business.common.IconAdapter;
import com.qy.business.main.base.BaseFragment;

import java.util.List;

import butterknife.Bind;

/**
 * Created by zhangyu on 2016/7/18.
 */
public class PurchaseFragment extends BaseFragment<PurchasePresenter, PurchaseModel> implements PurchaseContract.View {
    @Bind(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    protected IconAdapter mIconAdapter;

    @Override
    public View getContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_system, null);
    }

    @Override
    public void init() {
        if (isPrepared && isVisible) {


            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
            recyclerView.setAdapter(mIconAdapter = new IconAdapter(getActivity()));
            DividerGridItemDecoration dividerGridItemDecoration = new DividerGridItemDecoration(getActivity());
            recyclerView.setVerticalScrollBarEnabled(false);
            recyclerView.addItemDecoration(dividerGridItemDecoration);
            mIconAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {

                }
            });
            mPresenter.getFunctionIcons();
        }
    }

    @Override
    public void showFunctionList(List<IconBean> list) {
        mIconAdapter.addAll(list);
    }
}
