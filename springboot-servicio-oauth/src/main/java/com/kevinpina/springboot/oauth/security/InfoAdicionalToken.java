package com.kevinpina.springboot.oauth.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.kevinpina.springboot.commons.usuarios.models.entity.Usuario;
import com.kevinpina.springboot.oauth.services.IUsuarioService;


/**
 * Clase para agregar Informacion Adicional o Claims al Token 
 * se usa en la clase AutorizationServerConfig
 */
@Component
public class InfoAdicionalToken implements TokenEnhancer {
	
	@Autowired
	private IUsuarioService iUsuarioService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<>();
		
		Usuario usuario = iUsuarioService.findByUsername(authentication.getName());
		
		info.put("nombre", usuario.getNombre());
		info.put("apellido", usuario.getApellido());
		info.put("correo", usuario.getEmail());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
