package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.Producto;
import com.coderhouse.models.Venta;
import com.coderhouse.repositories.ProductoRepository;
import com.coderhouse.repositories.VentasRepository;

import jakarta.transaction.Transactional;

@Service
public class VentaService {

	@Autowired
	private VentasRepository ventaRepository;

	@Autowired
	private ProductoRepository productoRepository;

	public List<Venta> getAllVentas() {
		return ventaRepository.findAll();
	}

	public Venta getVentaById(Long id) {
		return ventaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Venta no encontrada"));

	}

	@Transactional
	public Venta createVenta(Venta venta) {
		return ventaRepository.save(venta);
	}

	@Transactional
	public Venta updateVenta(Long id, Venta ventaDetails) {
		Venta venta = ventaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Venta no encontrada"));
		venta.setNombre(ventaDetails.getNombre());
		return ventaRepository.save(venta);

	}

	public void deleteVenta(Long id) {
		if (ventaRepository.existsById(id)) {
			ventaRepository.deleteById(id);
		} else {
			throw new IllegalArgumentException("Venta no encontrada");
		}
	}

	@Transactional
	public Venta asignarProductoAVenta(Long ventaId, Long productoId) {
		Producto producto = productoRepository.findById(productoId)
				.orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

		Venta venta = ventaRepository.findById(ventaId)
				.orElseThrow(() -> new IllegalArgumentException("Venta no encontrada"));

		venta.setProducto(producto);
		return ventaRepository.save(venta);

	}

}
