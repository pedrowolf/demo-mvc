package com.pedro.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.pedro.domain.Usuario;
import com.pedro.repository.UsuarioRepository;

@Repository
public class ImplementsUserDetailsService  implements UserDetailsService{

	@Autowired
	private UsuarioRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = repo.findByLogin(username);
		if(user == null) {
			throw new UsernameNotFoundException("Usuario não encontrado!");
		}
		return user;
	}

}
