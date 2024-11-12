package com.coderhouse.dtos;

import java.util.List;

public class InscripcionDTO {

	private Long clienteId;
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
