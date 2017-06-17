package br.com.compra.jms;

import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import br.com.pedido.domain.Compra;
import br.com.pedido.domain.Item;

@Component
public class JmsConsumer implements MessageListener{
	
	/*public Compra receive(){
		System.out.println("recebendo mensagem - inicio");
		//System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","*");
		Message<Compra> receiveAndConvert = (Message<Compra>) jmsTemplate.receive(destinationQueue);
		System.out.println(receiveAndConvert);
		System.out.println("recebendo mensagem - fim");
		return null; 
	}*/

	@JmsListener(destination = "${jms.queue.destination}")
	@Override
	public void onMessage(javax.jms.Message message) {
		ObjectMessage objMsg = (ObjectMessage) message;
		try {
            System.out.println("--- Recebendo mensagem --- ");
            
            Compra compra = (Compra) objMsg.getObject();
            fecharCompra(compra);
         } catch (JMSException e) {
            e.printStackTrace();
        }
	}
	
	public Compra fecharCompra(Compra compra){
		
		System.out.println(compra.getNome());
        System.out.println(compra.getNumeroCartao());
        
        System.out.println("--- Itens --- ");
        
        float precoTotal = 0;
        for (Item item : compra.getItens()) {
        	System.out.println(item.getNome());
        	precoTotal += item.getPreco() * item.getQuantidade();
        }
        System.out.println("Total: " + precoTotal);
        

		return compra;
	}
}
