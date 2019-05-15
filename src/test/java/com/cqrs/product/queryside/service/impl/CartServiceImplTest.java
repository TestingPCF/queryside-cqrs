package com.cqrs.product.queryside.service.impl;

import com.cqrs.product.queryside.bean.Cart;
import com.cqrs.product.queryside.bean.CartItem;
import com.cqrs.product.queryside.bean.TokenInfo;
import com.cqrs.product.queryside.client.RestClient;
import com.cqrs.product.queryside.exception.CustomException;
import com.cqrs.product.queryside.repository.CartRepository;
import com.cqrs.product.queryside.util.EntityTransformerUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(value = PowerMockRunner.class)
@PrepareForTest({ EntityTransformerUtility.class, RestClient.class })
public class CartServiceImplTest {

    /**
     * userId.
     */
    private static String userId = "123";

    /**
     * authToken.
     */
    private final static String authToken = "1212bjhsds6";

    /**
     * skuCode.
     */
    private final static String SKU_CODE = "iphone6Red";

    /**
     * quantity.
     */
    private final static int quantity = 2;

    /**
     * LIST_PRICE.
     */
    private final static BigDecimal LIST_PRICE = BigDecimal.TEN;

    /**
     * SALE_PRICE.
     */
    private final static BigDecimal SALE_PRICE = BigDecimal.ONE;

    /**
     * cartServiceImpl Mock.
     */
    @InjectMocks
    private CartServiceImpl cartServiceImpl;

    /**
     * cartRepository.
     */
    @Mock
    private CartRepository cartRepository;

    /**
     * responseBody.
     */
    @Mock
    private Object responseBody;
    /**
     * Mock RabbitTemplate.
     */
    @Mock
    private RabbitTemplate rabbitTemplate;

    /**
     * salePrice - represents the sale price of the item.
     */
    private double salePrice = 10.0;

    /**
     * listrice - represents the list price of the item.
     */
    private double listPrice = 12.4;

    /**
     * response.
     */
    @Mock
    private ResponseEntity<Object> response;

    @Mock
    private ObjectMapper objectMapper;

    /**
     * This Method is called before the test is executed.
     *
     * @throws Exception
     **/

    @Before
    public void setUp() throws Exception {
        this.cartServiceImpl = PowerMockito.spy(new CartServiceImpl());
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Success test method for GetCartById.
     *
     * @throws Exception
     * @throws CustomException
     */
    @Test
    public final void testGetCartByIdSuccess() throws CustomException, Exception {
        PowerMockito.mockStatic(EntityTransformerUtility.class);
        TokenInfo tokenInfo = Mockito.mock(TokenInfo.class);
        Mockito.when(EntityTransformerUtility.getTokenInfo(authToken)).thenReturn(tokenInfo);
        Mockito.when(tokenInfo.getUserId()).thenReturn(userId);
        Cart cart = Mockito.mock(Cart.class);
        Mockito.when(cartRepository.findByUserId(userId)).thenReturn(cart);
        cartServiceImpl.getCartById(authToken);
    }

    /**
     * Success Test for testGetCartByIdWhenCartIsNull Method.
     *
     * @throws Exception
     * @throws CustomException
     **/
    @Test(expected = CustomException.class)
    public final void testGetCartByIdWhenCartIsNull() throws CustomException, Exception {
        Mockito.when(cartRepository.findByUserId(userId)).thenReturn(null);
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setStatus(401);
        PowerMockito.mockStatic(EntityTransformerUtility.class);
        Mockito.when(EntityTransformerUtility.getTokenInfo(authToken)).thenReturn(tokenInfo);
        cartServiceImpl.getCartById(authToken);
    }

    /**
     * Success Test for testGetCartByIdWhenCartIsNull Method for
     * HttpClientErrorException.
     *
     * @throws IOException
     * @throws HttpClientErrorException
     **/
    @Test(expected = CustomException.class)
    public final void testGetCartByIdHttpClientErrorException() throws HttpClientErrorException, Exception {
        Mockito.when(cartRepository.findByUserId(userId)).thenReturn(null);
        PowerMockito.mockStatic(EntityTransformerUtility.class);
        Mockito.when(EntityTransformerUtility.getTokenInfo(authToken)).thenThrow(HttpClientErrorException.class);
        cartServiceImpl.getCartById(authToken);
    }

    /**
     * Success Test for testGetCartByIdWhenCartIsNull Method.
     *
     * @throws IOException
     * @throws CustomException
     **/
    @Test(expected = CustomException.class)
    public final void testGetCartByIdWhenCustomExceptionOccurs() throws CustomException, Exception {
        Mockito.when(cartRepository.findByUserId(userId)).thenReturn(null);
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setStatus(201);
        PowerMockito.mockStatic(EntityTransformerUtility.class);
        Mockito.when(EntityTransformerUtility.getTokenInfo(authToken)).thenThrow(CustomException.class);
        cartServiceImpl.getCartById(authToken);
    }

    /**
     * Success Test for testaddItemInCartSuccess Method.
     *
     * @throws Exception
     **/
    @Test
    public final void testAddItemInCartSuccess() throws Exception {
        Cart cart = new Cart();
        CartItem cartItem = Mockito.mock(CartItem.class);
        cart.setUserId(userId);
        List<CartItem> cartItemList = new ArrayList<>();
        cartItemList.add(cartItem);
        Mockito.when(cartItem.getItemCode()).thenReturn(SKU_CODE);
        Mockito.when(cartItem.getListPrice()).thenReturn(LIST_PRICE);
        Mockito.when(cartItem.getSalePrice()).thenReturn(SALE_PRICE);
        Mockito.when(cartItem.getQuantity()).thenReturn(quantity);
        cart.setCartItems(cartItemList);
        Mockito.when(cartRepository.findByUserId(userId)).thenReturn(cart);


        cartServiceImpl.addItemInCart(cart);
    }

    /**
     * Success Test for testaddItemInCartSuccess Method.
     *
     * @throws Exception
     **/
    @Test
    public final void testAddItemInCartWithNewCartSuccess() throws Exception {
        Cart cart = new Cart();
        CartItem cartItem = Mockito.mock(CartItem.class);
        cart.setUserId(userId);
        List<CartItem> cartItemList = new ArrayList<>();
        cartItemList.add(cartItem);
        Mockito.when(cartItem.getItemCode()).thenReturn(SKU_CODE);
        Mockito.when(cartItem.getListPrice()).thenReturn(LIST_PRICE);
        Mockito.when(cartItem.getSalePrice()).thenReturn(SALE_PRICE);
        Mockito.when(cartItem.getQuantity()).thenReturn(quantity);
        cart.setCartItems(cartItemList);
        Mockito.when(cartRepository.findByUserId(userId)).thenReturn(null);


        cartServiceImpl.addItemInCart(cart);
    }
}
