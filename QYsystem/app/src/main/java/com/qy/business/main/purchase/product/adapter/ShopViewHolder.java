package com.qy.business.main.purchase.product.adapter;

import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.qy.business.R;
import com.qy.business.bean.Shop;

/**
 * Created by zhangyu on 2016/8/1.
 */
public class ShopViewHolder extends BaseViewHolder<Shop> {
    ImageView image;
    TextView tv_name,tv_des,tv_contact;
    ImageButton btn_location,btn_phone;
    public ShopViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_shop);
        image = $(R.id.image);
        tv_name = $(R.id.tv_title);
        tv_des = $(R.id.tv_des);
        tv_contact = $(R.id.tv_contact);
        btn_location = $(R.id.btn_location);
        btn_phone = $(R.id.btn_phone);
    }

    @Override
    public void setData(Shop data) {
        Glide.with(getContext())
                .load(data.getShoplogo())
                .placeholder(R.drawable.default_bg)
                .into(image);
        tv_name.setText(data.getShop_name());
        tv_des.setText(data.getB_scope());
        tv_contact.setText(data.getShop_address());
    }
}
