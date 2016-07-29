package com.qy.business.main.service.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.qy.business.R;
import com.qy.business.bean.IconBean;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by zhangyu on 2016/7/27.
 */
public class ImageViewHolder extends BaseViewHolder<IconBean> {
    private ImageView mImageView;

    public ImageViewHolder(ViewGroup parent) {
        super(parent, R.layout.view_image);
        AutoUtils.auto(itemView);
        mImageView = $(R.id.image);
    }

    @Override
    public void setData(IconBean data) {
        mImageView.setImageResource(data.getResId());
    }
}
