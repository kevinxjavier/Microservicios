package com.kevinpina.springboot.item.models;

import java.util.Date;

@Deprecated // La coloco deprecada porque ahora usamos el Producto.class de la Lib
			// no usamos @EntityScan en la clase principal SpringbootServicioItemApplication
			// porque no necesitamos cargar Producto como Bean @Entity como en el proyecto
			// servicio-productos. 
public class Producto {

	private Long id;
	private String nombre;
	private Double precio;
	private Date fechaCreacion;
	private Integer port;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

}
