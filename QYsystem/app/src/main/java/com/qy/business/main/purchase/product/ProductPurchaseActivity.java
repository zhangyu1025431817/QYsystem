package com.qy.business.main.purchase.product;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.qy.business.R;
import com.qy.business.main.base.BaseActivity;

import butterknife.Bind;

/**
 * Created by zhangyu on 2016/7/28.
 */
public class ProductPurchaseActivity extends BaseActivity<ProductPurchasePresenter,ProductPurchaseModel> implements ProductPurchaseContract.View{
    @Bind(R.id.id_tool_bar)
    Toolbar mToolbar;
    @Override
    public int getLayoutId() {
        return R.layout.activity_product_purchase;
    }

    @Override
    public void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
      //  mToolbar.setNavigationIcon(R.drawable.icon_back_home_gray);


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
