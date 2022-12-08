package com.g3.sgm.Service;
import com.g3.sgm.Models.Cita;
import com.g3.sgm.Repository.CitaRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional;

@Service
public class CitaService {
    
    @Autowired
    private CitaRepository citaRepository;

    @Transactional(readOnly=false)
    public Cita save (Cita cita){
        return citaRepository.save(cita);
    }

    @Transactional(readOnly=false)
    public void delete(Integer id) {
        citaRepository.deleteById(id);
    }

    @Transactional(readOnly=true)
    public Cita findById(Integer id) {
        return citaRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly=true)
    public List<Cita> findByAll() {
        return (List<Cita>) citaRepository.findAll();
    }

    @Transactional(readOnly=true)
    public List<Cita> consulta_cita_paciente(String idp) {
        return (List<Cita>) citaRepository.consulta_cita_paciente(idp);
    }

    @Transactional(readOnly=true)
    public List<Cita> consulta_cita_medico(String idm) {
        return (List<Cita>) citaRepository.consulta_cita_medico(idm);
    }

    @Transactional(readOnly=false)
    public void agregar_observacion (String idc, String observacion){
        citaRepository.agregar_observacion(idc, observacion);
    }

    @Transactional(readOnly=false)
    public void terminar_cita(String idc, String estado){
        citaRepository.terminar_cita(idc, estado);
    }

    @Transactional(readOnly=false)
    public void cancelar_cita(String idc, String estado){
        citaRepository.cancelar_cita(idc, estado);
    }   

}