package com.kevinpina.springboot.usuarios.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.kevinpina.springboot.commons.usuarios.models.entity.Usuario;

@RepositoryRestResource(path = "usuariocrud")	// Exporta el CRUD y los metodos personalizados que tengamos
public interface UsuarioDao extends PagingAndSortingRepository<Usuario, Long>{
	
	/** 
	 *	Estos metodos hacen todos lo mismo pero de diferentes maneras.
	 */
	
	/*
	 * Usando esta nomenclatura de nombre de metodo (Query Method Name) findBy[NombreCampo](nombreCampo) 
	 * se ejecuta la consultara la consulta JPQL: SELECT u FROM usuario WHERE u.username = ?1
	 * 
	 * Si se quiere usar mas campos para la busqueda seria: 
	 * 	findBy[NombreCampo1]And[NombreCampo2](nombreCampo1, nombreCampo2)
	 * 	findBy[NombreCampo1]Or[NombreCampo2](nombreCampo1, nombreCampo2)
	 * 
	 * Info: https://docs.spring.io/spring-data/jpa/docs/2.2.0.RELEASE/reference/html/#jpa.query-methods
	 */
	public Usuario findByUsername(String username);				//	http://localhost:8090/api/usuarios/usuariocrud/search/findByUsername?username=16870609
	
	@RestResource(path = "buscar-correo")
	public Usuario findByEmail(@Param("correo") String email);	//	http://localhost:8090/api/usuarios/usuariocrud/search/buscar-correo?correo=et.magnis@porttitor.co.uk
	
	// Usuario es el nombre de la clase y username es un atributo de la clase
	@Query("SELECT u FROM Usuario u WHERE u.username = ?1")		//	http://localhost:8090/api/usuarios/usuariocrud/search/obtenerPorUsername?username=16870609
																	// podemos customizar el endpoint tambien con @RestResource y @Param
	public Usuario obtenerPorUsername(String username);

//	// Usando consulta SQL nativas 
//	@Query(value = "SELECT * FROM usuarios WHERE username = ?", nativeQuery = true)	
//	public Usuario obtenerByUsername(String username);

}
