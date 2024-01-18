package com.andresyfr.biblioteca.utilidades;

import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Component
public class Mensaje {
	private String mensaje;
	private Object contenido;
	
	public Mensaje() {
		super();
		this.mensaje = "mensaje";
	}	
	
	public Map<String, Object> handleUsuarioDesconocido(Map<String, Object> respuesta){
		respuesta.put(mensaje, MensajesPrDefecto.M_USUARIO_DESCONOCIDO.toString());
		return respuesta;
	}

	public Map<String, Object> handleUsuarioCnLibroPrestado(String identUsuario, Map<String, Object> respuesta){
		respuesta.put(mensaje, MensajesPrDefecto.M_USUARIO_LIBRO_PRESTADO.toString().replace("{{identUsuario}}",identUsuario) );
		return respuesta;
	}
	
	public Map<String, Object> handleBorradoExitoso(String idPrestamo, Map<String, Object> respuesta){
		respuesta.put(mensaje, MensajesPrDefecto.M_BORRADO_EXITOSO.toString().replace("{{idPrestamo}}",idPrestamo) );
		return respuesta;
	}
	
	public Map<String, Object> handleBorradoError(String idPrestamo, Map<String, Object> respuesta){
		respuesta.put(mensaje, MensajesPrDefecto.M_BORRADO_ERROR.toString().replace("{{idPrestamo}}",idPrestamo) );
		return respuesta;
	}
	
	public Map<String, Object> handleActualizadoError(String identUsuario, Map<String, Object> respuesta){
		respuesta.put(mensaje, MensajesPrDefecto.M_ACTUALIZADO_ERROR.toString().replace("{{identUsuario}}",identUsuario) );
		return respuesta;
	}
	
	public Map<String, Object> handleActualizadoExitoso(String identUsuario, Map<String, Object> respuesta){
		respuesta.put(mensaje, MensajesPrDefecto.M_ACTUALIZADO_EXITOSO.toString().replace("{{identUsuario}}",identUsuario) );
		return respuesta;
	}
}
