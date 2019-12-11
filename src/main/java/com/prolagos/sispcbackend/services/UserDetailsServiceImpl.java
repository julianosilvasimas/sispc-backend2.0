package com.prolagos.sispcbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.prolagos.sispcbackend.domain.Cad_SisPC_Usuarios;
import com.prolagos.sispcbackend.repositories.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 	@Autowired
	private UsuarioRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { 
		Cad_SisPC_Usuarios uso = repo.findByEmail(email);
		if (uso == null) {
			throw new UsernameNotFoundException("usuario não foi encontrado");
		}
		return new User(uso.getEmail(), uso.getSenha(), uso.getAuthorities());
		//return null;
	}
		
}
