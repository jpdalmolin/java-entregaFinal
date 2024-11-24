package com.coderhouse.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Schema(description="Modelo de Productos")
@Entity
@Table(name = "Productos")
public class Producto {
	
	@Schema(description="Id Del Producto",requiredMode=Schema.RequiredMode.REQUIRED )
	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncremental
	private Long id;
	@Schema(description="Nombre Del Producto",requiredMode=Schema.RequiredMode.REQUIRED )
	@Column(nullable = false)
	private String nombre;

	@OneToMany(mappedBy = "producto", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Venta> ventas = new ArrayList<>();

	public Producto() {
		super();
	}

	public Producto(String nombre) {
		super();
		this.nombre = nombre;
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

	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + "]";
	}

}