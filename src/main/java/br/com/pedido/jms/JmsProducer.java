package br.com.pedido.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import br.com.pedido.domain.Compra;

@Component
public class JmsProducer {
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Value("${jms.queue.destination}")
	String destinationQueue;
	
	public void send(final Compra compra){
		//System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","*");
		jmsTemplate.convertAndSend(destinationQueue, compra);
	}
}
