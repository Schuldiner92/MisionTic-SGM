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
@Table(name="paciente")
public class Paciente implements Serializable {

    @Id
    @NotEmpty(message = "El id no debe ser vacío")    
    @Column(name="id_paciente")
    private String id_paciente;
    @NotEmpty(message = "El nombre no debe ser vacío")
    @Size(min = 5, max = 50,message = "El nombre debe tener entre 5 y 50 caracteres")
    @Column(name="nombre_paciente")
    private String nombre_paciente;
    @NotEmpty(message = "El apellido no debe ser vacío")
    @Size(min = 5, max = 50,message = "El apellido debe tener entre 5 y 50 caracteres")
    @Column(name="apellido_paciente")
    private String apellido_paciente;
    @Size(min = 1, max = 10,message = "Debe tener entre 1 y 10 caracteres")
    @NotEmpty(message = "El campo sexo no debe estar vacío")
    @Column(name="sexo")
    private String sexo;    
    @Column(name="fecha_nacimiento")
    private String fecha_nacimiento;
    @ManyToOne
    @JoinColumn(name="id_user")
    private User user;

    @Override
    public String toString() {
        return "Paciente [dni_paciente=" + id_paciente + ", nombre_paciente=" + nombre_paciente
                + ", apellido_paciente=" + apellido_paciente + ", sexo=" + sexo + ", fecha_nacimiento="
                + fecha_nacimiento + ", usuario=" + user + "]";
    }  
               
}
