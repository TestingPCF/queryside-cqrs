package com.cqrs.product.queryside.service;

import java.util.List;

import org.springframework.core.env.Environment;

import com.cqrs.product.queryside.bean.CreateproductReq;
import com.cqrs.product.queryside.bean.Order;
import com.cqrs.product.queryside.bean.ProductQueue;

public interface IProductService {
	
	//void addProduct(ProductMessageBean customMessage);
	void addProduct(CreateproductReq productData);
	
	ProductQueue getProduct(String skuCode);
	
	List<ProductQueue> getAllProduct();
	
	void addOrder(Order orderData);

	List<CreateproductReq> viewProducts(Environment env);
	
	List<Order> getAllOrders();
}
