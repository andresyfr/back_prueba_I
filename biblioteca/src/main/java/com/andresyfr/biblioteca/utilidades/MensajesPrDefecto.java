package com.andresyfr.biblioteca.utilidades;

public interface MensajesPrDefecto {
	public static final String M_USUARIO_DESCONOCIDO = "Tipo de usuario no permitido en la biblioteca";
	public static final String M_USUARIO_LIBRO_PRESTADO = "El usuario con identificación {{identUsuario}} ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo";
	public static final String M_BORRADO_EXITOSO = "El prestamo con id {{idPrestamo}} a sido borrado";
	public static final String M_BORRADO_ERROR = "El prestamo con id {{idPrestamo}} tuvo un error al intentar borrar";
	public static final String M_ACTUALIZADO_EXITOSO = "El prestamo del usuario {{identUsuario}} a sido borrado";
	public static final String M_ACTUALIZADO_ERROR = "El prestamo del usuario {{identUsuario}} tuvo un error al intentar borrar";
}
