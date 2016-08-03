package com.qy.business.main.purchase.product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
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
public class ProductListFragment extends BaseFragment<ProductPurchasePresenter, ProductPurchaseModel> implements ProductPurchaseContract.View, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    private EasyRecyclerView mRecyclerView;
    private GoodsAdapter mGoodsAdapter;
    private ShopAdapter mShopAdapter;
    private int page = 0;
    private String mCategoryId;
    private int mCurrentType = 1;

    public static ProductListFragment newInstance(String categoryId) {
        ProductListFragment fragment = new ProductListFragment();
        fragment.closeLazyLoad();
        fragment.setCategoryId(categoryId);
        return fragment;
    }

    @Override
    public View getContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRecyclerView = new EasyRecyclerView(getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setRefreshListener(this);
        return mRecyclerView;
    }

    public void setCategoryId(String id) {
        mCategoryId = id;
    }

    @Override
    public void init() {
        if (mCurrentType == 0) {
            showShop();
        } else {
            showGoods();
        }
    }

    private void showGoods() {
        page = 0;
        if (mGoodsAdapter != null) {
            mGoodsAdapter.removeAllHeader();
            mGoodsAdapter.removeAllFooter();
            mGoodsAdapter = null;
        }
        mGoodsAdapter = new GoodsAdapter(getActivity());
        mGoodsAdapter.setMore(R.layout.view_more, this);
        mGoodsAdapter.setNoMore(R.layout.view_nomore);
        mGoodsAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        mRecyclerView.setAdapterWithProgress(mGoodsAdapter);
        onRefresh();
    }

    private void showShop() {
        page = 0;
        if (mShopAdapter != null) {
            mShopAdapter.removeAllHeader();
            mShopAdapter.removeAllFooter();
            mShopAdapter = null;
        }
        mShopAdapter = new ShopAdapter(getActivity());
        mShopAdapter.setMore(R.layout.view_more, this);
        mShopAdapter.setNoMore(R.layout.view_nomore);
        mShopAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        mRecyclerView.setAdapter(mShopAdapter);
        onRefresh();
    }

    @Override
    public void showShopList(List<Shop> list) {
        mShopAdapter.addAll(list);
        mRecyclerView.setRefreshing(false);
    }

    @Override
    public void showGoodsList(List<Goods> list) {
        mGoodsAdapter.addAll(list);
        mRecyclerView.setRefreshing(false);
    }

    @Override
    public void onLoadMore() {
        if (mRecyclerView.getAdapter() == mGoodsAdapter) {
            mPresenter.getGoodsList(page, 20, mCategoryId, "", "", "", "");
        } else {
            mPresenter.getShopList(page, 20, mCategoryId, "", "", "", "");
        }
        page++;
    }

    @Override
    public void onRefresh() {
        mRecyclerView.setRefreshing(true);
        page = 0;
        if (mRecyclerView.getAdapter() == mGoodsAdapter) {
            mPresenter.getGoodsList(page, 20, mCategoryId, "", "", "", "");
        } else {
            mPresenter.getShopList(page, 20, mCategoryId, "", "", "", "");
        }
        page = 1;
    }

    public void changeType(int type) {
        mCurrentType = type;
        if (isVisible()) {
            if (type == 0) {
                showShop();
            } else {
                showGoods();
            }
        }
    }
}
