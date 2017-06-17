package br.com.pedido.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.pedido.domain.Compra;
import br.com.pedido.domain.Item;
import br.com.pedido.jms.JmsConsumer;
import br.com.pedido.jms.JmsProducer;

@Controller
@RequestMapping("/compra")
public class WebController {
	
	@Autowired
	JmsProducer jmsProducer;
	
	@Autowired
	JmsConsumer jmsConsumer;
	
	@RequestMapping(value="/produce")
	public ResponseEntity<?> produce(){
		try{
			System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","*");
			System.out.println("--- Criando mensagem de teste ---");
	        
	        Compra compra = new Compra();
	        compra.setNome("Luiz");
	        compra.setNumeroCartao("8524553");
	        
	        Item item1 = new Item();
	        item1.setNome("Livro");
	        item1.setPreco(50);
	        item1.setQuantidade(3);
	        
	        Item item2 = new Item();
	        item2.setNome("TV");
	        item2.setPreco(2000);
	        item2.setQuantidade(3);
	        
	        Item item3 = new Item();
	        item3.setNome("Geladeira");
	        item3.setPreco(700);
	        item3.setQuantidade(2);
	        
	        compra.getItens().add(item1);
	        compra.getItens().add(item2);
	        compra.getItens().add(item3);
	 
			jmsProducer.send(compra);
	        return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	/**
	 * @return
	 */
	/*@RequestMapping(value="/receive")
	public ResponseEntity<?> receive(){
		try{
			System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","*");
			jmsConsumer.receive();
			return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}*/
}