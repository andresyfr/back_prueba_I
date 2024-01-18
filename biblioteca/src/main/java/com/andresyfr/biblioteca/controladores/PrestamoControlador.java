package com.andresyfr.biblioteca.controladores;


import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andresyfr.biblioteca.interfaces.IPrestamosRepositorioInterface;
import com.andresyfr.biblioteca.modelos.PrestamosModelo;
import com.andresyfr.biblioteca.servicios.IPrestamosServicioInterface;
import com.andresyfr.biblioteca.utilidades.Mensaje;

@RestController
@RequestMapping("prestamo")
//@CrossOrigin(origins = "http://localhost")
public class PrestamoControlador {
	
	@Autowired
	private IPrestamosServicioInterface prestamosInterface;
	
	@Autowired 
	private Mensaje mensaje;
	
	@PostMapping()
	public Map<String, ?> agregarPrestamo(@RequestBody PrestamosModelo prestamo, HttpServletResponse response){
		// TODO Auto-generated method stub
		Map<String, Object> respuestaPrestamo = prestamosInterface.agregarPrestamo(prestamo);
		String idPrestamo = String.valueOf(respuestaPrestamo.keySet().toArray()[0]);
		String fechaDecolucion = String.valueOf(respuestaPrestamo.get(idPrestamo));
		if(respuestaPrestamo.containsKey(mensaje.getMensaje())) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return respuestaPrestamo;
		}
		respuestaPrestamo.put("id", Integer.valueOf(idPrestamo));
		respuestaPrestamo.put("fechaMaximaDevolucion", fechaDecolucion);
		respuestaPrestamo.remove(idPrestamo);
		response.setStatus(HttpServletResponse.SC_OK);
		return respuestaPrestamo;
	}

	@GetMapping(path = "/{id-prestamo}")
	public Optional<PrestamosModelo> retornarPrestamoPrId(@PathVariable("id-prestamo") Long id){
		PrestamosModelo prestamo = new PrestamosModelo(id);
		return prestamosInterface.retornarPrestamoPrId(prestamo);
	}
}

