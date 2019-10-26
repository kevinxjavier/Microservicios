package com.kevinpina.springboot.usuarios;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import com.kevinpina.springboot.usuarios.models.entity.Rol;
import com.kevinpina.springboot.usuarios.models.entity.Usuario;

/**
 * 
 * Esta clase es si queremos mostrar los id's o claves primarias de las Tablas usuarios y roles
 * en las respuestas de los servicios Rest de UsuarioDao.java
 *
 */
@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Usuario.class, Rol.class);
	}

}
