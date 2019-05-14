package com.cqrs.product.queryside.constant;

/**
 * Constants class.
 *
 * @author baghelp
 */
public class CartConstant {

    /**
     * NO_CONTENT.
     */
    public static final String NO_CONTENT = "204";

    /**
     * Constant for success message.
     */
    public static final String RETRIEVE_SUCCESS = "success";

    /**
     * Constant for updated message.
     */
    public static final String UPDATE_SUCCESS = "updated successfully";

    /**
     * Constant for deleted message.
     */
    public static final String DELETED = "deleted successfully";

    /**
     * Constant for failed message.
     */
    public static final String FAIL = "failed";

    /**
     * CART_EMPTY.
     */
    public static final String CART_EMPTY = "Cart is empty";

    /**
     * AUTHORIZATION_TOKEN.
     */
    public static final String AUTHORIZATION_TOKEN = "accessToken";

    /**
     * INVERNTORY.
     */
    public static final String INVERNTORY = "inventory";

    /**
     * PRODUCT.
     */
    public static final String PRODUCT = "product";

    /**
     * TOKEN.
     */
    public static final String TOKEN = "token";
    /**
     * UNAUTHORIZED.
     */
    public static final String UNAUTHORIZED = "User is not authorized.";
    /**
     * EXCHANGE_NAME_CART.
     */
    public static final String EXCHANGE_NAME_CART = "cartMQ";
    /**
     * ROUTING_KEY.
     */
    public static final String ROUTING_KEY_CART = "cartPOC";
    /**
     * QUEUE_SPECIFIC_NAME.
     */
    public static final String QUEUE_SPECIFIC_NAME_CART = "cartQueue";
}
