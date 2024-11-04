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
import com.mx.Restaurante.model.Mesas;
import com.mx.Restaurante.service.MesasImp;



@RestController
@RequestMapping(path = "MesasWs")
@CrossOrigin
public class MesasWs {

	@Autowired
	MesasImp mesaImp;

	// http://localhost:9000/MesasWs/listar
	@GetMapping(path = "listar")
	public List<Mesas> listar() {
		return mesaImp.listar();
	}
	
	// http://localhost:9000/MesasWs/guardar
		@PostMapping(path = "guardar")
		public ResponseEntity<?> guardar(@RequestBody Mesas mesa) {
			String response = mesaImp.guardar(mesa);

			if (response.equals("idMesaExiste"))
				return new ResponseEntity<>("No se guardo, ese idMesa ya existe", HttpStatus.OK);
			else if (response.equals("mesaNumExiste"))
				return new ResponseEntity<>("No se guardo, ese numero de mesa ya existe", HttpStatus.OK);
			else if (response.equals("idMeserNoExiste"))
				return new ResponseEntity<>("No se guardo, ese idMesero no existe", HttpStatus.OK);
			else
				return new ResponseEntity<>(mesa, HttpStatus.CREATED);
		}
		

		// http://localhost:9000/MesasWs/buscar
			@PostMapping(path = "buscar")
			public Mesas buscar(@RequestBody Mesas mesa) {
				return mesaImp.buscar(mesa.getId());
			}
			
			
			// http://localhost:9000/MesasWs/buscar
			 @PostMapping("/editar")
			    public ResponseEntity<?> editar(@RequestBody Mesas mesa) {
			        String respuesta = mesaImp.editar(mesa);

			        if (respuesta.equals("idMeseroNoExiste")) {
			            return new ResponseEntity<>("No se editó, el idMesero no existe", HttpStatus.NOT_FOUND);
			        } else if (respuesta.equals("idMesaNoExiste")) {
			            return new ResponseEntity<>("No se editó, el idMesa no existe", HttpStatus.NOT_FOUND);
			        } else if (respuesta.equals("mesaNumExiste")) {
			            return new ResponseEntity<>("No se editó, el numeroMesa ya existe", HttpStatus.CONFLICT);
			        } else if (respuesta.equals("guardado")) {
			            return new ResponseEntity<>(mesa, HttpStatus.OK);
			        } else {
			            return new ResponseEntity<>("Error desconocido", HttpStatus.INTERNAL_SERVER_ERROR);
			        }
			    }
			 
			// http://localhost:9000/MesasWs/eliminar
				@PostMapping(path = "eliminar")
				public ResponseEntity<String> eliminar(@RequestBody Mesas mesa) {
					boolean response = mesaImp.eliminar(mesa.getId());

					if (response == false)
						return new ResponseEntity<>("Ese registro no existe", HttpStatus.OK);
					else
						return new ResponseEntity<>("Se elimino con exito", HttpStatus.OK);
				}
}
