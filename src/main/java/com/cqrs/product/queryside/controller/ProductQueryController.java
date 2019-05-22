package com.cqrs.product.queryside.controller;

import static com.cqrs.product.queryside.constant.ProductConstants.ACCESS_TOKEN;
import static com.cqrs.product.queryside.constant.ProductConstants.SKU_CODE;
import static com.cqrs.product.queryside.constant.ProductConstants.VIEW_PRODUCT_BYSKUCODE_URI;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cqrs.product.queryside.bean.CreateproductReq;
import com.cqrs.product.queryside.constant.ProductConstants;
import com.cqrs.product.queryside.datatranslator.ViewProductbySkuCodeResponseTranslator;
import com.cqrs.product.queryside.datatranslator.ViewProductsResponseTranslator;
import com.cqrs.product.queryside.exception.ProductException;
import com.cqrs.product.queryside.response.ViewproductRes;
import com.cqrs.product.queryside.service.IProductService;


@RestController
public class ProductQueryController {
	
	public static final Logger logger=LoggerFactory.getLogger(ProductQueryController.class);
	
	@Autowired
	IProductService productService;
	
	@Autowired
	Environment env;
	
	@RabbitListener(queues = ProductConstants.QUEUE_SPECIFIC_NAME)
    public void receiveMessage(CreateproductReq productData) {
		logger.info("Queues message received...");
		logger.info("Product name in received message is... "+productData.getProductName());
		productService.addProduct(productData);
		logger.info("receiveMessage call end");
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
     * This method is used for view single entry of active product based on skuCode.
     * 
     * @param accessToken
     * @param skuCode
     * @return ResponseEntity
     * @throws ProductException
     */
    @RequestMapping(method = RequestMethod.GET, value = VIEW_PRODUCT_BYSKUCODE_URI, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ViewproductRes> viewProductBySkuCode(
            @RequestHeader(value = ACCESS_TOKEN, required = true) String accessToken,
            @PathVariable(SKU_CODE) String skuCode) throws ProductException {
    	logger.info("viewProductBySkuCode call start");
        ViewProductbySkuCodeResponseTranslator vpt = new ViewProductbySkuCodeResponseTranslator();
        ViewproductRes viewproductRes = null;
        try {
            if (!StringUtils.isEmpty(skuCode)) {
                List<CreateproductReq> pList = productService.viewproductbyskuCode(skuCode, env);
                viewproductRes = vpt.viewProductbySkuCodeResponseTranslator(pList, env);
            }
        } catch (Exception ex) {
            throw new ProductException(ex.getMessage());
        }
        logger.info("viewProductBySkuCode call end");
        return ResponseEntity.ok().body(viewproductRes);

    }
    
    /**
     * @param productService
     */
    public void setProductService(IProductService productService) {
		this.productService = productService;
	}

	/**
	 * @param env
	 */
	public void setEnv(Environment env) {
		this.env = env;
	}

}
	