package com.coderhouse.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Ventas")
public class Venta {

	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncremental
	private Long id;

	@Column(nullable = false)
	private String nombre;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "venta_cliente", 
			joinColumns = @JoinColumn(name = "venta_id"), 
			inverseJoinColumns = @JoinColumn(
					name = "cliente_id"))
	@JsonIgnore
	private List<Cliente> clientes = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER)
	private Producto producto;

	public Venta() {
		super();
	}

	public Venta(String nombre, Producto producto) {
		super();
		this.nombre = nombre;
		this.producto = producto;
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

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "Venta [id=" + id + ", nombre=" + nombre + ", producto=" + producto + "]";
	}

	

}