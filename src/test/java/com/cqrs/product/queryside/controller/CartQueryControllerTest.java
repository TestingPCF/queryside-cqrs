package com.cqrs.product.queryside.controller;

import com.cqrs.product.queryside.bean.Cart;
import com.cqrs.product.queryside.exception.CustomException;
import com.cqrs.product.queryside.response.CartService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpStatus;

@RunWith(value = PowerMockRunner.class)
@PrepareForTest({HttpStatus.class})
public class CartQueryControllerTest {

    /**
     * cartController.
     */
    @InjectMocks
    private CartQueryController cartController;

    @Mock
    private CartService cartService;

    /**
     * ACCESS_TOKEN.
     */
    private static final String AUTH_TOKEN = "TOKEN";

    /**
     * This is a method for pre-processing tasks.
     * @throws Exception Exception.
     */
    @Before
    public void setUp() throws Exception {
        cartController = PowerMockito.spy(new CartQueryController());
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test for failed case testGetCart.
     * @throws Exception
     * @throws CustomException
     */
    @Test
    public final void testGetCartFailed() throws CustomException, Exception {
        Mockito.when(cartService.getCartById(AUTH_TOKEN)).thenReturn(null);
        Assert.assertEquals(HttpStatus.OK, cartController.getCart(AUTH_TOKEN).getStatusCode());
    }

    /**
     * Test for testGetCartWhenCustomExceptionOccur for CustomException.
     * @throws Exception
     */
    @Test
    public final void testGetCartWhenCustomExceptionOccur() throws Exception {
        Mockito.when(cartService.getCartById(AUTH_TOKEN)).thenThrow(CustomException.class);
        Assert.assertEquals(HttpStatus.UNAUTHORIZED, cartController.getCart(AUTH_TOKEN).getStatusCode());
    }

    /**
     * Test for testGetCartWhenInternalServerError for Exception.
     * @throws Exception
     */
    @Test
    public final void testGetCartWhenInternalServerError() throws Exception {
        Mockito.when(cartService.getCartById(AUTH_TOKEN)).thenThrow(RuntimeException.class);
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, cartController.getCart(AUTH_TOKEN).getStatusCode());
    }

    /**
     * Test for testGetCartWhenInternalServerError for Exception.
     * @throws Exception
     */
    @Test
    public final void testGetCartSuccess() throws Exception {
        Cart cart = Mockito.mock(Cart.class);
        Mockito.when(cartService.getCartById(AUTH_TOKEN)).thenReturn(cart);
        cartController.getCart(AUTH_TOKEN);
    }

    /**
     * Test Success for testAddItemInCartSuccess.
     * @throws Exception
     */

    @Test
    public final void testAddItemInCartSuccess() throws Exception {
        Cart cart = Mockito.mock(Cart.class);
        Mockito.doNothing().when(cartService).addItemInCart(cart);
        cartController.receiveMessage(cart);
    }

}
