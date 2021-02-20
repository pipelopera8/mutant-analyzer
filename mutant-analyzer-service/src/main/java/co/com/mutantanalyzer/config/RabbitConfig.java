package co.com.mutantanalyzer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.mutantanalyzer.rabbitmq.Sender;

@Configuration
public class RabbitConfig {

	@Bean
	public Queue fistPartQueue() {
		return new Queue("mutant_queue");
	}

	@Bean
	public Sender sender() {
		return new Sender();
	}
}
