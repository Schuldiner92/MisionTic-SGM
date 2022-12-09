package com.g3.sgm.Models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="cita")
public class Cita implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_cita")    
    private int id_cita;      
    @Column(name="fecha_hora")
    private Date fecha_hora;      
    @Column(name="observacion")
    private String observacion;    
    @Column(name="estado")
    private String estado;
    @ManyToOne
    @JoinColumn(name="id_medico")
    private Medico medico;
    @ManyToOne
    @JoinColumn(name="id_paciente")
    private Paciente paciente;

    @Override
    public String toString() {
        return "Citas [id_cita=" + id_cita + ", fecha_hora=" + fecha_hora  + ", observacion=" + observacion
                + ", paciente=" + paciente + ", medico=" + medico + "]";
    }
        
}
