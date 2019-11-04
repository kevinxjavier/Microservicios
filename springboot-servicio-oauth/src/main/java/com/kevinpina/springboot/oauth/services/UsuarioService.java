package com.kevinpina.springboot.oauth.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kevinpina.springboot.commons.usuarios.models.entity.Usuario;
import com.kevinpina.springboot.oauth.clientes.UsuarioFeignCliente;

@Service
public class UsuarioService implements UserDetailsService, IUsuarioService {
	
	private Logger log = LoggerFactory.getLogger(UsuarioService.class) ;

	@Autowired
	private UsuarioFeignCliente usuarioFeignCliente;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioFeignCliente.findByUsername(username);
		
		if (usuario == null) {
			log.error("No se encontro el Usuario '" + username + "' en el Sistema");
			throw new UsernameNotFoundException("No se encontro el Usuario '" + username + "' en el Sistema");
		}
		
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority -> log.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());
		
		log.info("Usuario autenticado: " + username);
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}

	@Override
	public Usuario findByUsername(String username) {
		return usuarioFeignCliente.findByUsername(username);
	}

	@Override
	public Usuario update(Usuario usuario, Long id) {
		return usuarioFeignCliente.update(usuario, id);
	}

}
