package co.com.mutantanalyzer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.mutantanalyzer.handler.MutantHandler;

@Configuration
public class RabbitConfig {

	@Bean
	public MutantHandler receiver() {
		return new MutantHandler();
	}
}
