package com.qy.business.view;

import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import com.qy.business.tools.ScreenUtils;

import java.util.List;

/**
 * Created by zhangyu on 2016/5/10.
 */
public class ScrollerHorizontalLayout extends LinearLayout {
    /**
     * 一页显示的个数
     */
    private static final int SHOW_COUNT = 3;
    /**
     * 用于完成滚动操作的实例
     */
    private Scroller mScroller;

    /**
     * 判定为拖动的最小移动像素数
     */
    private int mTouchSlop;

    /**
     * 手机按下时的屏幕坐标
     */
    private float mXDown;

    /**
     * 手机当时所处的屏幕坐标
     */
    private float mXMove;

    /**
     * 上次触发ACTION_MOVE事件时的屏幕坐标
     */
    private float mXLastMove;

    /**
     * 界面可滚动的左边界
     */
    private int leftBorder;

    /**
     * 界面可滚动的右边界
     */
    private int rightBorder;


    public ScrollerHorizontalLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 第一步，创建Scroller的实例
        mScroller = new Scroller(context);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        // 获取TouchSlop值
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
    }

    public void insertViews(List<View> list) {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) getLayoutParams();
        /**
         * 屏幕宽度-margin-padding=屏幕内实际宽度
         */
        int width = (ScreenUtils.getScreenWidth(getContext())-lp.leftMargin-lp.rightMargin-getPaddingLeft()-getPaddingRight()) / SHOW_COUNT;
        int height = getHeight();
        for (View v : list) {
            //为了让添加的子view能居中显示，动态添加一个RelativeLayout
            RelativeLayout rl = new RelativeLayout(getContext());
            RelativeLayout.LayoutParams l = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
            l.addRule(RelativeLayout.CENTER_IN_PARENT);
            rl.addView(v,l);
            addView(rl, width, height);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            if (childCount == 0) {
                return;
            }
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                // 为ScrollerLayout中的每一个子控件在水平方向上进行布局
                childView.layout(i * childView.getMeasuredWidth(), 0, (i + 1) * childView.getMeasuredWidth(), childView.getMeasuredHeight());
            }
            /**
             *  初始化左右边界值
              */
            leftBorder = getChildAt(0).getLeft();
            rightBorder = getChildAt(getChildCount() - 1).getRight();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mXDown = ev.getRawX();
                mXLastMove = mXDown;
                break;
            case MotionEvent.ACTION_MOVE:
                /**
                 * 不会调用不知道为啥
                 */
                mXMove = ev.getRawX();
                float diff = Math.abs(mXMove - mXDown);
                mXLastMove = mXMove;
                // 当手指拖动值大于TouchSlop值时，认为应该进行滚动，拦截子控件的事件
                if (diff > mTouchSlop) {
                    return true;
                }
                break;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                /**
                 * 必须return true 要不然MotionEvent.ACTION_MOVE不会调用
                 */
                return true;
            case MotionEvent.ACTION_MOVE:
                /**
                 * 当前x坐标
                 */
                mXMove = event.getRawX();
                /**
                 * 手指滑动距离用于scrollBy的滑动
                 */
                int scrolledX = (int) (mXLastMove - mXMove);
                /**
                 * getScrollX();当前水平方向上移动的距离向左移动为正
                 */
//                if (getScrollX() + scrolledX < leftBorder) {
//                    scrollTo(leftBorder, 0);
//                    return true;
//                } else if (getScrollX() + getWidth() + scrolledX > rightBorder) {
//                    scrollTo(rightBorder - getWidth(), 0);
//                    return true;
//                }
                scrollBy(scrolledX, 0);
                mXLastMove = mXMove;
                break;
            case MotionEvent.ACTION_UP:
                // 当手指抬起时，根据当前的滚动值来判定应该滚动到哪个子控件的界面
                int targetIndex = (getScrollX() + getChildAt(0).getWidth() / 2) / getChildAt(0).getWidth();
                if (getScrollX() + (int) (mXLastMove - mXMove) < leftBorder) {
                    targetIndex = 0;
                }
                if (getScrollX() + getWidth() + (int) (mXLastMove - mXMove) > rightBorder) {
                    /**
                     * -3是为了让屏幕最后能显示3个Item
                     */
                    targetIndex = getChildCount()-3;
                }
                int dx = targetIndex * getChildAt(0).getWidth() - getScrollX();
                // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
                mScroller.startScroll(getScrollX(), 0, dx, 0);
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }
    @Override
    public void computeScroll() {
        // 第三步，重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}
