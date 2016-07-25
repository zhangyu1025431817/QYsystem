package com.qy.business.main.home;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.qy.business.R;
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
public class HomeFragment extends Fragment implements HomeMvp.View {
    public static final String TAG = "HomeFragment";
    private EasyRecyclerView recyclerView;
    private ImageAdapter imageAdapter;
    private BannerAdapter mBannerAdapter;
    private List<Ad> mAdList = new ArrayList<>();
    private List<IconBean> mIconList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
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
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(TAG, "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
    }
}
