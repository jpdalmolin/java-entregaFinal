package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;


import com.coderhouse.dtos.AsignacionProductoDTO;
import com.coderhouse.models.Venta;
import com.coderhouse.services.VentaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Gestion de Ventas", description="Endpoints para gestionar Ventas")
@RestController
@RequestMapping("/api/ventas")
public class VentaController {

	@Autowired
	private VentaService ventaService;
	
	@Operation(summary="Obtener lista de Ventas")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de Ventas obtenida correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class)) }),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
	})
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Venta>> getAllVentas() {
		try {
			List<Venta> ventas = ventaService.getAllVentas();
			return ResponseEntity.ok(ventas);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary="Obtener Ventas por ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Venta encontrada correctamente", content = @Content),
			@ApiResponse(responseCode = "404", description = "Venta no encontrada", content = @Content) })
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Venta> getVentaById(@PathVariable Long id) {
		try {
			Venta venta = ventaService.getVentaById(id);
			return ResponseEntity.ok(venta);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary="Crear Venta")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Venta creada correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class)) }),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
	})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Venta> createVenta(@RequestBody Venta venta) {
		try {
			Venta createdVenta = ventaService.createVenta(venta);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdVenta);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Operation(summary="Editar Venta")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Venta editada correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class)) }),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
	})
	@PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Venta> updateVenta(@PathVariable Long id, @RequestBody Venta ventaDetails) {
		try {
			Venta updatedVenta = ventaService.updateVenta(id, ventaDetails);
			return ResponseEntity.ok(updatedVenta);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Operation(summary="Borrar Venta")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Venta eliminada correctamente", content = @Content),
			@ApiResponse(responseCode = "404", description = "Venta no encontrada", content = @Content) })
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteVenta(@PathVariable Long id) {
		try {
			ventaService.deleteVenta(id);
			return ResponseEntity.noContent().build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Operation(summary="Asignar Producto a Venta")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Producto asignado correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class)) }),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@PostMapping(value = "/asignar-categoria", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Venta> asignarProductoAVenta(@RequestBody AsignacionProductoDTO asignacionDTO){
		try {
			
			Venta ventaActualizado = ventaService.asignarProductoAVenta(
					asignacionDTO.getVentaId(),
					asignacionDTO.getProductoId()
					);
			return ResponseEntity.ok(ventaActualizado);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
}
