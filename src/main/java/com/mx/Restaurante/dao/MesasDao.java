package com.mx.Restaurante.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.Restaurante.model.Mesas;

public interface MesasDao extends JpaRepository<Mesas, Long>{

}
