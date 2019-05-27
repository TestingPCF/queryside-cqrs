package com.cqrs.product.queryside.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * This is configuration loader class. Which pulls data at runtime.
 * @author shikhar.a
 */
@Configuration
@RefreshScope
public class ConfigLoader {

    private String exchangeName;

    private String routingKey;

    private String queueSpecificName;

    private String productNotExistMessage;

    public String getExchangeName() {
        return exchangeName;
    }

    @Value("${query.product.mq.exchange}")
    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    @Value("${query.product.mq.routing.key}")
    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public String getQueueSpecificName() {
        return queueSpecificName;
    }

    @Value("${query.product.mq.queue.name}")
    public void setQueueSpecificName(String queueSpecificName) {
        this.queueSpecificName = queueSpecificName;
    }

    public String getProductNotExistMessage() {
        return productNotExistMessage;
    }

    @Value("${product.not.exist.msg}")
    public void setProductNotExistMessage(String productNotExistMessage) {
        this.productNotExistMessage = productNotExistMessage;
    }
}
