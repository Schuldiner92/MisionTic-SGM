package com.g3.sgm.Controller;
import com.g3.sgm.Models.Administrador;
import com.g3.sgm.Models.Userr;
import com.g3.sgm.Models.Paciente;
import com.g3.sgm.Repository.AdministradorRepository;
import com.g3.sgm.Repository.UserrRepository;
import com.g3.sgm.Service.PacienteService;
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
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private UserrRepository userrRepository;

    @Autowired
    private PacienteService pacienteService;

    //Consumos de Recursos Administrador
    @PostMapping(value="/post")
    @ResponseBody
    public ResponseEntity<Paciente> agregar(@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario, @Valid @RequestBody Paciente paciente){   
        Administrador admon=new Administrador();
        admon=administradorRepository.login(usuario, Hash.sha1(clave));
        if (admon!=null) {
            return new ResponseEntity<>(pacienteService.save(paciente), HttpStatus.OK); 
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); 
        }            
    }

    @DeleteMapping(value="/{id}") 
    public ResponseEntity<Paciente> eliminar(@PathVariable String id,@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario){ 
        Administrador admon=new Administrador();
        admon=administradorRepository.login(usuario, Hash.sha1(clave));
       if (admon!=null) {
            Paciente obj = pacienteService.findById(id); 
            if(obj!=null) 
                pacienteService.delete(id);
            else 
                return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR); 
            return new ResponseEntity<>(obj, HttpStatus.OK);       
       } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
       }           
    }

    @PutMapping(value="/put") 
    @ResponseBody
    public ResponseEntity<Paciente> editar(@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario,@Valid @RequestBody Paciente paciente){ 
        Administrador admon=new Administrador();
        admon=administradorRepository.login(usuario, Hash.sha1(clave));
        if (admon!=null) {
            Paciente obj = pacienteService.findById(paciente.getId_paciente()); 
            if(obj!=null) { 
                obj.setNombre_paciente(paciente.getNombre_paciente());
                obj.setApellido_paciente(paciente.getApellido_paciente());
                obj.setSexo(paciente.getSexo());
                obj.setFecha_nacimiento(paciente.getFecha_nacimiento());
                pacienteService.save(paciente); 
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
    public ResponseEntity<List<Paciente>> consultarTodo(@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario){
        Administrador admon=new Administrador();
        admon=administradorRepository.login(usuario, Hash.sha1(clave));
        if (admon!=null) {
            return new ResponseEntity<>(pacienteService.findByAll(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }            
    }

    @GetMapping("/list/{id}") 
    @ResponseBody
    public ResponseEntity<Paciente> consultaPorId(@PathVariable String id,@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario){ 
        Administrador admon=new Administrador();
        admon=administradorRepository.login(usuario, Hash.sha1(clave));
        if (admon!=null) {
            return new ResponseEntity<>(pacienteService.findById(id),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }   
    }

    //Consumo de Recursos User
    @PutMapping(value="/cambiar_nom_ape") 
    public void cambiar_nom_ape(@RequestParam("idp") String idp,@RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido, @RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario){ 
        Userr userr1=new Userr();
        userr1=userrRepository.login(usuario, Hash.sha1(clave));
        if (userr1!=null) {
           pacienteService.cambiar_nom_ape(idp, nombre, apellido);
        }          
    }

    @GetMapping("/consulta_paciente")
    @ResponseBody
    public ResponseEntity<List<Paciente>> consulta_paciente(@RequestParam ("idu") String idu,@RequestHeader ("usuario") String usuario,@RequestHeader ("clave") String clave) { 
        Userr userr=new Userr();
        userr=userrRepository.login(usuario, Hash.sha1(clave));
        if (userr!=null) {
            return new ResponseEntity<>(pacienteService.consulta_paciente(idu),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }        
    }

}