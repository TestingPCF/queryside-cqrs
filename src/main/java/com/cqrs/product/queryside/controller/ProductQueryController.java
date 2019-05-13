package com.cqrs.product.queryside.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cqrs.product.queryside.bean.CreateproductReq;
import com.cqrs.product.queryside.bean.Order;
import com.cqrs.product.queryside.bean.ProductQueue;
import com.cqrs.product.queryside.config.RabbitmqConfigProduct;
import com.cqrs.product.queryside.constant.OrderConstant;
import com.cqrs.product.queryside.constant.ProductConstants;
import com.cqrs.product.queryside.datatranslator.ViewProductsResponseTranslator;
import com.cqrs.product.queryside.exception.ProductException;
import com.cqrs.product.queryside.response.ViewproductRes;
import com.cqrs.product.queryside.service.IProductService;
import com.cqrs.product.queryside.util.ResponseUtil;


@RestController
public class ProductQueryController {
	
	public static final Logger logger=LoggerFactory.getLogger(ProductQueryController.class);
	
	@Autowired
	IProductService productService;
	
	@Autowired
	Environment env;
	
	/*@RabbitListener(queues = RabbitmqConfig.QUEUE_SPECIFIC_NAME)
    public void receiveMessage(ProductMessageBean customMessage) {
		logger.info("Inside receiveMessage...");
		productService.addProduct(customMessage);
		logger.info("Outside receiveMessage...");
    }*/
	
	@RabbitListener(queues = RabbitmqConfigProduct.QUEUE_SPECIFIC_NAME)
    public void receiveMessage(CreateproductReq productData) {
		logger.info("Queues message received...");
		logger.info("Product name in received message is... "+productData.getProductName());
		productService.addProduct(productData);
		logger.info("receiveMessage call end");
    }
	
	/*@RabbitListener(queues = RabbitmqConfigOrder.QUEUE_SPECIFIC_NAME)
    public void receiveOrderMessage(Order orderData) {
		//CustomMessageBean customMessageBean=(CustomMessageBean) jackson2JsonMessageConverter.fromMessage(customMessage);
    	//System.out.println("Received message from Listener from Controller {} "+ customMessage.toString());
		logger.info("Inside receiveMessage...");
		//CreateproductReq productData = new CreateproductReq();
		//BeanUtils.copyProperties(productData, productBean);
		logger.info("User Email in order... "+orderData.getUserEmail());
		productService.addOrder(orderData);
		logger.info("Outside receiveMessage...");
    }*/
	
	@CrossOrigin("*")
	@GetMapping("/productInfo/{skuCode}")
	@ResponseBody
	public ProductQueue gerProduct(@PathVariable String skuCode){
		logger.info("Inside controller gerProduct with skuCode - "+ skuCode);
		ProductQueue products = productService.getProduct(skuCode);
		return products;
	}
	
	@CrossOrigin("*")
	@GetMapping("/productInfo")
	@ResponseBody
	public List<ProductQueue> gerAllProduct(){
		logger.info("Inside controller gerAllProduct()...");
		List<ProductQueue> products = productService.getAllProduct();
		return products;
	}
	
	/**
     * This method is used for view all active products.
     * 
     * @param accessToken
     * @return ResponseEntity
     * @throws ProductException
     */
    @RequestMapping(value = "/products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ViewproductRes> viewProducts(
            @RequestHeader(value = ProductConstants.ACCESS_TOKEN, required = true) String accessToken) throws ProductException {
    	logger.info("viewProducts call start"); 
    	logger.info("accessToken"+ accessToken);
        ViewProductsResponseTranslator vproductst = new ViewProductsResponseTranslator();
        ViewproductRes viewproductRes = null;

        List<CreateproductReq> pList = productService.viewProducts(env);
        viewproductRes = vproductst.viewProductsResponseTranslator(pList, env);
        logger.info("viewProducts call end");
        return ResponseEntity.ok().body(viewproductRes);

    }
    
    /**
     * This is a method wo handle GET request, it will return all the methods
     * associated with the logged-in user.
     *
     * @param authToken a GWT token of a logged in user.
     * @return Response Entity
     *
    @GetMapping("/orders")
    public final ResponseEntity getAllOrders(
            final @RequestHeader(value = OrderConstant.AUTHORIZATION_TOKEN, required = true) String authToken) {
        try {
            logger.info(OrderConstant.START + OrderConstant.ORDER_GETALL_INFO);
            List<Order> orderEntityList = productService.getAllOrders();
            logger.info(OrderConstant.COMPLETED + OrderConstant.ORDER_GETALL_INFO);
            return ResponseUtil.getResponseEntity(HttpStatus.OK, OrderConstant.ORDER_SUCCESS, orderEntityList);
        } catch (Exception e) {
            logger.info(OrderConstant.ERROR + OrderConstant.ORDER_GETALL_INFO);
            logger.error(e.getMessage(), e);
            return ResponseUtil.getResponseEntity( HttpStatus.INTERNAL_SERVER_ERROR, OrderConstant.ORDER_FAILED, null);
        }
    } */
}
	