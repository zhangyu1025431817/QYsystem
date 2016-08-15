package com.qy.business.main.purchase.product;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.qy.business.R;
import com.qy.business.bean.ProductCategory;
import com.qy.business.local.DataProvider;
import com.qy.business.main.purchase.product.adapter.FragmentAdapter;
import com.qy.business.main.purchase.product.adapter.RecyclerListAdapter;
import com.qy.business.main.purchase.product.helper.OnStartDragListener;
import com.qy.business.main.purchase.product.helper.SimpleItemTouchHelperCallback;
import com.qy.business.main.purchase.product.search.SearchActivity;
import com.qy.business.tools.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhangyu on 2016/7/28.
 */
public class ProductPurchaseActivity extends AppCompatActivity implements OnStartDragListener {
    private static final int SHOP = 0;
    private static final int GOODS = 1;
    @Bind(R.id.id_tool_bar)
    Toolbar toolbar;
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.tv_type)
    TextView tvType;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.tv_keyword)
    TextView textViewKeyword;
    private int mCurrentType = GOODS;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private ItemTouchHelper mItemTouchHelper;
    private List<ProductCategory> mCategoryList = new ArrayList<>();
    private RecyclerListAdapter mRecyclerListAdapter;
    private boolean isDrag = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_purchase);
        ButterKnife.bind(this);
        initView();
    }

    public void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        List<ProductCategory> list = (List<ProductCategory>) SPUtils.getObject(this, "category");
        if (list == null) {
            mCategoryList = DataProvider.productCategoryList();
        } else {
            mCategoryList.addAll(list);
        }
        //初始化分类
        setCategories();
        //初始化种类下拉列表
        initDropList();

    }

    private void setCategories() {
        mFragmentList.clear();
        for (ProductCategory bean : mCategoryList) {
            mFragmentList.add(ProductListFragment.newInstance(bean.getSupplygcate_id(),mCurrentType));
        }
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), mFragmentList, mCategoryList));
        tabs.setupWithViewPager(viewPager);
    }

    private void initDropList() {
        mRecyclerListAdapter = new RecyclerListAdapter(this, mCategoryList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mRecyclerListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mRecyclerListAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
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

    @OnClick(R.id.btn_return)
    public void onReturn(View view) {
        finish();
    }

    @OnClick(R.id.tv_type)
    public void checkType() {
        if (mCurrentType == SHOP) {
            tvType.setText("商品");
            mCurrentType = GOODS;
        } else {
            tvType.setText("店铺");
            mCurrentType = SHOP;
        }
        for (Fragment f : mFragmentList) {
            ((ProductListFragment) f).changeType(mCurrentType);
        }
    }

    @OnClick(R.id.btn_type_more)
    public void showMoreType() {
        if (recyclerView.getVisibility() == View.VISIBLE) {
            recyclerView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.hide));
            recyclerView.setVisibility(View.INVISIBLE);
            saveSort();
        } else if (recyclerView.getVisibility() == View.INVISIBLE) {
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.shown));
        }
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
        isDrag = true;

    }

    private void saveSort() {
        //拖动过后才修改
        if (isDrag) {
            isDrag = false;
            mCategoryList.clear();
            mCategoryList.addAll(mRecyclerListAdapter.getmItems());
            setCategories();
            SPUtils.putObject(this, "category", mCategoryList);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {// 按下的如果是BACK，同时没有重复
            if (recyclerView.getVisibility() == View.VISIBLE) {
                recyclerView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.hide));
                recyclerView.setVisibility(View.INVISIBLE);
                saveSort();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @OnClick(R.id.tv_keyword)
    public void onKeywordClick(){
        startActivity(new Intent(this, SearchActivity.class));
    }
}
