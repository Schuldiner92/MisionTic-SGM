package com.g3.sgm.Repository;
import com.g3.sgm.Models.Paciente;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PacienteRepository extends CrudRepository<Paciente, String> {
    //Seleccionar paciente de un user en particular
    @Transactional(readOnly=true)
    @Query(value="SELECT * FROM paciente WHERE id_user= :idu", nativeQuery=true)
    public List<Paciente> consulta_paciente(@Param("idu") String idu); 
    //Modificar nombre y apellidos de un paciente
    @Transactional(readOnly=false)   
    @Modifying
    @Query(value="UPDATE paciente SET nombre_paciente= :nombre, apellido_paciente= :apellido WHERE id_paciente like :idp", nativeQuery=true)
    public void cambiar_nom_ape(@Param("idp") String idp,@Param("nombre") String nombre, @Param("apellido") String apellido); 
        
}