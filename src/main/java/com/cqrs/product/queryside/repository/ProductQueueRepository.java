package com.cqrs.product.queryside.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cqrs.product.queryside.bean.ProductQueue;

@Repository("ProductQueueRepository")
public interface ProductQueueRepository extends JpaRepository<ProductQueue,String> {
	
	ProductQueue findBySkuCode(String skuCode);
}
