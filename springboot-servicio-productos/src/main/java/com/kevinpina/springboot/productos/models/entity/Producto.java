package com.kevinpina.springboot.productos.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Deprecated // La coloco deprecada porque ahora usamos el Producto.class de la Lib
			// com.kevinpina.springboot.commons adicionalmente este package esta fuera del contexto 
			// de esta App de SpringBoot ya que no tiene el mismo nombre de paquetes, para que la App 
			// le haga un Scan y cargue los Beans @Entity, @Repository, @Controller, etc, usamos el
			// @EntityScan({"paquete1", "paquete2"}) en la clase principal SpringbootServicioProductosApplication.
@Entity
@Table(name = "productos") // Opcional, sino se especifica toma como nombre de la tabla el nombre de la
							// clase
public class Producto implements Serializable {

	private static final long serialVersionUID = 1L;

	// Si los campos no corresponden a los atributos, se deben mapear con
	// @Column("nombreCampo") los atributos

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Identity = Auto_increment en H2DB
	private Long id;

	private String nombre;

	private double precio;

	@Column(name = "fecha_creacion")
	@Temporal(TemporalType.DATE) // Formato Fecha
	private Date fechaCreacion;

	@Transient // Esto hace que no se Serialize y que no forme parte del campo de la Tabla
	private Integer port;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
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
