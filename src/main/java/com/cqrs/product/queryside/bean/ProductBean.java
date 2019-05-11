package com.cqrs.product.queryside.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductBean {

	private String skuCode = null;
	private String productName = null;
	private Integer salePrice;
	private Integer listPrice;
	private String productDescrition = null;
	private String category = null;
	private boolean is_deleted = false;
	private String status = null;
	
	public ProductBean() {
		
	}
	
	public ProductBean(@JsonProperty String skuCode, @JsonProperty String productName, @JsonProperty Integer salePrice, @JsonProperty Integer listPrice,
			@JsonProperty String productDescrition, @JsonProperty String category, @JsonProperty boolean is_deleted, @JsonProperty String status) {
		super();
		this.skuCode = skuCode;
		this.productName = productName;
		this.salePrice = salePrice;
		this.listPrice = listPrice;
		this.productDescrition = productDescrition;
		this.category = category;
		this.is_deleted = is_deleted;
		this.status = status;
	}



	public String getSkuCode() {
		return skuCode;
	}
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Integer salePrice) {
		this.salePrice = salePrice;
	}
	public Integer getListPrice() {
		return listPrice;
	}
	public void setListPrice(Integer listPrice) {
		this.listPrice = listPrice;
	}
	public String getProductDescrition() {
		return productDescrition;
	}
	public void setProductDescrition(String productDescrition) {
		this.productDescrition = productDescrition;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public boolean isIs_deleted() {
		return is_deleted;
	}
	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
