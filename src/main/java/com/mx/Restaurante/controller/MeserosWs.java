package com.mx.Restaurante.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.Restaurante.model.Meseros;
import com.mx.Restaurante.service.MeserosImp;



@RestController
@RequestMapping(path = "MeserosWs")
@CrossOrigin
public class MeserosWs {

	@Autowired
	MeserosImp meseroImp;
	
	// http://localhost:9000/MeserosWs/listar
	@GetMapping(path = "listar")
	public List<Meseros>listar(){
		return meseroImp.listar();
	}
	
	// http://localhost:9000/MeserosWs/guardar
		@PostMapping(path = "guardar")
		public ResponseEntity<?> guardar(@RequestBody Meseros mesero) {
			String response = meseroImp.guardar(mesero);

			if (response.equals("idMeseroExiste"))
				return new ResponseEntity<>("No se guardo, ese idMesero ya existe", HttpStatus.OK);
			else if (response.equals("nombreCompletoExiste"))
				return new ResponseEntity<>("No se guardo, ese nombreCompleto ya existe", HttpStatus.OK);
			else
				return new ResponseEntity<>(mesero, HttpStatus.CREATED);
		}
		
		// http://localhost:9000/MeserosWs/buscar
		@PostMapping(path = "buscar")
		public Meseros buscar(@RequestBody Meseros mesero) {
			return meseroImp.buscar(mesero.getId());
		}
		
		// http://localhost:9000/MeserosWs/editar
		@PostMapping(path = "editar")
		public ResponseEntity<?> editar(@RequestBody Meseros mesero) {
			boolean response = meseroImp.editar(mesero);

			if (response == false)
				return new ResponseEntity<>("Ese registro no existe", HttpStatus.OK);
			else
				return new ResponseEntity<>(mesero, HttpStatus.CREATED);
		}
		// http://localhost:9000/MeserosWs/eliminar
				@PostMapping(path = "eliminar")
				public ResponseEntity<String> eliminar(@RequestBody Meseros mesero) {
					boolean response = meseroImp.eliminar(mesero.getId());

					if (response == false)
						return new ResponseEntity<>("Ese registro no existe", HttpStatus.OK);
					else
						return new ResponseEntity<>("Se elimino con exito", HttpStatus.OK);
				}

}
