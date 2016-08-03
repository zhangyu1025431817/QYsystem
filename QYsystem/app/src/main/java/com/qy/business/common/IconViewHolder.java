package com.qy.business.common;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.qy.business.R;
import com.qy.business.bean.IconBean;
import com.zhy.autolayout.attr.AutoAttr;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by zhangyu on 2016/7/20.
 */
public class IconViewHolder extends BaseViewHolder<IconBean> {
    private TextView mTvIcon;
    private Context mContext;
    public IconViewHolder(ViewGroup parent, Context context) {
        super(parent, R.layout.view_icon);
        AutoUtils.autoSize(itemView, AutoAttr.BASE_HEIGHT);
        mContext = context;
        mTvIcon = $(R.id.tv_icon);
    }

    @Override
    public void setData(IconBean data) {
        mTvIcon.setText(data.getName());
        Drawable drawable = mContext.getResources().getDrawable(data.getResId());
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        mTvIcon.setCompoundDrawables(null,drawable,null,null);
    }
}
