package br.com.compra.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import br.com.pedido.domain.Compra;

@Component
public class JmsConsumer {
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Value("${jms.queue.destination}")
	String destinationQueue;
	
	@JmsListener(destination = "${jms.queue.destination}")
	public Compra receive(){
		System.out.println("recebendo mensagem - inicio");
		//System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","*");
		Object receiveAndConvert = jmsTemplate.receiveAndConvert(destinationQueue);
		System.out.println(receiveAndConvert);
		System.out.println("recebendo mensagem - fim");
		return (Compra)receiveAndConvert; 
	}
}
