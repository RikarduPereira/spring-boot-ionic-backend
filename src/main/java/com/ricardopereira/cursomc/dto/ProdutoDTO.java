package com.ricardopereira.cursomc.dto;

import java.io.Serializable;

import com.ricardopereira.cursomc.domain.Produto;

public class ProdutoDTO implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private Integer Id;
	private String nome;
	private double preço;
	
	public ProdutoDTO() {
		
	}

	public ProdutoDTO(Produto obj) {
	
		Id = obj.getId();
		nome = obj.getNome();
		preço = obj.getPreço();
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getnome() {
		return nome;
	}

	public void setnome(String nome) {
		nome = nome;
	}

	public double getPreço() {
		return preço;
	}

	public void setPreço(double preço) {
		this.preço = preço;
	}
}
