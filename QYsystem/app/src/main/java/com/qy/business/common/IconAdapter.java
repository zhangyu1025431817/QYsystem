package com.qy.business.common;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.qy.business.bean.IconBean;

/**
 * Created by zhangyu on 2016/7/20.
 */
public class IconAdapter extends RecyclerArrayAdapter<IconBean> {
    private Context mContext;

    public IconAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new IconViewHolder(parent,mContext);
    }
}
