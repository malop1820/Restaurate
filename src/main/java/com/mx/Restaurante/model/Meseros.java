package com.mx.Restaurante.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*CREATE TABLE MESEROS(
ID NUMBER PRIMARY KEY,
NOMBRE VARCHAR2(70) NOT NULL,
APP VARCHAR2(70) NOT NULL,
APM VARCHAR2(70),
SUELDO NUMBER NOT NULL
);*/

@Entity
@Table(name = "MESEROS")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Meseros {
	@Id
	private Long id;
	private String nombre;
	private String app;
	private String apm;
	private Float sueldo;
	
	@OneToMany(mappedBy = "mesero", cascade = CascadeType.ALL)
	@JsonIgnore // Sirve para omitir una propiedad o lista de propiedades en mi Json
	List<Mesas> lista = new ArrayList<Mesas>(); // Esta variable no se agrega en lo demas
	
	public String getNombreCompleto() {
        return this.nombre + " " + this.app + " " + this.apm; // Concatenando nombre, app y apm
    }
}
