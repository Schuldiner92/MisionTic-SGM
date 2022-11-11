package com.sgm.demo.Models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="medico")
public class Medico implements Serializable {
    
    @Id
    @Column(name="dni_medico")
    private String dni_medico;
    @Column(name="nombre_medico")
    private String nombre_medico;
    @Column(name="apellido_medico")
    private String apellido_medico;
    @Column(name="especialidad")
    private String especialidad;
    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;
    
    @Override
    public String toString() {
        return "Medico [dni_medico=" + dni_medico + ", nombre_medico=" + nombre_medico + ", apellido_medico="
                + apellido_medico + ", especialidad=" + especialidad + ", usuario=" + usuario + "]";
    }
    
}
