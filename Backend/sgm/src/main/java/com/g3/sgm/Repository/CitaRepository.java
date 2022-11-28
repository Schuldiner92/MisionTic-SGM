package com.g3.sgm.Repository;
import com.g3.sgm.Models.Cita;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CitaRepository extends CrudRepository<Cita, Integer>{
    //Seleccionar cita de un paciente en particular
    @Transactional(readOnly=true)
    @Query(value="SELECT * FROM cita WHERE id_paciente= :idp", nativeQuery=true)
    public List<Cita> consulta_cita_paciente(@Param("idp") String idp); 
    //Seleccionar cita de un paciente en particular
    @Transactional(readOnly=true)
    @Query(value="SELECT * FROM cita WHERE id_medico= :idm", nativeQuery=true)
    public List<Cita> consulta_cita_medico(@Param("idm") String idm); 
    //Crear cita (po un paciente)    
    //Agregar datos al campo observacion (por un médico)
    @Transactional(readOnly=false)   
    @Modifying
    @Query(value="UPDATE cita SET observacion= :observacion WHERE id_medico like :idm", nativeQuery=true)
    public void agregar_observacion(@Param("idm") String idm,@Param("observacion") String observacion);
    //Cambiar estado de la cita a T(terminado)  (por un médico)
    @Transactional(readOnly=false)   
    @Modifying
    @Query(value="UPDATE cita SET estado= :estado WHERE id_medico like :idm", nativeQuery=true)
    public void terminar_cita(@Param("idm") String idm,@Param("estado") String estado);
    //Cambiar estado de la cita a C(Cancelado) (por un pciente)
    @Transactional(readOnly=false)   
    @Modifying
    @Query(value="UPDATE cita SET estado= :estado WHERE id_paciente like :idp", nativeQuery=true)
    public void cancelar_cita(@Param("idp") String idp,@Param("estado") String estado);


    
    
}
