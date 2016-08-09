package com.qy.business.main.purchase.product.detail;

import com.qy.business.bean.GoodsDetail;

/**
 * Created by zhangyu on 2016/8/8.
 */
public class ProductDetailModel implements ProductDetailContract.Model {
    @Override
    public void addToShoppingCart(String userName, String userPassword, String imei, String goodsId, String buyattr, String sku, int num) {

    }

    @Override
    public void share(GoodsDetail goods, int type) {

    }

    @Override
    public void collect(GoodsDetail goods, boolean isCollect) {

    }
}
