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

	@Transient
	private Integer port;

	@Column(name = "fecha_creacion")
	@Temporal(TemporalType.DATE) // Formato Fecha
	private Date fechaCreacion;

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
