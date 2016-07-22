package com.qy.business.main.home;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.qy.business.main.home.bean.IconBean;
import com.qy.business.tools.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyu on 2016/7/18.
 */
public class HomeFragment extends Fragment {
    private EasyRecyclerView recyclerView;
    private ImageAdapter imageAdapter;
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
                header.setAdapter(new BannerAdapter(getActivity()));
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
        TypedArray iconArray = getResources().obtainTypedArray(R.array.system_icon);
        String[] iconName = getResources().getStringArray(R.array.system_icon_name);

        List<IconBean> list = new ArrayList<>();
        int len = iconArray.length();
        for(int i = 0;i<len;i++){
            IconBean bean = new IconBean();
            bean.setResId(iconArray.getResourceId(i,0));
            bean.setName(iconName[i]);
            list.add(bean);
        }
        iconArray.recycle();


        imageAdapter.addAll(list);

        return v;
    }
}
