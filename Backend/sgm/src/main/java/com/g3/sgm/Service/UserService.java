package com.g3.sgm.Service;
import com.g3.sgm.Models.Userr;
import com.g3.sgm.Repository.UserrRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserrRepository userrRepository;

    @Transactional(readOnly=false)
    public Userr save(Userr user) {
        return userrRepository.save(user);
    }

    @Transactional(readOnly=false)
    public void delete(String id) {
        userrRepository.deleteById(id);
    }

    @Transactional(readOnly=true)
    public Userr findById(String string) {
        return userrRepository.findById(string).orElse(null);
    }

    @Transactional(readOnly=true)
    public List<Userr> findAll() {
        return (List<Userr>) userrRepository.findAll();
    }

    @Transactional(readOnly=true)
    public Userr login(String usuario, String clave) {
        return userrRepository.login(usuario, clave);
    }  

}
