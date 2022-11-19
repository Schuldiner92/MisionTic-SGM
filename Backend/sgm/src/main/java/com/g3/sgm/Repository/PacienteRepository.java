package com.g3.sgm.Repository;
import com.g3.sgm.Models.Paciente;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PacienteRepository extends CrudRepository<Paciente, String> {

    
}