package com.cqrs.product.queryside.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cqrs.product.queryside.bean.CreateproductReq;
/**
 * 
 * @author Brijendra and Kapil
 *
 */
public interface ProductRepository extends JpaRepository<CreateproductReq, String>{

}
