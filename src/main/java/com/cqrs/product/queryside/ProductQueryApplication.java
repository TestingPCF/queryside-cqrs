package com.cqrs.product.queryside;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.cqrs.product.queryside.config.RabbitmqConfigProduct;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * This is spring boot up class. It also has redis template bean declaration.
 * @author shikhar.a || Mohitkri
 */
@SpringBootApplication
@EnableRabbit
@Import(RabbitmqConfigProduct.class)
public class ProductQueryApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ProductQueryApplication.class, args);
	}

	/**
	 * Autowired JedisConnectionFactory.
	 */
	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;

    /**
	 * This method creates redis template.
     * @return RedisTemplate RedisTemplate
     */
	@Qualifier("redisTemplate")
	@Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory);
        return template;
    }
}
