package com.cqrs.product.queryside.response;

import com.cqrs.product.queryside.bean.Cart;

import java.io.IOException;

/**
 * CartService interface.
 * @author baghelp
 */
public interface CartService {

    /**
     * This method adds the passed product item in the cart and saves the cart.
     * @param cart cartDto.
     * @throws Exception
     */
    void addItemInCart(Cart cart) throws Exception;

    /**
     * This method retrieves the cart details for the given user.
     * @param authToken authToken.
     * @return cart cart.
     * @throws Exception
     */
    Cart getCartById(String authToken) throws Exception;

}
