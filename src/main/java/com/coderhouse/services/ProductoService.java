package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.Producto;
import com.coderhouse.repositories.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductoService {

	@Autowired
	private ProductoRepository productoRepository;

	public List<Producto> findAll() {
		return productoRepository.findAll();
	}

	public Producto getProductoById(Long id) {
		return productoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

	}

	public boolean existsById(Long id) {
        return productoRepository.existsById(id);
    }
	
	@Transactional
	public Producto createProducto(Producto producto) {
		return productoRepository.save(producto);
	}

	@Transactional
	public Producto updateProducto(Long id, Producto productoDetails) {
		Producto producto = productoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Producto no fue encontrado"));
		producto.setNombre(productoDetails.getNombre());
		return productoRepository.save(producto);

	}

	public void deleteById(Long id) {
		productoRepository.deleteById(id);
	}

}
