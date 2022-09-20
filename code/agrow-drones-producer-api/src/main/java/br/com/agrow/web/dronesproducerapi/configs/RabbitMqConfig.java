package br.com.agrow.web.dronesproducerapi.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
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

	@Bean
	public FanoutExchange fanout() {
		return new FanoutExchange("agrow.drones.exchange");
	}

	@Bean
	public Queue dronesSoloDbQueue() {
		return new Queue("agrow.drones.solo", true);
	}

	@Bean
	public Binding binding(Queue dronesSoloDbQueue, FanoutExchange fanout) {
		return BindingBuilder.bind(dronesSoloDbQueue).to(fanout);
	}
}
