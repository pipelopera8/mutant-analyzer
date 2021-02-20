package co.com.mutantanalyzer.rabbitmq;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import co.com.mutantanalyzer.exception.MutantAnalyzerExceptionHandler;

@SpringBootTest
class SenderTest {

	@Test
	void contextLoads() {
		MockitoAnnotations.initMocks(this);
	}
	
    @Mock
    private MessageSource messageSource;
    
    @Mock
	private RabbitTemplate template;

    @Mock
    private Queue queue;

    @InjectMocks
    @Spy
    private Sender sender;

    @Test
    public void sendFail() throws MutantAnalyzerExceptionHandler {
        sender.send(null);
    }
	
}
