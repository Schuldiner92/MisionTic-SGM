package com.g3.sgm.Repository;
import com.g3.sgm.Models.Medico;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MedicoRepository extends CrudRepository<Medico, String>{
    //Seleccionar medico de un user en particular
    @Transactional(readOnly=true)
    @Query(value="SELECT * FROM medico WHERE id_userr= :idu", nativeQuery=true)
    public List<Medico> consulta_medico(@Param("idu") String idu); 
    //Modificar nombre y apellidos de un medico
    @Transactional(readOnly=false)   
    @Modifying
    @Query(value="UPDATE medico SET nombre_medico= :nombre, apellido_medico= :apellido WHERE id_medico like :idm", nativeQuery=true)
    public void cambiar_nom_ape(@Param("idm") String idm,@Param("nombre") String nombre, @Param("apellido") String apellido);   

}
