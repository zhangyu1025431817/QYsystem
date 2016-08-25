package com.qy.business.main.purchase.product.search.result;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.qy.business.R;
import com.qy.business.bean.AddCartResult;
import com.qy.business.bean.Goods;
import com.qy.business.bean.Shop;
import com.qy.business.main.base.BaseActivity;
import com.qy.business.main.purchase.product.ProductPurchaseContract;
import com.qy.business.main.purchase.product.adapter.GoodsAdapter;
import com.qy.business.main.purchase.product.adapter.ShopAdapter;
import com.qy.business.main.purchase.product.detail.ProductDetailActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by zhangyu on 2016/8/16.
 */
public class SearchResultActivity extends BaseActivity<SearchResultPresenter, SearchResultModel> implements ProductPurchaseContract.View, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.id_tool_bar)
    Toolbar toolbar;
    @Bind(R.id.tv_keyword)
    TextView textViewKeyword;
    @Bind(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    @Bind(R.id.tv_type)
    TextView tvType;
    private GoodsAdapter mGoodsAdapter;
    private ShopAdapter mShopAdapter;
    private int page = 0;
    private String mKeyword ;
    @Override
    public int getLayoutId() {
        return R.layout.activity_search_result;
    }

    @Override
    public void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setRefreshListener(this);
        Intent intent = getIntent();
        int currentType = intent.getIntExtra("type", 1);
        mKeyword = intent.getStringExtra("keyword");
        if (currentType == 1) {
            tvType.setText("商品");
            mGoodsAdapter = new GoodsAdapter(this);
            mGoodsAdapter.setMore(R.layout.view_more, this);
            mGoodsAdapter.setNoMore(R.layout.view_nomore);
            mGoodsAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Goods bean = mGoodsAdapter.getItem(position);
                    Intent intent = new Intent();
                    intent.putExtra("goodsId", bean.getGoods_id());
                    intent.putExtra("goodsName", bean.getGoods_name());
                    intent.setClass(SearchResultActivity.this, ProductDetailActivity.class);
                    startActivity(intent);
                }
            });
            recyclerView.setAdapterWithProgress(mGoodsAdapter);
        } else {
            tvType.setText("供应商");
            mShopAdapter = new ShopAdapter(this);
            mShopAdapter.setMore(R.layout.view_more, this);
            mShopAdapter.setNoMore(R.layout.view_nomore);
            mShopAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {

                }
            });
            recyclerView.setAdapter(mShopAdapter);

        }
        textViewKeyword.setText(mKeyword);
        onRefresh();
    }

    @OnClick(R.id.btn_return)
    public void onReturn(View view) {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_shopping_cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_cart:

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showShopList(List<Shop> list) {
        mShopAdapter.addAll(list);
        recyclerView.setRefreshing(false);
    }

    @Override
    public void showGoodsList(List<Goods> list) {
        mGoodsAdapter.addAll(list);
        recyclerView.setRefreshing(false);
    }

    @Override
    public void showCount(AddCartResult result) {

    }

    @Override
    public void onLoadMore() {
        if (recyclerView.getAdapter() == mGoodsAdapter) {
            mPresenter.getGoodsList(page, 20, "", "", "", "", mKeyword);
        } else {
            mPresenter.getShopList(page, 20, "", "", "", "", mKeyword);
        }
        page++;
    }

    @Override
    public void onRefresh() {
        recyclerView.setRefreshing(true);
        onLoadMore();
    }

    @OnClick(R.id.tv_keyword)
    public void onKeywordClick(){
        finish();
    }
}
