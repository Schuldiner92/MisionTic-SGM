package com.g3.sgm.Repository;
import com.g3.sgm.Models.Administrador;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AdministradorRepository extends CrudRepository<Administrador,String> {
    //Autentificacion
    @Transactional(readOnly=true)
    @Query(value="SELECT * FROM administrador WHERE id_administrador= :usuario AND clave_administrador= :clave", nativeQuery=true)
    public Administrador login(@Param("usuario") String usuario, @Param("clave") String clave); 
    
}