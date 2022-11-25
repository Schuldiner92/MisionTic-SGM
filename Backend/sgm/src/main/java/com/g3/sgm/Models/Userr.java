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
@Table(name="userr")
public class Userr implements Serializable{
    
    @Id    
    @Column(name="id_userr")
    private String id_userr;    
    @Column(name="email")
    private String email;
    @Column(name="clave_userr")
    private String clave_userr;
    @Column(name="rol")
    private String rol;

    @Override
    public String toString() {
        return "Usuario [id_usuario=" + id_userr + ", email=" + email + ", clave_userr=" + clave_userr + ", rol=" + rol + "]";
    }
    
}
