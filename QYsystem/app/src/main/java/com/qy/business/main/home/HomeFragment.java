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
import com.qy.business.main.base.BaseFragment;
import com.qy.business.main.home.adapter.BannerAdapter;
import com.qy.business.main.home.adapter.DividerGridItemDecoration;
import com.qy.business.main.home.adapter.ImageAdapter;
import com.qy.business.main.home.bean.Ad;
import com.qy.business.main.home.bean.IconBean;
import com.qy.business.tools.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyu on 2016/7/18.
 */
public class HomeFragment extends BaseFragment implements HomeMvp.View {
    private EasyRecyclerView recyclerView;
    private ImageAdapter imageAdapter;
    private BannerAdapter mBannerAdapter;
    private List<Ad> mAdList = new ArrayList<>();
    private List<IconBean> mIconList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_system,null);
        recyclerView = (EasyRecyclerView) v.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(imageAdapter = new ImageAdapter(getActivity()));
        DividerGridItemDecoration dividerGridItemDecoration = new DividerGridItemDecoration(getActivity());
        recyclerView.setVerticalScrollBarEnabled(false);

        recyclerView.addItemDecoration(dividerGridItemDecoration);
        imageAdapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                RollPagerView header = new RollPagerView(getActivity());
                header.setHintView(new ColorPointHintView(getActivity(), Color.YELLOW,Color.GRAY));
                header.setHintPadding(0, 0, 0, DensityUtils.dp2px(getActivity(),8));
                header.setPlayDelay(2000);
                header.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtils.dp2px(getActivity(),200)));
                header.setAdapter(mBannerAdapter = new BannerAdapter(getActivity(),mAdList));
                return header;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });
        imageAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        imageAdapter.addAll(mIconList);

        return v;
    }

    @Override
    public void showAd(List<Ad> list) {
        mAdList.clear();
        mAdList.addAll(list);
        mBannerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showFunctionList(List<IconBean> list) {
        mIconList.clear();
        mIconList.addAll(list);
        imageAdapter.notifyDataSetChanged();
    }
}
