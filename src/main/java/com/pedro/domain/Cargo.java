package com.pedro.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CARGOS")
public class Cargo extends GenericDomain<Long> {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Informe um nome")
	@Size(min = 3, max = 60, message = "O nome do cargo deve estar entre {min} e {max}")
	@Column(name = "nome", nullable = false, unique = true, length = 60)
	private String nome;
	
	@NotNull(message = "Selecione o departamento")
	@ManyToOne
	@JoinColumn(name = "id_departamento")
	private Departamento departamento;
	
	@OneToMany(mappedBy = "cargo")
	private List<Funcionario> funcionarios;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	
}
