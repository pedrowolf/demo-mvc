package com.pedro.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ENDERECOS")
public class Endereco extends GenericDomain<Long> {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Preencha o logradouro")
	@Size(min = 3, max = 255, message = "O campo logradouro deve ter entre {min} e {max} caracteres")
	@Column(nullable = false)
	private String logradouro;

	@NotBlank(message = "Preencha o bairro")
	@Size(min = 3, max = 255, message = "O campo bairro deve ter entre {min} e {max} caracteres")
	@Column(nullable = false)
	private String bairro;

	@NotBlank(message = "Preencha a cidade")
	@Size(min = 3, max = 255, message = "O campo cidade deve ter entre {min} e {max} caracteres")
	@Column(nullable = false)
	private String cidade;

	@NotNull(message = "Selecione uma UF")
	@Column(nullable = false, length = 2)
	@Enumerated(EnumType.STRING)
	private UF uf;

	@NotBlank(message = "Preencha o cep")
	@Size(min = 3, max = 255, message = "O campo cep deve ter entre {min} e {max} caracteres")
	@Column(nullable = false, length = 9)
	private String cep;

	@NotNull(message = "Preencha o número")
	@Digits(integer = 5, fraction = 0)
	@Column(nullable = false, length = 5)
	private Integer numero;

	@NotBlank(message = "Preencha o complemento")
	@Size(min = 3, max = 255, message = "O campo complemento deve ter entre {min} e {max} caracteres")
	private String complemento;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
}
