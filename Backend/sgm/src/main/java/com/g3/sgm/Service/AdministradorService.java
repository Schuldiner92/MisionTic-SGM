package com.g3.sgm.Service;
import com.g3.sgm.Models.Administrador;
import com.g3.sgm.Repository.AdministradorRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdministradorService {
    
    @Autowired
    private AdministradorRepository administradorRepository;

    @Transactional(readOnly=false)
    public Administrador save(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    @Transactional(readOnly=false)
    public void delete(String id) {
        administradorRepository.deleteById(id);
    }

    @Transactional(readOnly=true)
    public Administrador findById(String id) {
        return administradorRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly=true)
    public List<Administrador> findAll() {
        return (List<Administrador>) administradorRepository.findAll();
    }

    @Transactional(readOnly=true)
    public Administrador login(String usuario, String clave) {
        return administradorRepository.login(usuario, clave);
    }

}