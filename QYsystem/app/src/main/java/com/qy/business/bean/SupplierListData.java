package com.qy.business.bean;

import java.util.LinkedList;

public class SupplierListData {

	private LinkedList<SupplierList_dbBean> list;
	private LinkedList<SupplygcateBean> supplygcate;
	private LinkedList<CatesBean> nextcate;
	private LinkedList<RegionBean> region_list;
	private LinkedList<BrandsBean> brands;

	public LinkedList<SupplygcateBean> getSupplygcate() {
		return supplygcate;
	}

	public void setSupplygcate(LinkedList<SupplygcateBean> supplygcate) {
		this.supplygcate = supplygcate;
	}

	public LinkedList<CatesBean> getNextcate() {
		return nextcate;
	}

	public void setNextcate(LinkedList<CatesBean> nextcate) {
		this.nextcate = nextcate;
	}

	public LinkedList<RegionBean> getRegion_list() {
		return region_list;
	}

	public void setRegion_list(LinkedList<RegionBean> region_list) {
		this.region_list = region_list;
	}

	public LinkedList<BrandsBean> getBrands() {
		return brands;
	}

	public void setBrands(LinkedList<BrandsBean> brands) {
		this.brands = brands;
	}

	public LinkedList<SupplierList_dbBean> getList() {
		return list;
	}

	public void setList(LinkedList<SupplierList_dbBean> list) {
		this.list = list;
	}

}
