package com.g3.sgm.Service;
import com.g3.sgm.Models.User;
import com.g3.sgm.Repository.UserRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly=false)
    public User save(User Usuario) {
        return userRepository.save(Usuario);
    }

    @Transactional(readOnly=false)
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Transactional(readOnly=true)
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly=true)
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Transactional(readOnly=true)
    public User login(Integer usuario, String clave) {
        return userRepository.login(usuario, clave);
    }  

}
