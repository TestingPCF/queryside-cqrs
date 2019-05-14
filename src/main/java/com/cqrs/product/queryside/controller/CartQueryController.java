package com.cqrs.product.queryside.controller;

import com.cqrs.product.queryside.bean.Cart;
import com.cqrs.product.queryside.bean.ResponseStatus;
import com.cqrs.product.queryside.bean.Status;
import com.cqrs.product.queryside.constant.CartConstant;
import com.cqrs.product.queryside.exception.CustomException;
import com.cqrs.product.queryside.response.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("/query/cart")
@RestController
public class CartQueryController {

    public static final Logger logger= LoggerFactory.getLogger(CartQueryController.class);

    @Autowired
    CartService cartService;

    @RabbitListener(queues = CartConstant.QUEUE_SPECIFIC_NAME_CART)
    public void receiveMessage(Cart cartData) throws Exception {
        logger.info("Queues message received..." + cartData);
        logger.info("Message recieved as Cart for User ID... "
                + cartData.getUserId());
        cartService.addItemInCart(cartData);
        logger.info("receiveMessage call end");
    }

    /**
     * Method to get cart details.
     * @param authToken
     * @return
     */
    @GetMapping
    public ResponseEntity<ResponseStatus<?>> getCart(
            @RequestHeader(value = "accessToken", required = true)
                    String authToken) {
        ResponseStatus<Cart> response = null;
        Status messageStatus = null;
        try {
            Cart cart = cartService.getCartById(authToken);
            if(cart != null) {
                Status status = new Status(HttpStatus.OK, CartConstant.RETRIEVE_SUCCESS);
                response = new ResponseStatus.Builder<Cart>(status).setEntity(cart)
                        .build();
            } else {
                Status status = new Status(HttpStatus.OK, CartConstant.CART_EMPTY);
                response = new ResponseStatus.Builder<Cart>(status).build();
            }
        } catch (CustomException | IOException ex) {
            messageStatus = new Status(HttpStatus.UNAUTHORIZED, ex.getMessage());
            response = new ResponseStatus.Builder<Cart>(messageStatus).build();
            logger.error(CartConstant.UNAUTHORIZED, ex.getMessage());
        } catch(Exception ex) {
            messageStatus = new Status(HttpStatus.INTERNAL_SERVER_ERROR,
                    ex.getMessage());
            response = new ResponseStatus.Builder<Cart>(messageStatus).build();
            logger.error(ex.getMessage());
        }
        logger.info("Item retrieved successfully into the cart.");
        return new ResponseEntity<ResponseStatus<?>>(response, response
                .getStatus().status());
    }
}
