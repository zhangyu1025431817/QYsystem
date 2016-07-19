package com.qy.business.main.main;

import android.graphics.drawable.Drawable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qy.business.R;
import com.qy.business.main.base.BaseActivity;
import com.qy.business.main.home.HomeFragment;
import com.qy.business.main.purchase.PurchaseFragment;
import com.qy.business.main.sell.SellFragment;
import com.qy.business.main.service.ServiceFragment;

import butterknife.Bind;

public class MainActivity extends BaseActivity<MainPresenter, MainModel> implements MainContract.View {
    @Bind(R.id.ll_title)
    Toolbar toolbar;
    @Bind(R.id.toolbar_title)
    TextView title;
    @Bind(R.id.viewpager)
    ViewPager mViewpager;
    @Bind(R.id.nv_main_navigation)
    NavigationView nvMainNavigation;
    @Bind(R.id.dl_main_drawer)
    DrawerLayout dlMainDrawer;
    @Bind(R.id.tabLayout)
    TabLayout mTabLayout;
    ImageView im_face;
    TextView tv_name;
    private int[] mTitles = new int[]{R.string.home_system, R.string.sell_system, R.string.purchase_system, R.string.qy_service};

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.icon_user);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        title.setText(mTitles[0]);

        initViewPager();

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, dlMainDrawer, R.string.drawer_open, R.string.drawer_closed);
        mDrawerToggle.syncState();
        dlMainDrawer.addDrawerListener(mDrawerToggle);

        View headerView = nvMainNavigation.inflateHeaderView(R.layout.nav_header_main);
        im_face = (ImageView) headerView.findViewById(R.id.im_face);
        tv_name = (TextView) headerView.findViewById(R.id.tv_name);


    }

    private void initViewPager() {
        mViewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new HomeFragment();
                    case 1:
                        return new SellFragment();
                    case 2:
                        return new PurchaseFragment();
                    case 3:
                        return new ServiceFragment();
                    default:
                        return new HomeFragment();

                }
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return getString(mTitles[position]);
            }

        });
        mTabLayout.setupWithViewPager(mViewpager);
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            Drawable d = null;
            switch (i) {
                case 0:
                    d = getResources().getDrawable(R.drawable.cb_home_icon);
                    break;
                case 1:
                    d = getResources().getDrawable(R.drawable.cb_sell_icon);
                    break;
                case 2:
                    d = getResources().getDrawable(R.drawable.cb_purchase_icon);
                    break;
                case 3:
                    d = getResources().getDrawable(R.drawable.cb_service_icon);
                    break;
            }
            tab.setIcon(d);
        }
    }

    @Override
    public void onBackPressed() {
        if (dlMainDrawer.isDrawerOpen(Gravity.LEFT)) dlMainDrawer.closeDrawers();
        else super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            dlMainDrawer.openDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgressError(String stateCode, String msg) {

    }

    @Override
    public void showDialogError(String option, String msg) {

    }

    @Override
    public void showSucceed() {

    }

}
