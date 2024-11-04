package com.mx.Restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mx.Restaurante.dao.MeserosDao;
import com.mx.Restaurante.model.Meseros;



@Service
public class MeserosImp {
	@Autowired
	MeserosDao meseroDao;
	
	@Transactional(readOnly = true)
	public List<Meseros>listar(){
		return meseroDao.findAll();
	}
	
	// Validar: idModelo, nombre no exista, idMarca exista, guardar
		@Transactional
		public String guardar(Meseros mesero) {

			String respuesta = "";
			boolean banderaMod = false;


					for (Meseros mod : meseroDao.findAll()) {

						if (mod.getId().equals(mesero.getId())) {
							// Que idMesero ya existe mesero
							respuesta = "idMeseroExiste";
							banderaMod = true;
							break;
						} else if (mod.getNombreCompleto().equals(mesero.getNombreCompleto())) {
				            respuesta = "nombreCompletoExiste";
				            banderaMod = true;
				            break;
				        }
						break;
					}
				    if (!banderaMod) {
				        meseroDao.save(mesero); 
				        respuesta = "guardado"; 
				    }
					return respuesta;
		}
		
		@Transactional(readOnly = true)
		public Meseros buscar(Long id) {
			return meseroDao.findById(id).orElse(null);
		}
		
		@Transactional
		public boolean editar(Meseros mesero) {

			boolean bandera = false;

			for (Meseros m : meseroDao.findAll()) {
				if (m.getId().equals(mesero.getId())) {
					meseroDao.save(mesero);
					bandera = true;
					break;
				}
			}
			return bandera;
		}
		
		// Validar que el idExista para eliminar
		@Transactional
		public boolean eliminar(Long id) {
			boolean bandera = false;
			for (Meseros m : meseroDao.findAll()) {
				if (m.getId().equals(id)) {
					meseroDao.deleteById(id);
					// marcasDao.delete(m);
					bandera = true;
					break;
				}
			}
			return bandera;
		}
				
}
