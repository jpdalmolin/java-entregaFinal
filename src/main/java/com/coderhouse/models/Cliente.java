package com.coderhouse.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Schema(description="Modelo de Alumno")
@Entity
@Table(name = "Clientes")
public class Cliente {
	@Schema(description="Id Del Cliente",requiredMode=Schema.RequiredMode.REQUIRED )
	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncremental
	private Long id;
	@Schema(description="Nombre Del Cliente",requiredMode=Schema.RequiredMode.REQUIRED )
	private String nombre;
	@Schema(description="Apellido Del Cliente",requiredMode=Schema.RequiredMode.REQUIRED )
	private String apellido;
	@Schema(description="DNI Del Cliente",requiredMode=Schema.RequiredMode.REQUIRED )
	@Column(unique = true, nullable = false)
	private int dni;
	@Schema(description="Legajo Del Cliente",requiredMode=Schema.RequiredMode.REQUIRED )
	@Column(unique = true, nullable = false)
	private String legajo;

	@Schema(description="Lista de ventas del cliente ")
	@ManyToMany(
				mappedBy = "clientes", 
				fetch = FetchType.EAGER
			)
	private List<Venta> ventas = new ArrayList<>();
	@Schema(description="Fecha de ventas del cliente ")
	private LocalDateTime createdAt = LocalDateTime.now();

	
	public Cliente() {
		super();		
	}
		
	public Cliente(String nombre, String apellido, int dni, String legajo) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.legajo = legajo;
	}

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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String cliente() {
		return legajo;
	}
	
	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}

	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", legajo="
				+ legajo + ", ventas=" + ventas + ", createdAt=" + createdAt + "]";
	}

	

}