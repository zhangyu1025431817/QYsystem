package com.qy.business.main.purchase.product.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.qy.business.R;
import com.qy.business.bean.Shop;

/**
 * Created by zhangyu on 2016/8/1.
 */
public class ShopViewHolder extends BaseViewHolder<Shop> {
    ImageView image;
    public ShopViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_shop);
        image = $(R.id.image);
    }

    @Override
    public void setData(Shop data) {
        Glide.with(getContext())
                .load(data.getShoplogo())
                .placeholder(R.drawable.default_bg)
                .into(image);
    }
}
