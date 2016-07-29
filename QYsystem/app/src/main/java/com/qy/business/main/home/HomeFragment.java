package com.qy.business.main.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.qy.business.R;
import com.qy.business.bean.Ad;
import com.qy.business.bean.IconBean;
import com.qy.business.main.base.BaseFragment;
import com.qy.business.common.DividerGridItemDecoration;
import com.qy.business.common.IconAdapter;
import com.qy.business.main.home.adapter.BannerAdapter;
import com.qy.business.tools.DensityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by zhangyu on 2016/7/18.
 */
public class HomeFragment extends BaseFragment<HomePresenter,HomeModel> implements HomeContract.View {
    @Bind(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    protected IconAdapter mIconAdapter;
    private BannerAdapter mBannerAdapter;
    private List<Ad> mAdList = new ArrayList<>();

    @Override
    public void showAd(List<Ad> list) {
        mAdList.clear();
        mAdList.addAll(list);
        mBannerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showFunctionList(List<IconBean> list) {
        mIconAdapter.addAll(list);
    }

    @Override
    public View getContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_system,null);
    }

    @Override
    public void init() {
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
            recyclerView.setAdapter(mIconAdapter = new IconAdapter(getActivity()));
            DividerGridItemDecoration dividerGridItemDecoration = new DividerGridItemDecoration(getActivity());
            recyclerView.setVerticalScrollBarEnabled(false);
            recyclerView.addItemDecoration(dividerGridItemDecoration);
            mIconAdapter.addHeader(new RecyclerArrayAdapter.ItemView() {
                @Override
                public View onCreateView(ViewGroup parent) {
                    RollPagerView header = new RollPagerView(getActivity());
                    header.setHintView(new ColorPointHintView(getActivity(), Color.YELLOW, Color.GRAY));
                    header.setHintPadding(0, 0, 0, DensityUtils.dp2px(getActivity(), 8));
                    header.setPlayDelay(2000);
                    header.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtils.dp2px(getActivity(), 200)));
                    header.setAdapter(mBannerAdapter = new BannerAdapter(getActivity(), mAdList));
                    return header;
                }

                @Override
                public void onBindView(View headerView) {

                }
            });
            mIconAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {

                }
            });
            mPresenter.getAd();
            mPresenter.getFunctionIcons();
        }

}
