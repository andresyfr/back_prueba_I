package com.andresyfr.biblioteca.servicios;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andresyfr.biblioteca.interfaces.IPrestamosRepositorioInterface;
import com.andresyfr.biblioteca.modelos.PrestamosModelo;
import com.andresyfr.biblioteca.utilidades.ManejadorFechas;
import com.andresyfr.biblioteca.utilidades.Mensaje;
import com.andresyfr.biblioteca.utilidades.Usuarios;

@Service
public class PrestamosServicio implements IPrestamosServicioInterface{

	@Autowired
	private IPrestamosRepositorioInterface iPrestamosInterface;
	
	@Autowired
	private ManejadorFechas manejadorFechas;
	
	@Autowired
	private Mensaje mensaje;
	
	@Override
	public Map<String, Object> agregarPrestamo(PrestamosModelo prestamo) {
		String identUsuario = prestamo.getIdentificacionUsuario();
		Usuarios tipoUsuario= Usuarios.convertirHaciaEnum(prestamo.getTipoUsuario());
		Map<String, Object> respuesta=new LinkedHashMap<>();
		if(tipoUsuario.ordinal() >= Usuarios.USUARIO_DIFERENTE.ordinal()) {
			return mensaje.handleUsuarioDesconocido(respuesta);
		}
		if(tipoUsuario == Usuarios.USUARIO_INVITADO && tienLibroPrestado(prestamo)) {
			return mensaje.handleUsuarioCnLibroPrestado(identUsuario, respuesta);
		}
		String fechaDevolucion = manejadorFechas.calculoFechaDevol(tipoUsuario);
		prestamo.setFechaMaximaDevolucion(fechaDevolucion);
		PrestamosModelo prestamosModelo = iPrestamosInterface.save(prestamo);
		respuesta.put(String.valueOf(prestamosModelo.getId()), fechaDevolucion);
		return respuesta;
	}

	private boolean tienLibroPrestado(PrestamosModelo prestamosModelo) {
		List<PrestamosModelo> libro = iPrestamosInterface.findByIdentificacionUsuario(prestamosModelo.getIdentificacionUsuario());
		return (libro.size()==0)?false:true;
	}
	
	@Override
	public Optional<PrestamosModelo> retornarPrestamoPrId(PrestamosModelo prestamo) {
		return iPrestamosInterface.findById(prestamo.getId());
	}

	@Override
	public Map<String, Object> retornarPrestamos(){
		Map<String, Object> respuesta = new LinkedHashMap<>();
		Iterable<PrestamosModelo> repo = iPrestamosInterface.findAll();
		List<PrestamosModelo> listaPrestamos=new ArrayList<>();
		repo.forEach(listaPrestamos::add);
		respuesta.put(String.valueOf(listaPrestamos.size()), listaPrestamos);
		return respuesta;
	}
	
	/**
	 * Metodo que se realiza si se llegase a requerir en la prueba de angular :/
	 */
	@Override
	public Map<String, Object> actualizarPrestamo(PrestamosModelo prestamo) {
		// TODO Auto-generated method stub
		Map<String, Object> respuesMap = new LinkedHashMap<>();
		try {
			iPrestamosInterface.save(prestamo);
			respuesMap = mensaje.handleActualizadoExitoso(String.valueOf(prestamo.getIdentificacionUsuario()), respuesMap);
		}catch (Exception e) {
			// TODO: handle exception
			respuesMap = mensaje.handleActualizadoError(String.valueOf(prestamo.getIdentificacionUsuario()), respuesMap);
		}
		return respuesMap;
	}

	/**
	 * Metodo que se realiza si se llegase a requerir en la prueba de angular :/
	 */
	@Override
	public Map<String, Object> eliminarPrestamo(Long idPrestamo) {
		// TODO Auto-generated method stub
		Map<String, Object> respuesMap = new LinkedHashMap<>();
		try {
			iPrestamosInterface.deleteById(idPrestamo);
			respuesMap = mensaje.handleBorradoExitoso(String.valueOf(idPrestamo), respuesMap);
		}catch (Exception e) {
			// TODO: handle exception
			respuesMap = mensaje.handleBorradoError(String.valueOf(idPrestamo), respuesMap);
		}
		return respuesMap;
	}

}
