package com.andresyfr.biblioteca.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.UniqueElements;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table
@AllArgsConstructor
@ToString
public class PrestamosModelo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;
	@Size(max = 10)
	private String isbn;
	@Size(max = 10)
	private String identificacionUsuario;
	@Column(length = 1)
	private int tipoUsuario;
	private String fechaMaximaDevolucion;	
	public PrestamosModelo(){
	}
	public PrestamosModelo(@UniqueElements Long id) {
		super();
		this.id = id;
	}
}
