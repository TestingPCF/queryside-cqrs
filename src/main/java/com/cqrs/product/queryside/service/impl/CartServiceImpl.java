package com.cqrs.product.queryside.service.impl;

import com.cqrs.product.queryside.bean.Cart;
import com.cqrs.product.queryside.bean.CartItem;
import com.cqrs.product.queryside.bean.TokenInfo;
import com.cqrs.product.queryside.exception.CustomException;
import com.cqrs.product.queryside.repository.CartRepository;
import com.cqrs.product.queryside.response.CartService;
import com.cqrs.product.queryside.util.EntityTransformerUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * CartServiceImpl - implementation class for the cart service.
 * @author baghelp
 */
@Service
public class CartServiceImpl implements CartService {
    /**
     * Logger object.
     */
    private static final Logger LOG =
            LoggerFactory.getLogger(CartServiceImpl.class);

    /**
     * Autowired object of the CartRepository to be able
     * to access the members of the JPA repository.
     */
    @Autowired
    private CartRepository cartRepository;
    /**
     * Autowired RabbitTemplate.
     */
    @Autowired
    private RabbitTemplate rabbitCartTemplate;

    /**
     * Method to add item in the cart.
     * @param cart cart
     * @throws Exception
     */
    @Override
    public void addItemInCart(final Cart cart) throws Exception {
        LOG.info("Read [cartQueue] and saving the entity.");
        Cart savedCart = getCart(cart.getUserId());
        boolean isItemQuantityIncreased = false;
        if (savedCart != null) {
            isItemQuantityIncreased = setCartQtyToDto(savedCart, cart);
        } else {
            savedCart = cart;
            for(CartItem item : savedCart.getCartItems()) {
                item.setCart(savedCart);
            }
        }
        savedCart.setSubTotal(calculateSubtotal(savedCart.getCartItems()));
        Cart persistCart = cartRepository.save(savedCart);
        LOG.info("Read [cartQueue] and saved the entity.");
    }

    private BigDecimal calculateSubtotal(List<CartItem> cartItems) {
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem cartItem : cartItems) {
            total = total.add(cartItem.getSalePrice()
                    .multiply(new BigDecimal(cartItem.getQuantity())));
        }
        return total;
    }

    private boolean setCartQtyToDto(Cart savedCart, Cart cart) {
        CartItem newItem = cart.getCartItems().get(0);
        for (CartItem cartItem : savedCart.getCartItems()) {
            if (cartItem.getItemCode().equalsIgnoreCase(newItem.getItemCode())) {
                cartItem.setQuantity(cartItem.getQuantity() + newItem.getQuantity());
                return true;
            }
        }
        newItem.setCart(savedCart);
        savedCart.getCartItems().add(newItem);
        return false;
    }

    /**
     * Method to retrieve the details of the shopping cart by userId.
     *
     * @param authToken string type.
     * @return cart {@link Cart}
     * @throws Exception
     */
    @Override
    public Cart getCartById(final String authToken) throws Exception, IOException {
        String userId = getUserIdByToken(authToken);
        Cart cart = getCart(userId);
        return cart;
    }

    /**
     * Private method to get cart details by userId.
     *
     * @return cart object Cart type.
     * @throws IOException
     * @throws Exception
     */
    private Cart getCart(final String userId) throws Exception, IOException {
        return cartRepository.findByUserId(userId);
    }
    /**
     * This method return userId based on Authorization token value.
     *
     * @param authToken {@link String}
     * @return userId {@link String} based on token.
     * @throws Exception
     */
    private String getUserIdByToken(final String authToken) throws Exception {
        try {
            TokenInfo tokenInfo = EntityTransformerUtility.getTokenInfo(authToken);
            if (tokenInfo != null && tokenInfo.getStatus() == 0) {
                return tokenInfo.getUserId();
            } else if (tokenInfo != null && tokenInfo.getStatus() == 401) {
                throw new CustomException("Unauthorized User", HttpStatus.UNAUTHORIZED);
            }
        } catch (HttpClientErrorException ex) {
            throw new CustomException("Unauthorized User", HttpStatus.UNAUTHORIZED);
        }
        return null;
    }
}
