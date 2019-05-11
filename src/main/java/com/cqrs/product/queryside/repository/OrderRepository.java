package com.cqrs.product.queryside.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cqrs.product.queryside.bean.Order;

/**
 * This is an interface which extends MongoRepository interface. It will be
 * responsible to talk to mongo database.
 *
 * @author shikhar.a || ankit-kumar
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
