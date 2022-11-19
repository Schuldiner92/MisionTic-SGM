package com.g3.sgm.Models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="user")
public class User implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_user")
    private int id_user;
    @NotEmpty(message = "El email no debe ser vacío")
    @Size(min = 5, max = 50,message = "El email debe tener entre 5 y 50 caracteres")
    @Column(name="email")
    private String email;
    @NotEmpty(message = "La Clave no debe ser vacía")
    @Size(min = 5, max = 30,message = "la clave debe tener entre 5 y 30 caracteres")
    @Column(name="clave")
    private String clave;
    @NotEmpty(message = "Se debe elegir un rol de usuario")
    @Column(name="rol")
    private String rol;

    @Override
    public String toString() {
        return "Usuario [id_usuario=" + id_user + ", email=" + email + ", clave=" + clave + ", rol=" + rol + "]";
    }
    
}
