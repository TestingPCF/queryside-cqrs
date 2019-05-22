package com.cqrs.product.queryside.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cqrs.product.queryside.constant.CartConstant;
import com.cqrs.product.queryside.constant.ProductConstants;


@Configuration
public class RabbitmqConfigProduct {

	@Bean
	public TopicExchange mqExchange() {
		return new TopicExchange(ProductConstants.EXCHANGE_NAME);
	}

	@Bean
	public Queue appQueueSpecific() {
		return new Queue(ProductConstants.QUEUE_SPECIFIC_NAME);
	}

	@Bean
	public Binding declareBindingSpecific() {
		return BindingBuilder.bind(appQueueSpecific()).to(mqExchange()).with(ProductConstants.ROUTING_KEY);
	}

	@Bean
	public TopicExchange mqCartExchange() {
		return new TopicExchange(CartConstant.EXCHANGE_NAME_CART);
	}

	@Bean
	public Queue appCartQueueSpecific() {
		return new Queue(CartConstant.QUEUE_SPECIFIC_NAME_CART);
	}

	@Bean
	public Binding declareBindingSpecificCart() {
		return BindingBuilder.bind(appCartQueueSpecific()).to(mqCartExchange()).with(CartConstant.ROUTING_KEY_CART);
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		
		rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
		
		return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		Jackson2JsonMessageConverter converter=new Jackson2JsonMessageConverter();
		return converter;
	}
	
	public static class ImplicitJsonMessageConverter extends Jackson2JsonMessageConverter {    
        public ImplicitJsonMessageConverter( ) {
            super();
        }    
        @Override
        public Object fromMessage(Message message) throws MessageConversionException {
            message.getMessageProperties().setContentType("application/json");
            return super.fromMessage(message);
        }
    }
}
