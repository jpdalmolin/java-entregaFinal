package com.coderhouse.models;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
	
	private String id;
	private String nombre;
	private String apellido;
	private String email;
	private String creado;
	
}
