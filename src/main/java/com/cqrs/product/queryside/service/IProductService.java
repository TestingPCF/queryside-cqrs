package com.cqrs.product.queryside.service;

import java.util.List;

import org.springframework.core.env.Environment;

import com.cqrs.product.queryside.bean.CreateproductReq;

public interface IProductService {
	
	void addProduct(CreateproductReq productData);
	
	public List<CreateproductReq> viewproductbyskuCode(String skuCode, Environment env);
	
	List<CreateproductReq> viewProducts(Environment env);
	
}
