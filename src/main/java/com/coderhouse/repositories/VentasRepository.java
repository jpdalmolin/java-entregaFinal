package com.coderhouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhouse.models.Venta;

public interface VentasRepository  extends JpaRepository<Venta, Long> {}