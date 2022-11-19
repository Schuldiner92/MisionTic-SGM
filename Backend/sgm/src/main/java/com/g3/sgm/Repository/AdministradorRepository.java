package com.g3.sgm.Repository;
import com.g3.sgm.Models.Administrador;
import com.g3.sgm.Models.User;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AdministradorRepository extends CrudRepository<Administrador,String> {
    //Autentiiicación 
    @Transactional(readOnly=true)
    @Query(value="SELECT * FROM administrador WHERE id_administrador= :usuario AND clave_administrador= :clave", nativeQuery=true)
    public Administrador login(@Param("usuario") String usuario, @Param("clave") String clave);
    public Administrador login(@Valid User usuario, String sha1); 
}