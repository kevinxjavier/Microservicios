package com.kevinpina.springboot.oauth.services;

import com.kevinpina.springboot.commons.usuarios.models.entity.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername(String username);

	public Usuario update(Usuario usuario, Long id);

}
