package co.com.mutantanalyzer.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class Sender {
	
	@Autowired
	private RabbitTemplate template;

	@Autowired
	private Queue queue;

	@Scheduled(fixedDelay = 1000, initialDelay = 500)
	public void send(byte[] data) {
		this.template.convertAndSend(queue.getName(), data);
	}
}
