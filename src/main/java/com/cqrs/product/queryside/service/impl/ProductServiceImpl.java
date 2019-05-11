package com.cqrs.product.queryside.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.cqrs.product.queryside.bean.CreateproductReq;
import com.cqrs.product.queryside.bean.Order;
import com.cqrs.product.queryside.bean.ProductQueue;
import com.cqrs.product.queryside.constant.OrderConstant;
import com.cqrs.product.queryside.repository.OrderRepository;
import com.cqrs.product.queryside.repository.ProductQueueRepository;
import com.cqrs.product.queryside.repository.ProductRepository;
import com.cqrs.product.queryside.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService{

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private ProductQueueRepository productQueueRepository;
	
	@Autowired
	private ProductRepository productRespository;
	
	@Autowired
	private OrderRepository orderRespository;

	/*@Override
	public void addProduct(ProductMessageBean customMessage) {
		logger.info("Inside addProduct...");
		ProductQueue product = new ProductQueue();
		product.setName(customMessage.getName());
		product.setSkuCode(customMessage.getSkuCode());
		productReposetory.save(product);
		logger.info("Product - "+ product.getName() + " save successfully...");
	}*/
	
	@Override
	public void addProduct(CreateproductReq productData) {
		productRespository.save(productData);
		logger.info("Product - "+ productData.getProductName() + " save successfully...");
	}
	
	@Override
	public void addOrder(Order orderData) {
		orderRespository.save(orderData);
		logger.info("Order - "+ orderData.getOrderId() + " save successfully...");
	}
	
	@Override
	public ProductQueue getProduct(String skuCode) {
		logger.info("Inside getProduct with skuCode - "+ skuCode);
		ProductQueue product = productQueueRepository.findBySkuCode(skuCode);
		logger.info("Product find - skuCode - "+ skuCode);
		return product;
	}

	@Override
	public List<ProductQueue> getAllProduct() {
		logger.info("Inside gerAllProduct()...");
		List<ProductQueue> product = productQueueRepository.findAll();
		return product;
	}

	@Override
	public List<CreateproductReq> viewProducts(Environment env) {
		logger.info("View Products DB call start");
        List<CreateproductReq> productList = new ArrayList<CreateproductReq>();

        productList = productRespository.findAll();

        logger.info("View Products DB call end");

        return productList;
	}

	@Override
	public List<Order> getAllOrders() {
		logger.info(OrderConstant.INPROGRES + OrderConstant.ORDER_GETALL_INFO);
		return orderRespository.findAll();
	}
}
