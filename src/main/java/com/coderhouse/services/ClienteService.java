package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dtos.InscripcionDTO;
import com.coderhouse.models.Cliente;
import com.coderhouse.models.Venta;
import com.coderhouse.repositories.ClienteRepository;
import com.coderhouse.repositories.VentasRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private VentasRepository ventaRepository;

	public List<Cliente> getAllClientes() {
		return clienteRepository.findAll();
	}

	public Cliente findById(Long id) {
		return clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
	}

	@Transactional
	public Cliente saveCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Transactional
	public Cliente updateCliente(Long id, Cliente clienteDetails) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

		cliente.setNombre(clienteDetails.getNombre());
		cliente.setApellido(clienteDetails.getApellido());

		if (clienteDetails.getDni() != 0) {
			cliente.setDni(clienteDetails.getDni());
		}

		if (clienteDetails.getLegajo() != null && !clienteDetails.getLegajo().isEmpty()) {
			cliente.setLegajo(clienteDetails.getLegajo());
		}

		return clienteRepository.save(cliente);
	}

	public void deleteCliente(Long id) {
		if (!clienteRepository.existsById(id)) {
			throw new IllegalArgumentException("Cliente no encontrado");
		}
		clienteRepository.deleteById(id);

	}

	@Transactional
	public Cliente inscribirClienteAVentas(InscripcionDTO inscripcionDTO) {
		Cliente cliente = clienteRepository.findById(inscripcionDTO.getClienteId())
				.orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
		for (Long ventaId : inscripcionDTO.getVentaIds()) {
			Venta venta = ventaRepository.findById(ventaId)
					.orElseThrow(() -> new IllegalArgumentException("Venta no encontrada"));
			cliente.getVentas().add(venta);
			venta.getClientes().add(cliente);
			ventaRepository.save(venta);
		}
		
		return clienteRepository.save(cliente);
		
	}
}
