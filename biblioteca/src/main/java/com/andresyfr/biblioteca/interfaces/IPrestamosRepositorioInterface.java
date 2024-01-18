package com.andresyfr.biblioteca.interfaces;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.andresyfr.biblioteca.modelos.PrestamosModelo;

@Repository
public interface IPrestamosRepositorioInterface extends CrudRepository<PrestamosModelo, Long>{
	List<PrestamosModelo> findByIdentificacionUsuario(String identificacionUsuario);
}
