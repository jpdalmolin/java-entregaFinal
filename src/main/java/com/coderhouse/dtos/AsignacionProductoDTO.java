package com.coderhouse.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="Modelo de Asignacion Productos DTO")
public class AsignacionProductoDTO {
	
	@Schema(description="Producto ID", requiredMode=Schema.RequiredMode.REQUIRED,example="1231241")
	private Long productoId;
	@Schema(description="Venta ID", requiredMode=Schema.RequiredMode.REQUIRED,example="1231241")
	private Long ventaId;
	public Long getProductoId() {
		return productoId;
	}
	public void setProductoId(Long productoId) {
		this.productoId = productoId;
	}
	public Long getVentaId() {
		return ventaId;
	}
	public void setVentaId(Long ventaId) {
		this.ventaId = ventaId;
	}
	
	
}
