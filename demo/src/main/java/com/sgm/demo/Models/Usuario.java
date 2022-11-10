package com.sgm.demo.Models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="usuario")
public class Usuario implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private int id_usuario;
    @Column(name="email")
    private String email;
    @Column(name="clave")
    private String clave;
    @Column(name="rol")
    private String rol;

    @Override
    public String toString() {
        return "Usuario [id_usuario=" + id_usuario + ", email=" + email + ", clave=" + clave + ", rol=" + rol + "]";
    }
    
}
