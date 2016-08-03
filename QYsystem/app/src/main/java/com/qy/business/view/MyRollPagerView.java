package com.qy.business.view;

import android.content.Context;
import android.util.AttributeSet;

import com.jude.rollviewpager.RollPagerView;
import com.zhy.autolayout.AutoRelativeLayout;
import com.zhy.autolayout.utils.AutoLayoutHelper;

/**
 * Created by zhangyu on 2016/8/3.
 */
public class MyRollPagerView extends RollPagerView {
    private final AutoLayoutHelper mHelper = new AutoLayoutHelper(this);
    public MyRollPagerView(Context context) {
        super(context);
    }

    public MyRollPagerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRollPagerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    public AutoRelativeLayout.LayoutParams generateLayoutParams(AttributeSet attrs)
    {
        return new AutoRelativeLayout.LayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        if (!isInEditMode())
        {
            mHelper.adjustChildren();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
