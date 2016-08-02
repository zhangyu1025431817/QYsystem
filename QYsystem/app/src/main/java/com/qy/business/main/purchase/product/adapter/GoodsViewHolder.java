package com.qy.business.main.purchase.product.adapter;

import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.qy.business.R;
import com.qy.business.bean.Goods;

/**
 * Created by zhangyu on 2016/8/1.
 */
public class GoodsViewHolder extends BaseViewHolder<Goods> {
    public GoodsViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_goods);
    }

    @Override
    public void setData(Goods data) {
        super.setData(data);
    }
}
