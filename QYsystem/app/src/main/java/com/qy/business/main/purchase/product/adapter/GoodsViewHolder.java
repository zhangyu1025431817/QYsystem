package com.qy.business.main.purchase.product.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.qy.business.R;
import com.qy.business.bean.Goods;
import com.qy.business.main.base.RxBus;
import com.qy.business.main.purchase.product.ProductListFragment;

/**
 * Created by zhangyu on 2016/8/1.
 */
public class GoodsViewHolder extends BaseViewHolder<Goods> {
    ImageView image;
    TextView tv_title, tv_des, tv_price, tv_unit;
    ImageButton btn_shopping_cart;
    public GoodsViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_goods);
        image = $(R.id.image);
        tv_title = $(R.id.tv_title);
        tv_des = $(R.id.tv_des);
        tv_price = $(R.id.tv_price);
        tv_unit = $(R.id.tv_unit);
        btn_shopping_cart = $(R.id.btn_shopping_cart);
    }

    @Override
    public void setData(final Goods data) {
        Glide.with(getContext())
                .load(data.getTop_photos())
                .placeholder(R.drawable.default_bg)
                .into(image);
        tv_title.setText(data.getGoods_name());
        tv_price.setText(data.getGoods_price());
        tv_unit.setText("/"+data.getGoods_unit());
        tv_des.setText("暂无产品描述");
        btn_shopping_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBus.$().post(ProductListFragment.EVENT_BUS_TAG,data);
            }
        });
    }
}
