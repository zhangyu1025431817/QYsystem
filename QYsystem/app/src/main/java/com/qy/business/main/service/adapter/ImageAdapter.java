package com.qy.business.main.service.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.qy.business.bean.IconBean;

/**
 * Created by zhangyu on 2016/7/27.
 */
public class ImageAdapter extends RecyclerArrayAdapter<IconBean> {
    private Context mContext;

    public ImageAdapter(Context context) {
        super(context);
        mContext = context;
    }
    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(parent);
    }
}
