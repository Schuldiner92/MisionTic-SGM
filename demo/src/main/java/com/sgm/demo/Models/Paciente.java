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
@Table(name="paciente")
public class Paciente implements Serializable {

    @Id
    @Column(name="ni_paciente")
    private String dni_paciente;
    @Column(name="nombre_paciente")
    private String nombre_paciente;
    @Column(name="apellido_paciente")
    private String apellido_paciente;
    @Column(name="sexo")
    private String sexo;
    @Column(name="fecha_nacimiento")
    private String fecha_nacimiento;
    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;
    
    @Override
    public String toString() {
        return "Paciente [dni_paciente=" + dni_paciente + ", nombre_paciente=" + nombre_paciente
                + ", apellido_paciente=" + apellido_paciente + ", sexo=" + sexo + ", fecha_nacimiento="
                + fecha_nacimiento + "]";
    }
    
}
