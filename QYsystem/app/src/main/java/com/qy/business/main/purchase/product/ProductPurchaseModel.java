package com.qy.business.main.purchase.product;

import com.qy.business.bean.AddCartResult;
import com.qy.business.bean.Goods;
import com.qy.business.bean.GoodsBean;
import com.qy.business.bean.Shop;
import com.qy.business.bean.ShopBean;
import com.qy.business.network.NetWorkRequest;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by zhangyu on 2016/7/28.
 */
public class ProductPurchaseModel implements ProductPurchaseContract.Model {
    @Override
    public Observable<List<Shop>> getShopList(int page, int limit, String spId, String cid, String brandId, String areaId, String keyword) {
        return NetWorkRequest.getShopList(page,limit,spId,cid,brandId,areaId,keyword).map(new Func1<ShopBean, List<Shop>>() {
            @Override
            public List<Shop> call(ShopBean shopBean) {
                List<Shop> list = new ArrayList<>();
                try {
                    list.addAll(shopBean.getData().getList());
                }catch (Exception e){
                    e.printStackTrace();
                }
                return list;
            }
        });
    }

    @Override
    public Observable<List<Goods>> getGoodsList(int page, int limit, String spId, String cid, String brandId, String areaId, String keyword) {
        return NetWorkRequest.getGoodsList(page,limit,spId,cid,brandId,areaId,keyword).map(new Func1<GoodsBean, List<Goods>>() {
            @Override
            public List<Goods> call(GoodsBean goodsBean) {
                List<Goods> list = new ArrayList<>();
                try {
                    list.addAll(goodsBean.getData().getList());
                }catch (Exception e){
                    e.printStackTrace();
                }
                return list;
            }
        });
    }

    @Override
    public Observable<AddCartResult> addToShoppingCart(String name, String password, String imei, String id, String sku, int number) {
        return NetWorkRequest.addToShoppingCart(name,password,imei,id,sku,number);
    }
}
