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


@Configuration
public class RabbitmqConfigProduct {
	public static final String EXCHANGE_NAME = "productMQ";
	
	public static final String ROUTING_KEY = "productPOC";
	
	public static final String QUEUE_SPECIFIC_NAME = "productQueue";

	
	@Bean
	public TopicExchange mqExchange() {
		return new TopicExchange(EXCHANGE_NAME);
	}

	@Bean
	public Queue appQueueSpecific() {
		return new Queue(QUEUE_SPECIFIC_NAME);
	}

	@Bean
	public Binding declareBindingSpecific() {
		return BindingBuilder.bind(appQueueSpecific()).to(mqExchange()).with(ROUTING_KEY);
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
