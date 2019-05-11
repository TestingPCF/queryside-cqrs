package com.cqrs.product.queryside.util;

import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cqrs.product.queryside.bean.Order;
import com.cqrs.product.queryside.bean.Response;
import com.cqrs.product.queryside.bean.Status;

/**
 * This is a utility class which can be used to prepare a Http response entity.
 *
 * @author shikhar.a || ankit-kumar
 */
public final class ResponseUtil {

    /**
     * Private constructor.
     *
     * @param obj Object
     */
    private ResponseUtil(final Object obj) {
        System.out.println(obj);
    }

    /**
     * This method will prepare a ResponseEntity and return the same.
     *
     * @param httpStatus     http Status
     * @param responseString response String
     * @param data           data
     * @return ResponseEntity entity object
     */
    public static ResponseEntity getResponseEntity(
            final HttpStatus httpStatus,
            final String responseString,
            final Object data) {
        Status status = new Status(httpStatus,
                responseString);
        Response<Order> response = null;
        if (data instanceof Collection) {
            List<Order> orderList = (List<Order>) data;
            response = new Response.Builder<Order>(status)
                    .setCollection(orderList).build();
        } else {
            Order order = (Order) data;
            response = new Response.Builder<Order>(status)
                    .setEntity(order)
                    .build();
        }

        return new ResponseEntity<Response<Order>>(response,
                httpStatus);
    }

    /**
     * getCartResponse.
     * @param cartResponse Cart Response Entity
     * @return Cart response
     * @throws IOException IO Exception
     */
   /* public static CartResponse getCartResponse(
            final ResponseEntity<Object> cartResponse)
            throws IOException {
        JsonNode jsonNode =
                new ObjectMapper().valueToTree(cartResponse.getBody());
        String json =
                new ObjectMapper().writeValueAsString(jsonNode);
        CartResponse response = new ObjectMapper()
                .readValue(json, CartResponse.class);
        return response;
    }*/
}
