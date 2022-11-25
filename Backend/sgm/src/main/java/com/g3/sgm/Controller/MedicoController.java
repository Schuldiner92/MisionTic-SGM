package com.g3.sgm.Controller;
import com.g3.sgm.Models.Administrador;
import com.g3.sgm.Models.Userr;
import com.g3.sgm.Models.Medico;
import com.g3.sgm.Repository.AdministradorRepository;
import com.g3.sgm.Repository.UserrRepository;
import com.g3.sgm.Service.MedicoService;
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
@RequestMapping("/medico")
public class MedicoController {
    
    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private UserrRepository userRepository;

    @Autowired
    private MedicoService medicoService;

    //Consumos de Recursos Administrador
    @PostMapping(value="/post")
    @ResponseBody
    public ResponseEntity<Medico> agregar(@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario, @Valid @RequestBody Medico medico){   
        Administrador admon=new Administrador();
        admon=administradorRepository.login(usuario, Hash.sha1(clave));
        if (admon!=null) {
            return new ResponseEntity<>(medicoService.save(medico), HttpStatus.OK); 
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); 
        }            
    }

    @DeleteMapping(value="/{id}") 
    public ResponseEntity<Medico> eliminar(@PathVariable String id,@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario){ 
        Administrador admon=new Administrador();
        admon=administradorRepository.login(usuario, Hash.sha1(clave));
       if (admon!=null) {
            Medico obj = medicoService.findById(id); 
            if(obj!=null) 
                medicoService.delete(id);
            else 
                return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR); 
            return new ResponseEntity<>(obj, HttpStatus.OK);       
       } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
       }           
    }

    @PutMapping(value="/put") 
    @ResponseBody
    public ResponseEntity<Medico> editar(@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario,@Valid @RequestBody Medico medico){ 
        Administrador admon=new Administrador();
        admon=administradorRepository.login(usuario, Hash.sha1(clave));
        if (admon!=null) {
            Medico obj = medicoService.findById(medico.getId_medico()); 
            if(obj!=null) { 
                obj.setNombre_medico(medico.getNombre_medico());
                obj.setApellido_medico(medico.getApellido_medico());
                obj.setEspecialidad(medico.getEspecialidad());                
                medicoService.save(medico); 
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
    public ResponseEntity<List<Medico>> consultarTodo(@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario){
        Administrador admon=new Administrador();
        admon=administradorRepository.login(usuario, Hash.sha1(clave));
        if (admon!=null) {
            return new ResponseEntity<>(medicoService.findByAll(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }            
    }

    @GetMapping("/list/{id}") 
    @ResponseBody
    public ResponseEntity<Medico> consultaPorId(@PathVariable String id,@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario){ 
        Administrador admon=new Administrador();
        admon=administradorRepository.login(usuario, Hash.sha1(clave));
        if (admon!=null) {
            return new ResponseEntity<>(medicoService.findById(id),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }   
    }

    //Consumo de Recursos User
    @PutMapping(value="/cambiar_nom_ape") 
    public void cambiar_nom_ape(@RequestParam("idm") String idm,@RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido, @RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario){ 
        Userr user1=new Userr();
        user1=userRepository.login(usuario, Hash.sha1(clave));
        if (user1!=null) {
           medicoService.cambiar_nom_ape(idm, nombre, apellido);
        }          
    }

    @GetMapping("/consulta_medico")
    @ResponseBody
    public ResponseEntity<List<Medico>> consulta_medico(@RequestParam ("idu") String idu,@RequestHeader ("usuario") String usuario,@RequestHeader ("clave") String clave) { 
        Userr user=new Userr();
        user=userRepository.login(usuario, Hash.sha1(clave));
        if (user!=null) {
            return new ResponseEntity<>(medicoService.consulta_medico(idu),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }        
    }

}
