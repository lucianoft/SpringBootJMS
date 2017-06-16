package br.com.pedido.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Compra implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String nome;
	private String numeroCartao;
	private List<Item> itens = new ArrayList<Item>();
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNumeroCartao() {
		return numeroCartao;
	}
	
	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	
	public List<Item> getItens() {
		return itens;
	}
	
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	
	

}
