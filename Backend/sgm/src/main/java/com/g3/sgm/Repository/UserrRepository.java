package com.g3.sgm.Repository;
import com.g3.sgm.Models.Userr;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserrRepository extends CrudRepository<Userr, String>{
    //Autentificacion del usuario
    @Transactional(readOnly=true)
    @Query(value = "select * from userr where id_userr = :usuario and clave_userr= :clave", nativeQuery = true)
    public Userr login(@Param("usuario") String usuario, @Param("clave") String clave);

}
