package com.qy.business.bean;

import java.io.Serializable;
import java.util.LinkedList;

public class SupplierList_dbBean implements Serializable{
	
	/**
	 * 旧接口
	 */
	private String sup_name;//供应商名称
	private String contact;//联系人
	private String phone;//联系电话
	private String is_send;//是否送货
	private String user_id;//
	private String maintype_name;//主营业务
	
	/**
	 * 新接口、店铺
	 */
	private String user_name;
	private String supplycate_id;
	private String add_time;
	private String limit_areas;
	private String trade_type;
	private String delivery_type;
	private String create_type;
	private String create_hour;
	private String admin_user;
	private String is_recom;
	private String franchiser;
	private String user_pass;
	private String remark;
	private String orderby;
	private String supply_area;
	private String fax;
	private String area_id;
	private String shop_address;
	private String com_name;
	private String shop_x;
	private String shop_y;
	private String shoplogo;
	private String shop_name;
	private String shop_qq;
	private String user_code;
	private String supply_index_url;
	private String is_lingou;
	private String fav_num;
	private String open_time;
	private String b_scope;
	private String qq;
	private LinkedList<GoodsInfoBean> goods;
	private LinkedList<BrandInfoBean> brand_list;
	private String telephone;
	private double distance = 0;
	
	/**
	 * 新接口、商品
	 */
	private String goods_id;
	private String fc_id;
	private String fc_name;
	private String c_id;
	private String c_name;
	private String brand_id;
	private String brand_name;
	private String goods_type;
	private String goods_code;
	private String goods_name;
	private String goods_ad;
	private String goods_price;
	private String cost_price;
	private String sec_price;
	private String sec_start;
	private String sec_end;
	private String goods_status;
	private String goods_tags;
	private String goods_buyattr;
	private String freight;
	private String tid;
	private String top_photos;
	private String apppic;
	private String stock;
	private String warning;
	private String goods_unit;
	private String spec;
	private String onlinetime;
	private String nexttime;
	private String sale_num;
	private String sum_score;
	private String comment_count;
	private String fav_count;
	private String pro_id;
	private String city_id;
	private String isused;
	private String isproxy;
	private String is_top;
	private String is_postpaid;
	private String is_visit;
	private String is_change;
	private String is_expense;
	private String is_sale;
	private String is_book;
	private String search_attr;
	private String search_cid;
	private String attr_sku;
	private LinkedList<SkuData> sku_list;
	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getLimit_areas() {
		return limit_areas;
	}
	public void setLimit_areas(String limit_areas) {
		this.limit_areas = limit_areas;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getDelivery_type() {
		return delivery_type;
	}
	public void setDelivery_type(String delivery_type) {
		this.delivery_type = delivery_type;
	}
	public String getCreate_type() {
		return create_type;
	}
	public void setCreate_type(String create_type) {
		this.create_type = create_type;
	}
	public String getCreate_hour() {
		return create_hour;
	}
	public void setCreate_hour(String create_hour) {
		this.create_hour = create_hour;
	}
	public String getUser_pass() {
		return user_pass;
	}
	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOrderby() {
		return orderby;
	}
	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	public String getSupply_area() {
		return supply_area;
	}
	public void setSupply_area(String supply_area) {
		this.supply_area = supply_area;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getShop_x() {
		return shop_x;
	}
	public void setShop_x(String shop_x) {
		this.shop_x = shop_x;
	}
	public String getShop_y() {
		return shop_y;
	}
	public void setShop_y(String shop_y) {
		this.shop_y = shop_y;
	}
	public String getFav_num() {
		return fav_num;
	}
	public void setFav_num(String fav_num) {
		this.fav_num = fav_num;
	}
	public String getOpen_time() {
		return open_time;
	}
	public void setOpen_time(String open_time) {
		this.open_time = open_time;
	}
	public String getB_scope() {
		return b_scope;
	}
	public void setB_scope(String b_scope) {
		this.b_scope = b_scope;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public LinkedList<GoodsInfoBean> getGoods() {
		return goods;
	}
	public void setGoods(LinkedList<GoodsInfoBean> goods) {
		this.goods = goods;
	}
	public LinkedList<BrandInfoBean> getBrand_list() {
		return brand_list;
	}
	public void setBrand_list(LinkedList<BrandInfoBean> brand_list) {
		this.brand_list = brand_list;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getSupplycate_id() {
		return supplycate_id;
	}
	public void setSupplycate_id(String supplycate_id) {
		this.supplycate_id = supplycate_id;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getAdmin_user() {
		return admin_user;
	}
	public void setAdmin_user(String admin_user) {
		this.admin_user = admin_user;
	}
	public String getIs_recom() {
		return is_recom;
	}
	public void setIs_recom(String is_recom) {
		this.is_recom = is_recom;
	}
	public String getFranchiser() {
		return franchiser;
	}
	public void setFranchiser(String franchiser) {
		this.franchiser = franchiser;
	}
	public String getArea_id() {
		return area_id;
	}
	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}
	public String getShop_address() {
		return shop_address;
	}
	public void setShop_address(String shop_address) {
		this.shop_address = shop_address;
	}
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	public String getShoplogo() {
		return shoplogo;
	}
	public void setShoplogo(String shoplogo) {
		this.shoplogo = shoplogo;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getShop_qq() {
		return shop_qq;
	}
	public void setShop_qq(String shop_qq) {
		this.shop_qq = shop_qq;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public String getSupply_index_url() {
		return supply_index_url;
	}
	public void setSupply_index_url(String supply_index_url) {
		this.supply_index_url = supply_index_url;
	}
	public String getIs_lingou() {
		return is_lingou;
	}
	public void setIs_lingou(String is_lingou) {
		this.is_lingou = is_lingou;
	}
	//本地存储的数据
	private String issave;//是否有临时保存的未提交订单
	
	public String getSup_name() {
		return sup_name;
	}
	public void setSup_name(String sup_name) {
		this.sup_name = sup_name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIs_send() {
		return is_send;
	}
	public void setIs_send(String is_send) {
		this.is_send = is_send;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getMaintype_name() {
		return maintype_name;
	}
	public void setMaintype_name(String maintype_name) {
		this.maintype_name = maintype_name;
	}
	public String getIssave() {
		return issave;
	}
	public void setIssave(String issave) {
		this.issave = issave;
	}
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getFc_id() {
		return fc_id;
	}
	public void setFc_id(String fc_id) {
		this.fc_id = fc_id;
	}
	public String getFc_name() {
		return fc_name;
	}
	public void setFc_name(String fc_name) {
		this.fc_name = fc_name;
	}
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public String getGoods_type() {
		return goods_type;
	}
	public void setGoods_type(String goods_type) {
		this.goods_type = goods_type;
	}
	public String getGoods_code() {
		return goods_code;
	}
	public void setGoods_code(String goods_code) {
		this.goods_code = goods_code;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGoods_ad() {
		return goods_ad;
	}
	public void setGoods_ad(String goods_ad) {
		this.goods_ad = goods_ad;
	}
	public String getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(String goods_price) {
		this.goods_price = goods_price;
	}
	public String getCost_price() {
		return cost_price;
	}
	public void setCost_price(String cost_price) {
		this.cost_price = cost_price;
	}
	public String getSec_price() {
		return sec_price;
	}
	public void setSec_price(String sec_price) {
		this.sec_price = sec_price;
	}
	public String getSec_start() {
		return sec_start;
	}
	public void setSec_start(String sec_start) {
		this.sec_start = sec_start;
	}
	public String getSec_end() {
		return sec_end;
	}
	public void setSec_end(String sec_end) {
		this.sec_end = sec_end;
	}
	public String getGoods_status() {
		return goods_status;
	}
	public void setGoods_status(String goods_status) {
		this.goods_status = goods_status;
	}
	public String getGoods_tags() {
		return goods_tags;
	}
	public void setGoods_tags(String goods_tags) {
		this.goods_tags = goods_tags;
	}
	public String getGoods_buyattr() {
		return goods_buyattr;
	}
	public void setGoods_buyattr(String goods_buyattr) {
		this.goods_buyattr = goods_buyattr;
	}
	public String getFreight() {
		return freight;
	}
	public void setFreight(String freight) {
		this.freight = freight;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getTop_photos() {
		return top_photos;
	}
	public void setTop_photos(String top_photos) {
		this.top_photos = top_photos;
	}
	public String getApppic() {
		return apppic;
	}
	public void setApppic(String apppic) {
		this.apppic = apppic;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public String getWarning() {
		return warning;
	}
	public void setWarning(String warning) {
		this.warning = warning;
	}
	public String getGoods_unit() {
		return goods_unit;
	}
	public void setGoods_unit(String goods_unit) {
		this.goods_unit = goods_unit;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getOnlinetime() {
		return onlinetime;
	}
	public void setOnlinetime(String onlinetime) {
		this.onlinetime = onlinetime;
	}
	public String getNexttime() {
		return nexttime;
	}
	public void setNexttime(String nexttime) {
		this.nexttime = nexttime;
	}
	public String getSale_num() {
		return sale_num;
	}
	public void setSale_num(String sale_num) {
		this.sale_num = sale_num;
	}
	public String getSum_score() {
		return sum_score;
	}
	public void setSum_score(String sum_score) {
		this.sum_score = sum_score;
	}
	public String getComment_count() {
		return comment_count;
	}
	public void setComment_count(String comment_count) {
		this.comment_count = comment_count;
	}
	public String getFav_count() {
		return fav_count;
	}
	public void setFav_count(String fav_count) {
		this.fav_count = fav_count;
	}
	public String getPro_id() {
		return pro_id;
	}
	public void setPro_id(String pro_id) {
		this.pro_id = pro_id;
	}
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	public String getIsused() {
		return isused;
	}
	public void setIsused(String isused) {
		this.isused = isused;
	}
	public String getIsproxy() {
		return isproxy;
	}
	public void setIsproxy(String isproxy) {
		this.isproxy = isproxy;
	}
	public String getIs_top() {
		return is_top;
	}
	public void setIs_top(String is_top) {
		this.is_top = is_top;
	}
	public String getIs_postpaid() {
		return is_postpaid;
	}
	public void setIs_postpaid(String is_postpaid) {
		this.is_postpaid = is_postpaid;
	}
	public String getIs_visit() {
		return is_visit;
	}
	public void setIs_visit(String is_visit) {
		this.is_visit = is_visit;
	}
	public String getIs_change() {
		return is_change;
	}
	public void setIs_change(String is_change) {
		this.is_change = is_change;
	}
	public String getIs_expense() {
		return is_expense;
	}
	public void setIs_expense(String is_expense) {
		this.is_expense = is_expense;
	}
	public String getIs_sale() {
		return is_sale;
	}
	public void setIs_sale(String is_sale) {
		this.is_sale = is_sale;
	}
	public String getIs_book() {
		return is_book;
	}
	public void setIs_book(String is_book) {
		this.is_book = is_book;
	}
	public String getSearch_attr() {
		return search_attr;
	}
	public void setSearch_attr(String search_attr) {
		this.search_attr = search_attr;
	}
	public String getSearch_cid() {
		return search_cid;
	}
	public void setSearch_cid(String search_cid) {
		this.search_cid = search_cid;
	}
	public LinkedList<SkuData> getSku_list() {
		return sku_list;
	}
	public void setSku_list(LinkedList<SkuData> sku_list) {
		this.sku_list = sku_list;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getAttr_sku() {
		return attr_sku;
	}
	public void setAttr_sku(String attr_sku) {
		this.attr_sku = attr_sku;
	}
	
	

}
