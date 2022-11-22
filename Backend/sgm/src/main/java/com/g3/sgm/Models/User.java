package com.g3.sgm.Models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="user")
public class User implements Serializable{
    
    @Id    
    @Column(name="id_user")
    private String id_user;    
    @Column(name="email")
    private String email;
    @Column(name="clave_user")
    private String clave_user;
    @Column(name="rol")
    private String rol;

    @Override
    public String toString() {
        return "Usuario [id_usuario=" + id_user + ", email=" + email + ", clave_user=" + clave_user + ", rol=" + rol + "]";
    }
    
}
