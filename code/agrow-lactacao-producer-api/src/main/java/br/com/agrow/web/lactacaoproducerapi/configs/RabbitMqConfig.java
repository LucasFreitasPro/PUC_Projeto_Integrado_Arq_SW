package br.com.agrow.web.lactacaoproducerapi.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
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

	@Bean
	public FanoutExchange fanout() {
		return new FanoutExchange("agrow.lactacao.exchange");
	}

	@Bean
	public Queue lactacaoUnidadeAnimalDbQueue() {
		return new Queue("agrow.lactacoes.unidade.animal", true);
	}

	@Bean
	public Binding binding(Queue lactacaoUnidadeAnimalDbQueue, FanoutExchange fanout) {
		return BindingBuilder.bind(lactacaoUnidadeAnimalDbQueue).to(fanout);
	}
}
