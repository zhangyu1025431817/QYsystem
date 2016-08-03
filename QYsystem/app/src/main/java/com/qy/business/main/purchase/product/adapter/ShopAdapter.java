package com.qy.business.main.purchase.product.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.qy.business.bean.Shop;

/**
 * Created by zhangyu on 2016/8/1.
 */
public class ShopAdapter extends RecyclerArrayAdapter<Shop> {
    public ShopAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShopViewHolder(parent);
    }
}
