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

import com.qy.business.R;
import com.qy.business.bean.ProductCategory;
import com.qy.business.local.DataProvider;
import com.qy.business.main.purchase.product.adapter.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by zhangyu on 2016/7/28.
 */
public class ProductPurchaseActivity extends AppCompatActivity{
    @Bind(R.id.id_tool_bar)
    Toolbar mToolbar;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;
    @Bind(R.id.tabs)
    TabLayout tabs;

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
        final List<Fragment> fragments = new ArrayList<>();
        List<ProductCategory> list = DataProvider.productCategoryList();
        List<String> ids = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for(ProductCategory bean : list){
            ids.add(bean.getSupplygcate_id());
            titles.add(bean.getSupplygcate_name());
        }
        Observable.from(ids).subscribe(new Action1<String>() {
            @Override
            public void call(String id) {
                fragments.add(ProductListFragment.newInstance(id));
            }
        });
        mViewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(),fragments,titles));
        tabs.setupWithViewPager(mViewPager);
        tabs.setTabsFromPagerAdapter(mViewPager.getAdapter());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shopping_cart, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle toolbar item clicks here. It'll
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_cart:
                // Open the search view on the menu item click.

                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
