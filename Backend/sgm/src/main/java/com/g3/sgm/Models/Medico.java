package com.g3.sgm.Models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="medico")
public class Medico implements Serializable {
    
    @Id
    @NotEmpty(message = "El id no debe ser vacío")
    @Size(min = 5, max = 30,message = "El id debe tener entre 5 y 30 caracteres")
    @Column(name="id_medico")
    private String id_medico;
    @NotEmpty(message = "El nombre no debe ser vacío")
    @Size(min = 5, max = 50,message = "El nombre debe tener entre 5 y 50 caracteres")
    @Column(name="nombre_medico")
    private String nombre_medico;
    @NotEmpty(message = "El apellido no debe ser vacío")
    @Size(min = 5, max = 50,message = "El apellido debe tener entre 5 y 50 caracteres")
    @Column(name="apellido_medico")
    private String apellido_medico;
    @NotEmpty(message = "El campo especialidad no debe estar vacío")
    @Column(name="especialidad")
    private String especialidad;
    @ManyToOne
    @JoinColumn(name="id_user")
    private User user;

    @Override
    public String toString() {
        return "Medico [dni_medico=" + id_medico + ", nombre_medico=" + nombre_medico + ", apellido_medico="
                + apellido_medico + ", especialidad=" + especialidad + ", usuario=" + user + "]";
    }   
       
}
