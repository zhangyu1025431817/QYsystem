package com.qy.business.main.purchase.product.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.qy.business.R;
import com.qy.business.bean.Goods;

/**
 * Created by zhangyu on 2016/8/1.
 */
public class GoodsViewHolder extends BaseViewHolder<Goods> {
    ImageView image;
    TextView tv_title, tv_des, tv_info, tv_time;
    public GoodsViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_goods);
        image = $(R.id.image);
    }

    @Override
    public void setData(Goods data) {
        Glide.with(getContext())
                .load(data.getTop_photos())
                .placeholder(R.drawable.default_bg)
                .into(image);

    }
}
