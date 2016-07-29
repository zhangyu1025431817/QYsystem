package com.qy.business.main.purchase.product;

import com.qy.business.R;
import com.qy.business.main.base.BaseActivity;

/**
 * Created by zhangyu on 2016/7/28.
 */
public class ProductPurchaseActivity extends BaseActivity<ProductPurchasePresenter,ProductPurchaseModel> implements ProductPurchaseContract.View{
    @Override
    public int getLayoutId() {
        return R.layout.activity_product_purchase;
    }

    @Override
    public void initView() {

    }
}
