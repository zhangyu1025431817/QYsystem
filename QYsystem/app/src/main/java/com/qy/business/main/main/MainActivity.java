package com.qy.business.main.main;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.qy.business.R;
import com.qy.business.main.base.BaseActivity;

import butterknife.Bind;

public class MainActivity extends BaseActivity<MainPresenter, MainModel> implements MainContract.View,RadioGroup.OnCheckedChangeListener {
    @Bind(R.id.ll_title)
    Toolbar toolbar;
    @Bind(R.id.toolbar_title)
    TextView title;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.nv_main_navigation)
    NavigationView nvMainNavigation;
    @Bind(R.id.dl_main_drawer)
    DrawerLayout dlMainDrawer;
    @Bind(R.id.rg_nav_bottom)
    RadioGroup radioGroup;
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
        radioGroup.check(R.id.rb_home);
        radioGroup.setOnCheckedChangeListener(this);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, dlMainDrawer, R.string.drawer_open, R.string.drawer_closed);
        mDrawerToggle.syncState();
        dlMainDrawer.addDrawerListener(mDrawerToggle);

        View headerView = nvMainNavigation.inflateHeaderView(R.layout.nav_header_main);
        im_face = (ImageView) headerView.findViewById(R.id.im_face);
        tv_name = (TextView) headerView.findViewById(R.id.tv_name);


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

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_home:
                break;
            case R.id.rb_sell:
                break;
            case R.id.rb_purchase:
                break;
            case R.id.rb_service:
                break;
        }
    }
}
