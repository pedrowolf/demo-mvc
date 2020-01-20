package com.pedro.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "DEPARTAMENTOS")
public class Departamento extends GenericDomain<Long> {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Informe um nome")
	@Size(min = 3, max = 60, message = "O nome do departamento deve estar entre {min} e {max}")
	@Column(name = "nome", nullable = false, unique = true, length = 60)
	private String nome;
	
	@OneToMany(mappedBy = "departamento")
	private List<Cargo> cargos;
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}
	
	public List<Cargo> getCargos() {
		return cargos;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void reload(Departamento dep) {
		this.nome = dep.getNome();
		this.cargos = dep.getCargos();
	}
}

