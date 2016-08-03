package com.qy.business.main.purchase.product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.qy.business.R;
import com.qy.business.bean.ProductCategory;
import com.qy.business.local.DataProvider;
import com.qy.business.main.purchase.product.adapter.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhangyu on 2016/7/28.
 */
public class ProductPurchaseActivity extends AppCompatActivity {
    private static final int SHOP = 0;
    private static final int GOODS = 1;
    @Bind(R.id.id_tool_bar)
    Toolbar mToolbar;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.btn_return)
    ImageButton imageButton;
    @Bind(R.id.tv_type)
    TextView tvType;
    private int currentType = GOODS;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_purchase);
        ButterKnife.bind(this);
        initView();
    }

    public void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        List<ProductCategory> list = DataProvider.productCategoryList();
        List<String> titles = new ArrayList<>();
        for (ProductCategory bean : list) {
            titles.add(bean.getSupplygcate_name());
            fragmentList.add(ProductListFragment.newInstance(bean.getSupplygcate_id()));
        }
        mViewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragmentList, titles));
        tabs.setupWithViewPager(mViewPager);
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

        if (currentType == SHOP) {
            tvType.setText("商品");
            currentType = GOODS;
        } else {
            tvType.setText("店铺");
            currentType = SHOP;
        }

        for (Fragment f : fragmentList) {
            ((ProductListFragment) f).changeType(currentType);
        }
    }
}
