package com.generation.mdteixeira.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produtos")
public class Produto {

	@Id // define as primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // define o Auto_Increment
	private Long id;

	@NotBlank(message = "O nome é obrigatório!") // não deixa ser vazio
	@Size(min = 5, max = 100, message = "O nome deve ter entre 5 e 100 caracteres.") // define o tamanho
	private String nome;

	@Column(length = 1000)
	private String descricao;
	
	@NotNull
	@NotBlank(message = "O console é obrigatório!") // não deixa ser vazio
	private String console;

	@NotNull
	private float preco;

	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}


	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
