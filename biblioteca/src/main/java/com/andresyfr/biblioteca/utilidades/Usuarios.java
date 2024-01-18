package com.andresyfr.biblioteca.utilidades;

import java.util.Iterator;

import lombok.Getter;

@Getter
public enum Usuarios {
	USUARIO_AFILIADO(1,10), USUARIO_EMPLEADO(2,8), USUARIO_INVITADO(3,7), USUARIO_DIFERENTE(4,0);
	
	private final int tipo_usuario;
	private final int dias_prestamo;
	
	Usuarios(int tipo_usuario, int dias_prestamo) {
		this.tipo_usuario = tipo_usuario;
		this.dias_prestamo = dias_prestamo;
	}
	
	public static Usuarios convertirHaciaEnum(int tipoUsuario) {
		Usuarios[] valores = Usuarios.values();
		for (Usuarios usuarios : valores) {
			if(usuarios.tipo_usuario == tipoUsuario)
				return usuarios;
		}
		return USUARIO_DIFERENTE;
		//throw new IllegalArgumentException("Tipo de usuario no válido para los usuarios registrados: [tipoUsuario: "+tipoUsuario+" ]");
	}
}
