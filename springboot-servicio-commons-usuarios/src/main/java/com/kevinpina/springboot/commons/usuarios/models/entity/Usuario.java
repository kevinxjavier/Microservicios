package com.kevinpina.springboot.commons.usuarios.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT si se usa, MySQL, H2, SQLServer
	private Long id;

	@Column(unique = true, length = 20)
	private String username;

	@Column(length = 60)
	private String password;

	private Boolean enabled;
	private String nombre;
	private String apellido;

	@Column(unique = true, length = 100)
	private String email;
	
	@ManyToMany(fetch = FetchType.LAZY)	// @ManyToMany Por debajo creara una tabla intermedia usuarios_roles
											// FetchType.EAGER trae los usuarios con los Roles
											// FetchType.LAZY solo se trae usuarios y los roles solo se llaman cuando se invoca getRoles()
	@JoinTable(name = "usuarios_to_roles", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_rol"), uniqueConstraints = {@UniqueConstraint(columnNames = {"id_usuario", "id_rol"})})
		// @JoinTable cambia el nombre de la tabla intermedia a usuarios_to_roles
		// joinColumns cambia el nombre del campo foraneo de la tabla intermedia a id_usuario en lugar de usuarios_id por defecto
		// inverseJoinColumns cambia el nombre del campo foraneo de la tabla intermedia a id_rol en lugar de roles_id por defecto
		// uniqueConstraints hacemos una restriccion unica usando campos compuestos 
	private List<Rol> roles;
	
	private Integer intentos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public Integer getIntentos() {
		return intentos;
	}

	public void setIntentos(Integer intentos) {
		this.intentos = intentos;
	}

}
