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
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly=false)
    public void delete(String id) {
        userRepository.deleteById(id);
    }

    @Transactional(readOnly=true)
    public User findById(String string) {
        return userRepository.findById(string).orElse(null);
    }

    @Transactional(readOnly=true)
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Transactional(readOnly=true)
    public User login(String usuario, String clave) {
        return userRepository.login(usuario, clave);
    }  

}
