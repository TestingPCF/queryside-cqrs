package com.cqrs.product.queryside.client;

import com.cqrs.product.queryside.constant.CartConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * This class will treat as a client for rest apis.
 * @author kumar_sanjay || Pankaj
 */
public class RestClient {

    /**
     * uaa.
     */
    private static String uaa = "http://uaa.apps.sandbox.cflab01.in.hclcnlabs.com/tokenInfo";

    /**
     * logger.
     */
    private static final Logger LOGGER =
            LoggerFactory.getLogger(RestClient.class);

    /**
     * getResponseFromMS.
     * @param serviceName Name of the service.
     * @param object Object
     * @param authorization Authorization
     * @param skuCode skuCode
     * @return ResponseEntity prepared response entity
     * @throws HttpClientErrorException exception
     */
    public static ResponseEntity<Object>
    getResponseFromMS(final String serviceName,
                      final Object object,
                      final String authorization,
                      final String skuCode)
            throws HttpClientErrorException {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set(CartConstant.AUTHORIZATION_TOKEN, authorization);
            HttpEntity<String> entity = new HttpEntity<>("parameters",
                    headers);
            ResponseEntity<Object> response = null;
            switch (serviceName) {
                case CartConstant.TOKEN:
                 LOGGER.info("Call API of Token MS from cart");
                 response = restTemplate.exchange(uaa,
                            HttpMethod.GET,
                            entity,
                            Object.class);
                 return response;
                    default:
                        return new ResponseEntity<>(HttpStatus
                                .INTERNAL_SERVER_ERROR);

            }
    }

    /**
     * add Method Method.
     */
    public final int printApplication(final int a, final int b) {
        return a + b;
    }

}
