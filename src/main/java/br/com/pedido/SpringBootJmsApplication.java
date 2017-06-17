package br.com.pedido;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms()
public class SpringBootJmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJmsApplication.class, args);
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		ConnectionFactory factory= new ActiveMQConnectionFactory();
		((ActiveMQConnectionFactory)factory).setTrustAllPackages(true);
		
		return factory;
	}
}