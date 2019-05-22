package com.cqrs.product.queryside.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.cqrs.product.queryside.bean.CreateproductReq;
import com.cqrs.product.queryside.exception.ProductException;
import com.cqrs.product.queryside.repository.ProductRepository;
import com.cqrs.product.queryside.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService{

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	
	@Autowired
	private ProductRepository productRespository;

	/**
	 * This method listen add product event from queue and persist inside query DB 
	 */
	@Override
	public void addProduct(CreateproductReq productData) {
		productRespository.save(productData);
		logger.info("Product - "+ productData.getProductName() + " save successfully...");
	}
	
	/**
	 * This method will fetch all products from query side db 
	 */
	@Override
	public List<CreateproductReq> viewProducts(Environment env) {
		logger.info("View Products DB call start");
        List<CreateproductReq> productList = new ArrayList<CreateproductReq>();

        productList = productRespository.findAll();

        logger.info("View Products DB call end");

        return productList;
	}
	
	 /**
     * This method is used for view single entry of active product based on skuCode.
     * 
     * @param skuCode
     * @return List<CreateproductReq>
     * @throws ProductException
     */
    @Override
    public List<CreateproductReq> viewproductbyskuCode(String skuCode, Environment env) throws ProductException {
    	logger.info("View ProductBySkuCode DB call start");
        List<CreateproductReq> productList = new ArrayList<CreateproductReq>();
        if (null != skuCode) {
	        Optional<CreateproductReq> product = productRespository.findById(skuCode);
	        if (product.isPresent()) {
	            productList.add(product.get());
	        }
        }
        logger.info("View ProductBySkuCode DB call end");
        return productList;
    }

}
