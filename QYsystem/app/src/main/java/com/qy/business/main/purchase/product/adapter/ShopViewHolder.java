package com.qy.business.main.purchase.product.adapter;

import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.qy.business.R;
import com.qy.business.bean.Shop;

/**
 * Created by zhangyu on 2016/8/1.
 */
public class ShopViewHolder extends BaseViewHolder<Shop> {

    public ShopViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_shop);
    }

    @Override
    public void setData(Shop data) {
        super.setData(data);
    }
}
