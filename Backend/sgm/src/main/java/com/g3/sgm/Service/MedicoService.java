package com.g3.sgm.Service;

import com.g3.sgm.Models.Medico;
import com.g3.sgm.Repository.MedicoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Transactional(readOnly=false)
    public Medico save (Medico medico){
        return medicoRepository.save(medico);
    }

    @Transactional(readOnly=false)
    public void delete(String id) {
        medicoRepository.deleteById(id);
    }

    @Transactional(readOnly=true)
    public Medico findById(String id) {
        return medicoRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly=true)
    public List<Medico> findByAll() {
        return (List<Medico>) medicoRepository.findAll();
    }

    @Transactional(readOnly=true)
    public List<Medico> consulta_medico(String idu) {
        return (List<Medico>) medicoRepository.consulta_medico(idu);
    }
    
    @Transactional(readOnly=false)
    public void cambiar_nom_ape (String idm, String nombre, String apellido){
        medicoRepository.cambiar_nom_ape(idm, nombre, apellido);
    }
    
}
