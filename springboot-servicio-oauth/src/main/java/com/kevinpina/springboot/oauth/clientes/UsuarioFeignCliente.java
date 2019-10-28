package com.kevinpina.springboot.oauth.clientes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kevinpina.springboot.commons.usuarios.models.entity.Usuario;

@FeignClient(name = "servicio-usuarios")
public interface UsuarioFeignCliente {

	@GetMapping("/usuariocrud/search/findByUsername")
	public Usuario findByUsername(@RequestParam String username);
	
}
