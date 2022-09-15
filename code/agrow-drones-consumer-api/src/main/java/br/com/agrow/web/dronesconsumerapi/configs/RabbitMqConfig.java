package br.com.agrow.web.dronesconsumerapi.configs;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

	@Value("${spring.rabbitmq.host}")
	private String rabbitMqHost;

	@Value("${spring.rabbitmq.port}")
	private Integer rabbitMqPort;

	@Bean
	public CachingConnectionFactory connectionFactory() {
		return new CachingConnectionFactory(rabbitMqHost, rabbitMqPort);
	}

	@Bean
	public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
		return new RabbitTemplate(connectionFactory);
	}
}
