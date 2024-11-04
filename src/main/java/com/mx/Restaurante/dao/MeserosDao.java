package com.mx.Restaurante.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.Restaurante.model.Meseros;

public interface MeserosDao extends JpaRepository<Meseros, Long>  {

}
