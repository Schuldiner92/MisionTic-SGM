package com.sgm.demo.Models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
    @Column(name="fecha")
    private String fecha;
    @Column(name="hora")
    private String hora;
    @Column(name="observacion")
    private String observacion;    
    @ManyToOne
    @JoinColumn(name="dni_medico")
    private Medico medico;
    @ManyToOne
    @JoinColumn(name="dni_paciente")
    private Paciente paciente;

    @Override
    public String toString() {
        return "Citas [id_cita=" + id_cita + ", fecha=" + fecha + ", hora=" + hora + ", observacion=" + observacion
                + ", paciente=" + paciente + ", medico=" + medico + "]";
    }
        
}
