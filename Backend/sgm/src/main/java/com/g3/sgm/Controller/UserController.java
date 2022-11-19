package com.g3.sgm.Controller;
import com.g3.sgm.Models.Administrador;
import com.g3.sgm.Models.User;
import com.g3.sgm.Repository.AdministradorRepository;
import com.g3.sgm.Service.UserService;
import com.g3.sgm.Security.Hash;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private UserService userService;

    @PostMapping(value="/post")
    @ResponseBody
    public ResponseEntity<User> agregar(@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario, @Valid @RequestBody User user){   
        Administrador admon=new Administrador();
        admon=administradorRepository.login(usuario, Hash.sha1(clave));
        if (admon!=null) {
            user.setClave(Hash.sha1(user.getClave()));
            return new ResponseEntity<>(userService.save(user), HttpStatus.OK); 
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); 
        }            
    }

    @DeleteMapping(value="/{id}") 
    public ResponseEntity<User> eliminar(@PathVariable Integer id,@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario){ 
        Administrador admon=new Administrador();
        admon=administradorRepository.login(usuario, Hash.sha1(clave));
       if (admon!=null) {
            User obj = userService.findById(id); 
            if(obj!=null) 
                userService.delete(id);
            else 
                return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR); 
            return new ResponseEntity<>(obj, HttpStatus.OK); 
      
       } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
       }      
    }

    @PutMapping(value="/put") 
    @ResponseBody
    public ResponseEntity<User> editar(@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario,@Valid @RequestBody User user){ 
        Administrador admon=new Administrador();
        admon=administradorRepository.login(usuario, Hash.sha1(clave));
        if (admon!=null) {
            user.setClave(Hash.sha1(user.getClave()));
            User obj = userService.findById(user.getId_user()); 
            if(obj!=null) { 
                obj.setEmail(user.getEmail());
                obj.setClave(user.getClave());
                userService.save(user); 
            } 
            else 
                return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR); 
            return new ResponseEntity<>(obj, HttpStatus.OK); 
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }        
    }

    @GetMapping("/list") 
    @ResponseBody
    public ResponseEntity<List<User>> consultarTodo(@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario){
        Administrador admon=new Administrador();
        admon=administradorRepository.login(usuario, Hash.sha1(clave));
        if (admon!=null) {
            return new ResponseEntity<>(userService.findAll(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }            
    }

    @GetMapping("/list/{id}") 
    @ResponseBody
    public ResponseEntity<User> consultaPorId(@PathVariable Integer id,@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario){ 
        Administrador admon=new Administrador();
        admon=administradorRepository.login(usuario, Hash.sha1(clave));
        if (admon!=null) {
            return new ResponseEntity<>(userService.findById(id),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }   
    }

    @GetMapping("/login")
    @ResponseBody
    public User ingresar(@RequestParam ("usuario") Integer usuario,@RequestParam ("clave") String clave) {
        clave=Hash.sha1(clave);
        return userService.login(usuario, clave); 
    }
    
}
