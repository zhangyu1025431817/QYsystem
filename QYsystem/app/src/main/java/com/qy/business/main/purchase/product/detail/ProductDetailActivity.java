package com.qy.business.main.purchase.product.detail;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.qy.business.R;
import com.qy.business.bean.Ad;
import com.qy.business.local.DataProvider;
import com.qy.business.main.base.BaseActivity;
import com.qy.business.main.home.adapter.BannerAdapter;
import com.qy.business.main.purchase.product.pay.OrderPayActivity;
import com.qy.business.tools.DensityUtils;
import com.qy.business.view.AutoRollPagerView;
import com.qy.business.view.BadgeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by zhangyu on 2016/8/8.
 */
public class ProductDetailActivity extends BaseActivity<ProductDetailPresenter, ProductDetailModel> implements ProductDetailContract.View {
    @Bind(R.id.roll_pager_view)
    AutoRollPagerView rollPagerView;
    @Bind(R.id.btn_shopping_cart)
    ImageButton imageButtonShopCart;
    private BadgeView badgeView;
    private BannerAdapter mBannerAdapter;
    private List<Ad> mAdList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_product_detail;
    }

    /**
     * 返回
     */
    @OnClick(R.id.btn_return)
    public void back() {
        finish();
    }

    /**
     * 进入购物车
     */
    @OnClick(R.id.btn_shopping_cart)
    public void goToShoppingCart() {

    }

    /**
     * 联系卖家
     */
    @OnClick(R.id.tv_contact_saler)
    public void contactSaler() {

    }

    /**
     * 进入店铺
     */
    @OnClick(R.id.tv_into_shop)
    public void intoShop() {

    }

    /**
     * 立即购买
     */
    @OnClick({R.id.tv_buy, R.id.tv_bottom_buy})
    public void buyNow() {
        startActivity(new Intent(this, OrderPayActivity.class));
    }

    /**
     * 加入购物车
     */
    @OnClick({R.id.tv_add_to_cart, R.id.tv_bottom_add_to_cart})
    public void addToCart() {
        showDialog();
    }

    /**
     * 收藏
     */
    @OnClick({R.id.tv_collect, R.id.tv_bottom_collect})
    public void collect(View view) {

    }

    /**
     * 分享
     */
    @OnClick(R.id.tv_share)
    public void share() {

    }

    @Override
    public void initView() {
        rollPagerView.setHintView(new ColorPointHintView(this, Color.RED, Color.LTGRAY));
        rollPagerView.setHintPadding(0, 0, 0, DensityUtils.dp2px(this, 8));
        mAdList.addAll(DataProvider.getAdList());
        //   rollPagerView.setPlayDelay(2000);
        rollPagerView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 700));
        rollPagerView.setAdapter(mBannerAdapter = new BannerAdapter(this, mAdList, R.drawable.bg_hotport));

        badgeView = new BadgeView(this);
        badgeView.setTargetView(imageButtonShopCart);
        badgeView.setBadgeCount(2);
        badgeView.setBadgeMargin(5);
    }

    @Override
    public void collect(boolean isCollect) {

    }

    @Override
    public void addToShoppingCart(boolean isSucceed, String msg) {

    }

    @Override
    public void share(boolean isSucceed) {

    }

    private void showDialog() {
        MyDialogFragment dialogFragment = new MyDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "myDialog");
    }

    public class MyDialogFragment extends DialogFragment implements View.OnClickListener {
        private TextView tvCountNumber;
        private TextView tvPrice;
        private TextView tvTotalMoney;
        int mCount = 0;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
            View view = inflater.inflate(R.layout.view_dialog_shopping_cart, container);
            view.findViewById(R.id.iv_add_count).setOnClickListener(this);
            view.findViewById(R.id.iv_minus_count).setOnClickListener(this);
            view.findViewById(R.id.tv_add_to_cart).setOnClickListener(this);
            tvCountNumber = (TextView) view.findViewById(R.id.tv_count_number);
            tvPrice = (TextView) view.findViewById(R.id.tv_price);
            tvTotalMoney = (TextView) view.findViewById(R.id.tv_total_money);
            return view;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_add_count:
                    tvCountNumber.setText(++mCount + "");
                    tvTotalMoney.setText(Integer.parseInt(tvPrice.getText().toString().trim())*mCount+"元");
                    break;
                case R.id.iv_minus_count:
                    tvCountNumber.setText((mCount > 0 ? --mCount : 0) + "");
                    tvTotalMoney.setText(Integer.parseInt(tvPrice.getText().toString().trim())*mCount+"元");
                    break;
                case R.id.tv_add_to_cart:

                    this.dismiss();
                    break;
            }
        }
    }
}
