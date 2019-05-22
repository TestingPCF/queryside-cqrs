
package com.cqrs.product.queryside.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;

import com.cqrs.product.queryside.bean.CreateproductReq;
import com.cqrs.product.queryside.exception.ProductException;
import com.cqrs.product.queryside.response.ViewproductRes;
import com.cqrs.product.queryside.service.impl.ProductServiceImpl;


public class ProductQueryControllerTest {

	ProductQueryController productController = new ProductQueryController();

	Environment env;

	
	
	@Test
	public void testReceiveMessage() throws ProductException {

		ProductServiceImpl productService = Mockito.mock(ProductServiceImpl.class);
		env = Mockito.mock(Environment.class);
		productController.setEnv(env);
		productController.setProductService(productService);
		CreateproductReq createproductReq = new CreateproductReq();
		createproductReq.setSkuCode("ABC");
		productController.receiveMessage(createproductReq);
	}
	
	@Test
	public void testViewProductBySkuCode() throws ProductException {

		String skuCode="ABC";
		ProductServiceImpl productService = Mockito.mock(ProductServiceImpl.class);
		env = Mockito.mock(Environment.class);
		productController.setEnv(env);
		productController.setProductService(productService);

		CreateproductReq createproductReq = new CreateproductReq();
		createproductReq.setSkuCode("ABC");
		List<CreateproductReq> pList=new ArrayList<CreateproductReq>();
		pList.add(createproductReq);
		when(productService.viewproductbyskuCode(skuCode, env)).thenReturn(pList);
		
		ResponseEntity<ViewproductRes>  response=productController.viewProductBySkuCode("abc", skuCode);
		assertEquals("200 OK",response.getStatusCode().toString());
	}
	
	@Test(expected=ProductException.class)
	public void testViewProductBySkuCodeException() throws ProductException {

		String skuCode="ABC";
		ProductServiceImpl productService = Mockito.mock(ProductServiceImpl.class);
		env = Mockito.mock(Environment.class);
		productController.setEnv(env);

		CreateproductReq createproductReq = new CreateproductReq();
		createproductReq.setSkuCode("ABC");
		List<CreateproductReq> pList=new ArrayList<CreateproductReq>();
		pList.add(createproductReq);
		when(productService.viewproductbyskuCode(skuCode, env)).thenReturn(pList);
		
		productController.viewProductBySkuCode("abc", skuCode);
	}
	
	
	@Test
	public void testViewProducts() throws ProductException {

		ProductServiceImpl productService = Mockito.mock(ProductServiceImpl.class);
		env = Mockito.mock(Environment.class);
		productController.setEnv(env);
		productController.setProductService(productService);

		CreateproductReq createproductReq = new CreateproductReq();
		createproductReq.setSkuCode("ABC");
		List<CreateproductReq> pList=new ArrayList<CreateproductReq>();
		pList.add(createproductReq);
		when(productService.viewProducts(env)).thenReturn(pList);
		
		ResponseEntity<ViewproductRes>  response = productController.viewProducts("131333");
		assertEquals("200 OK",response.getStatusCode().toString());
	}
	
}
