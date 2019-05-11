package com.cqrs.product.queryside.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductMessageBean {

	private String skuCode;
	private String name;
	
	public ProductMessageBean() {
	}
	public ProductMessageBean(@JsonProperty String skuCode,@JsonProperty String name) {
		super();
		this.skuCode = skuCode;
		this.name = name;
	}
	
	public String getSkuCode() {
		return skuCode;
	}
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "ProductMessageBean {skuCode=" + skuCode + ", name=" + name + "}";
	}
}

