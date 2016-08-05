package com.qy.business.main.purchase.product.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.qy.business.bean.ProductCategory;

import java.util.List;

/**
 * Created by zhangyu on 16/8/1.
 */
public class FragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments;
    private List<ProductCategory> mCategoryList;

    public FragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<ProductCategory> categoryList) {
        super(fm);
        mFragments = fragments;
        mCategoryList = categoryList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mCategoryList.get(position).getSupplygcate_name();
    }
}