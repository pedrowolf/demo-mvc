package com.pedro.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class UsuarioRole extends GenericDomain<Long> implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	private String roleName;

	@ManyToMany(mappedBy = "roles")
	private List<Usuario> usuarios;

	@Override
	public String getAuthority() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}
}
