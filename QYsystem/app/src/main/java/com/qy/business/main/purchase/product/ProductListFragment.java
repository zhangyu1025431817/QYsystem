package com.qy.business.main.purchase.product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.qy.business.R;
import com.qy.business.bean.Goods;
import com.qy.business.bean.Shop;
import com.qy.business.main.base.BaseFragment;
import com.qy.business.main.purchase.product.adapter.GoodsAdapter;
import com.qy.business.main.purchase.product.adapter.ShopAdapter;

import java.util.List;

/**
 * Created by zhangyu on 2016/8/1.
 */
public class ProductListFragment extends BaseFragment<ProductPurchasePresenter,ProductPurchaseModel> implements ProductPurchaseContract.View, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener{
    private EasyRecyclerView mRecyclerView;
    private GoodsAdapter mGoodsAdapter;
    private ShopAdapter mShopAdapter;
    private int page = 0;
    private String mCategoryId;
    public static ProductListFragment newInstance(String categoryId){
        Bundle arguments = new Bundle();
        arguments.putBoolean("lazyLoad",false);
        arguments.putString("categoryId",categoryId);
        ProductListFragment fragment = new ProductListFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public View getContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRecyclerView = new EasyRecyclerView(getActivity());
        mRecyclerView.setRefreshListener(this);
        return  mRecyclerView;
    }

    @Override
    public void init() {
        mCategoryId = getArguments().getString("categoryId","0");
        showGoods();
    }


    public void showGoods(){
        page = 0;
        if(mGoodsAdapter == null){
            mGoodsAdapter = new GoodsAdapter(getActivity());
            mGoodsAdapter.setMore(R.layout.view_more, this);
            mGoodsAdapter.setNoMore(R.layout.view_nomore);
            mGoodsAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {

                }
            });
        }
        mRecyclerView.setAdapter(mGoodsAdapter);
        onRefresh();
    }
    public void showShop(){
        page =0;
        if(mShopAdapter == null){
            mShopAdapter = new ShopAdapter(getActivity());
            mShopAdapter.setMore(R.layout.view_more, this);
            mShopAdapter.setNoMore(R.layout.view_nomore);
            mShopAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {

                }
            });
        }
        mRecyclerView.setAdapter(mShopAdapter);
    }
    @Override
    public void showShopList(List<Shop> list) {
        mShopAdapter.addAll(list);
    }

    @Override
    public void showGoodsList(List<Goods> list) {
        mGoodsAdapter.addAll(list);
    }

    @Override
    public void onLoadMore() {
        if(mRecyclerView.getAdapter() == mGoodsAdapter) {
            mPresenter.getGoodsList(page,20,mCategoryId,"","","","");
        }else {
            mPresenter.getShopList(page,20,mCategoryId,"","","","");
        }
        page++;
    }

    @Override
    public void onRefresh() {
        page = 0;
        if(mRecyclerView.getAdapter() == mGoodsAdapter) {
            mPresenter.getGoodsList(page,20,mCategoryId,"","","","");
        }else {
            mPresenter.getShopList(page,20,mCategoryId,"","","","");
        }
        page = 1;
    }
}
