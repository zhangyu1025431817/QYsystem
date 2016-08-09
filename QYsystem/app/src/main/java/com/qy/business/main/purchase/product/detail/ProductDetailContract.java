package com.qy.business.main.purchase.product.detail;

import com.qy.business.bean.GoodsDetail;
import com.qy.business.main.base.BaseModel;
import com.qy.business.main.base.BasePresenter;
import com.qy.business.main.base.BaseView;

/**
 * Created by zhangyu on 2016/8/8.
 */
public interface ProductDetailContract {
    interface Model extends BaseModel{
        void addToShoppingCart(String userName,String userPassword,String imei,String goodsId,String buyattr,String sku,int num);
        void share(GoodsDetail goods,int type);
        void collect(GoodsDetail goods,boolean isCollect);
    }
    interface View extends BaseView{
        void collect(boolean isCollect);
        void addToShoppingCart(boolean isSucceed,String msg);
        void share(boolean isSucceed);
    }
    abstract class Presenter extends BasePresenter<Model,View>{
        /**
         * 添加到购物车
         * @param goodsId 商品id
         * @param buyattr 购买属性
         * @param sku 销售属性
         * @param num 数量
         */
       abstract void addToShoppingCart(String goodsId,String buyattr,String sku,int num);

        /**
         * 收藏
         * @param goods
         * @param isCollect true收藏，false取消收藏
         */
        abstract void collect(GoodsDetail goods,boolean isCollect);

        /**
         * 分享
         * @param goods
         * @param type 分享类型 微信，qq。。。
         */
        abstract void share(GoodsDetail goods,int type);
    }
}
