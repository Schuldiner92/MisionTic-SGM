package com.g3.sgm.Service;

import com.g3.sgm.Models.Paciente;
import com.g3.sgm.Repository.PacienteRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional;

@Service
public class PacienteService {
    
    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional(readOnly=false)
    public Paciente save (Paciente paciente){
        return pacienteRepository.save(paciente);
    }

    @Transactional(readOnly=false)
    public void delete(String id) {
        pacienteRepository.deleteById(id);
    }

    @Transactional(readOnly=true)
    public Paciente findById(String id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly=true)
    public List<Paciente> findByAll() {
        return (List<Paciente>) pacienteRepository.findAll();
    }

    @Transactional(readOnly=true)
    public List<Paciente> consulta_paciente(String idu) {
        return (List<Paciente>) pacienteRepository.consulta_paciente(idu);
    }
    
    @Transactional(readOnly=false)
    public void cambiar_nom_ape (String idp, String nombre, String apellido){
        pacienteRepository.cambiar_nom_ape(idp, nombre, apellido);
    }
    
}
