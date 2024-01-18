package com.andresyfr.biblioteca.utilidades;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

@Component
public class ManejadorFechas {
	
	public String casteoFecha(LocalDate fecha) {
		return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(fecha);
	}
	
	public LocalDate sumarDiaFecha(int numeroDias) {
		LocalDate fechaActual = LocalDate.now();
		return Stream.iterate(fechaActual.plusDays(1), date -> date.plusDays(1))
				.filter(this::esDiaLaboral)
				.limit(numeroDias)
				.collect(Collectors.toList())
				.get(numeroDias-1);
	}
	
	public String calculoFechaDevol(Usuarios tipoUsuario) {
		LocalDate resultadoFecha = sumarDiaFecha(tipoUsuario.getDias_prestamo());
		return casteoFecha(resultadoFecha);
	}
	
	private boolean esDiaLaboral(LocalDate fecha) {
		return !(fecha.getDayOfWeek()==DayOfWeek.SATURDAY || fecha.getDayOfWeek()==DayOfWeek.SUNDAY); 
	}
}
