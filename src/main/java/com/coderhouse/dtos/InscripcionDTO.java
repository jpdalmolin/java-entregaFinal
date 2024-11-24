package com.coderhouse.dtos;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="Modelo de Asignacion Venta a cliente DTO")
public class InscripcionDTO {

	@Schema(description="Cliente ID", requiredMode=Schema.RequiredMode.REQUIRED,example="1")
	private Long clienteId;
	@Schema(description="Venta ID", requiredMode=Schema.RequiredMode.REQUIRED,example="1")
	private List<Long> ventaIds;

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public List<Long> getVentaIds() {
		return ventaIds;
	}

	public void setVentaIds(List<Long> ventaIds) {
		this.ventaIds = ventaIds;
	}

}
