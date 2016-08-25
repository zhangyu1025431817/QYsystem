package com.qy.business.main.purchase.product;

import com.qy.business.bean.AddCartResult;
import com.qy.business.bean.Goods;
import com.qy.business.bean.Shop;
import com.qy.business.network.MySubscriber;
import com.qy.business.tools.SPUtils;

import java.util.List;

/**
 * Created by zhangyu on 2016/7/28.
 */
public class ProductPurchasePresenter extends ProductPurchaseContract.Presenter{
    @Override
    public void onStart() {

    }

    @Override
    public void getShopList(int page, int limit,String spId, String cid, String brandId, String areaId, String keyword) {
        mRxManager.add(mModel.getShopList(page,limit,spId,cid,brandId,areaId,keyword).subscribe(new MySubscriber<List<Shop>>(){
            @Override
            public void onNext(List<Shop> list) {
                mView.showShopList(list);
            }

            @Override
            public void onError(Throwable e) {
                mView.showShopList(null);
            }
        }));
    }

    @Override
    public void getGoodsList(int page, int limit,String spId, String cid, String brandId, String areaId, String keyword) {
        mRxManager.add(mModel.getGoodsList(page,limit,spId,cid,brandId,areaId,keyword).subscribe(new MySubscriber<List<Goods>>(){
            @Override
            public void onNext(List<Goods> list) {
                mView.showGoodsList(list);
            }

            @Override
            public void onError(Throwable e) {
                mView.showGoodsList(null);
            }
        }));
    }

    @Override
    public void addToCart(String id, String sku, int number) {
        mRxManager.add(mModel.addToShoppingCart((String) SPUtils.get(context,"username_aes",""),
                (String) SPUtils.get(context,"password",""),
                (String) SPUtils.get(context,"imei",""),id,sku,number).subscribe(new MySubscriber<AddCartResult>(){
            @Override
            public void onNext(AddCartResult addCartResult) {
                mView.showCount(addCartResult);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                e.printStackTrace();
            }
        }));
    }
}
