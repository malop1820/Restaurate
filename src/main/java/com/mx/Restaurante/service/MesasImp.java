package com.mx.Restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mx.Restaurante.dao.MesasDao;
import com.mx.Restaurante.dao.MeserosDao;
import com.mx.Restaurante.model.Mesas;



@Service
public class MesasImp {
	@Autowired
	MeserosDao meserooDao;

	@Autowired
	MesasDao mesasDao;

	@Transactional(readOnly = true)
	public List<Mesas> listar() {
		return mesasDao.findAll();
	}
	
	@Transactional
	public String guardar(Mesas mesas) {

		String respuesta = "";
		boolean banderaMeser = false;
		boolean banderaMesa = false;
		for (Mesas m : mesasDao.findAll()) {

			if (m.getId().equals(mesas.getMesero().getId())) {
				// Que existe el idMesa
				banderaMeser = true;

				for (Mesas mes : mesasDao.findAll()) {

					if (mes.getId().equals(mesas.getId())) {
						// Que idMesa ya existe
						respuesta = "idMesaExiste";
						banderaMesa = true;
						break;
					} else if (mes.getNum_mesa().equals(mesas.getNum_mesa())) {
						// Que nombreModelo ya existe
						respuesta = "mesaNumExiste";
						banderaMesa = true;
						break;
					}
				}
				break;
			}
		}

		// idMarcaNoexiste
		if (banderaMeser == false) {
			respuesta = "idMeserNoExiste";
			banderaMesa = true;
		} else if (banderaMesa == false) {
			mesasDao.save(mesas);
			respuesta = "guardado";
		}

		return respuesta;
	}
	@Transactional(readOnly = true)
	public Mesas buscar(Long id) {
		return mesasDao.findById(id).orElse(null);
	}
	
	@Transactional
	public String editar(Mesas mesas) {
        // Verificar si el mesero y la mesa existen, y si el número de mesa es único
        boolean meseroExiste = false;
        boolean mesaExiste = false;
        boolean numeroMesaDuplicado = false;

        for (Mesas m : mesasDao.findAll()) {
            if (m.getMesero().getId().equals(mesas.getMesero().getId())) {
                meseroExiste = true;
            }
            if (m.getId().equals(mesas.getId())) {
                mesaExiste = true;
            }
            if (m.getNum_mesa().equals(mesas.getNum_mesa()) && !m.getId().equals(mesas.getId())) {
                numeroMesaDuplicado = true;
            }
        }

        if (!meseroExiste) {
            return "idMeseroNoExiste";
        } else if (!mesaExiste) {
            return "idMesaNoExiste";
        } else if (numeroMesaDuplicado) {
            return "mesaNumExiste";
        }

        // Si no hay conflictos, guardar la mesa
        mesasDao.save(mesas);
        return "guardado";
    }
	
	
	public boolean eliminar(Long id) {
		boolean bandera = false;
		for (Mesas m : mesasDao.findAll()) {
			if (m.getId().equals(id)) {
				mesasDao.deleteById(id);
				//modeloDao.delete(m);
				bandera = true;
				break;
			}
		}
		return bandera;
	}
	
}
