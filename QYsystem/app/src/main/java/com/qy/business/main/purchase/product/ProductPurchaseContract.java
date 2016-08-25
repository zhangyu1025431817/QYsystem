package com.qy.business.main.purchase.product;

import com.qy.business.bean.AddCartResult;
import com.qy.business.bean.Goods;
import com.qy.business.bean.Shop;
import com.qy.business.main.base.BaseModel;
import com.qy.business.main.base.BasePresenter;
import com.qy.business.main.base.BaseView;

import java.util.List;

import rx.Observable;

/**
 * Created by zhangyu on 2016/7/28.
 */
public interface ProductPurchaseContract {
     interface Model extends BaseModel{
        Observable<List<Shop>> getShopList(int page, int limit, String spId, String cid, String brandId, String areaId, String keyword);
        Observable<List<Goods>> getGoodsList(int page, int limit, String spId, String cid, String brandId, String areaId, String keyword);
        Observable<AddCartResult> addToShoppingCart(String name,String password,String imei,String id,String sku,int number);
    }
     interface View extends BaseView{
        void showShopList(List<Shop> list);
        void showGoodsList(List<Goods> list);
        void showCount(AddCartResult result);
    }
    abstract class Presenter extends BasePresenter<Model,View>{
        abstract void getShopList(int page, int limit,String spId, String cid, String brandId, String areaId, String keyword);
        abstract void getGoodsList(int page, int limit,String spId, String cid, String brandId, String areaId, String keyword);
        abstract void addToCart(String id,String sku,int number);
    }
}
