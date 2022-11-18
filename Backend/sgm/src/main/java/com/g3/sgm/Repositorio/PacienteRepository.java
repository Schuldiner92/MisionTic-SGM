package com.g3.sgm.Repositorio;

import com.g3.sgm.Models.Paciente;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PacienteRepository extends CrudRepository<Paciente, String> {

    // Autentificacion del medico
    @Query(value = "select * from paciente where id_paciente = : usuario and clave_paciente= : clave", nativeQuery = true)
    public Paciente login(@Param("usuario") String usuario, @Param("clave") String clave);

}