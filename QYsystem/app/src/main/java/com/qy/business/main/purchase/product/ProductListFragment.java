package com.qy.business.main.purchase.product;

import android.content.Intent;
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
import com.qy.business.bean.AddCartResult;
import com.qy.business.bean.Goods;
import com.qy.business.bean.Shop;
import com.qy.business.bean.SkuData;
import com.qy.business.main.base.BaseFragment;
import com.qy.business.main.base.RxBus;
import com.qy.business.main.purchase.product.adapter.GoodsAdapter;
import com.qy.business.main.purchase.product.adapter.ShopAdapter;
import com.qy.business.main.purchase.product.detail.ProductDetailActivity;

import java.util.List;

import rx.functions.Action1;

/**
 * Created by zhangyu on 2016/8/1.
 */
public class ProductListFragment extends BaseFragment<ProductPurchasePresenter, ProductPurchaseModel> implements ProductPurchaseContract.View, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    public static final String EVENT_BUS_TAG = "result";
    private EasyRecyclerView mRecyclerView;
    private GoodsAdapter mGoodsAdapter;
    private ShopAdapter mShopAdapter;
    private int page = 0;
    private String mCategoryId;
    private int mCurrentType = 1;

    public static ProductListFragment newInstance(String categoryId, int type) {
        ProductListFragment fragment = new ProductListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("categoryId", categoryId);
        bundle.putInt("type", type);
        bundle.putBoolean("lazyLoad", false);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View getContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRecyclerView = new EasyRecyclerView(getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setRefreshListener(this);
        return mRecyclerView;
    }

    @Override
    public void init() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.containsKey("categoryId")) {
                mCategoryId = bundle.getString("categoryId");
            }
            if (bundle.containsKey("type")) {
                mCurrentType = bundle.getInt("type");
            }
        }
        if (mCurrentType == 0) {
            showShop();
        } else {
            showGoods();
        }
        //注册添加到购物车事件 该事件由fragment里面的adapter发送
        RxBus.$().register(EVENT_BUS_TAG).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                if(o instanceof Goods){
                    Goods goods = (Goods)o;
                    List<SkuData> skuList = goods.getSku_list();
                    String sku = "";
                    if(skuList != null && !skuList.isEmpty()){
                    }
                  //  mPresenter.addToCart(goods.getGoods_id(),skuList == null? "",);
                }
            }
        });
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
                Goods bean = mGoodsAdapter.getItem(position);
                Intent intent = new Intent();
                intent.putExtra("goodsId",bean.getGoods_id());
                intent.putExtra("goodsName",bean.getGoods_name());
                intent.setClass(getActivity(), ProductDetailActivity.class);
                startActivity(intent);
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
    public void showCount(AddCartResult result) {

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
//        if (mRecyclerView.getAdapter() == mGoodsAdapter) {
//            mPresenter.getGoodsList(page, 20, mCategoryId, "", "", "", "");
//        } else {
//            mPresenter.getShopList(page, 20, mCategoryId, "", "", "", "");
//        }
//        page = 1;
        onLoadMore();
    }

    /**
     * when the type changed
     *
     * @param type 0-shop , 1-goods
     */
    public void changeType(int type) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.containsKey("type")) {
                bundle.putInt("type", type);
            }
        }
        //如果当前界面正在显示
        if (isVisible()) {
            if (type == 0) {
                showShop();
            } else {
                showGoods();
            }
        } else {
            setArguments(bundle);
        }

    }
}
