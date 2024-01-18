package com.andresyfr.biblioteca.servicios;

import java.util.Map;
import java.util.Optional;

import com.andresyfr.biblioteca.modelos.PrestamosModelo;

public interface IPrestamosServicioInterface {
	Map<String, Object> agregarPrestamo(PrestamosModelo prestamo);
	Optional<PrestamosModelo> retornarPrestamoPrId(PrestamosModelo prestamo);
	Map<String, Object> retornarPrestamos();
	Map<String, Object> actualizarPrestamo(PrestamosModelo prestamo);
	Map<String, Object> eliminarPrestamo(Long idPrestamo);
}
