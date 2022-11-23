package com.g3.sgm.Repository;
import com.g3.sgm.Models.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends CrudRepository<User, String>{
    //Autentificacion del usuario
    @Transactional(readOnly=true)
    @Query(value = "select * from user where id_user = :usuario and clave_user= :clave", nativeQuery = true)
    public User login(@Param("usuario") String usuario, @Param("clave") String clave);

}
