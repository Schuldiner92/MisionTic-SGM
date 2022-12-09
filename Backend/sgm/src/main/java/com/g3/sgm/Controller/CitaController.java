package com.g3.sgm.Controller;

import com.g3.sgm.Models.Administrador;
import com.g3.sgm.Models.Userr; 
import com.g3.sgm.Models.Cita;
import com.g3.sgm.Repository.AdministradorRepository;
import com.g3.sgm.Repository.UserrRepository;
import com.g3.sgm.Service.CitaService;
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
@RequestMapping("/cita")
public class CitaController {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private UserrRepository userrRepository;

    @Autowired
    private CitaService citaService;
    
    //Consumos de Recursos Administrador
    @PostMapping(value="/post")
    @ResponseBody
    public ResponseEntity<Cita> agregar(@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario, @Valid @RequestBody Cita cita){   
        Administrador admon=new Administrador();
        admon=administradorRepository.login(usuario, Hash.sha1(clave));
        if (admon!=null) {
            return new ResponseEntity<>(citaService.save(cita), HttpStatus.OK); 
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); 
        }            
    }

    @DeleteMapping(value="/{id}") 
    public ResponseEntity<Cita> eliminar(@PathVariable Integer id,@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario){ 
        Administrador admon=new Administrador();
        admon=administradorRepository.login(usuario, Hash.sha1(clave));
       if (admon!=null) {
            Cita obj = citaService.findById(id); 
            if(obj!=null) 
                citaService.delete(id);
            else 
                return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR); 
            return new ResponseEntity<>(obj, HttpStatus.OK);       
       } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
       }           
    }

    @PutMapping(value="/put") 
    @ResponseBody
    public ResponseEntity<Cita> editar(@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario,@Valid @RequestBody Cita cita){ 
        Administrador admon=new Administrador();
        admon=administradorRepository.login(usuario, Hash.sha1(clave));
        if (admon!=null) {
            Cita obj = citaService.findById(cita.getId_cita()); 
            if(obj!=null) { 
                obj.setFecha_hora(cita.getFecha_hora());
                obj.setObservacion(cita.getObservacion());
                obj.setEstado(cita.getEstado());                
                citaService.save(cita); 
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
    public ResponseEntity<List<Cita>> consultarTodo(@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario){
        Administrador admon=new Administrador();
        admon=administradorRepository.login(usuario, Hash.sha1(clave));
        if (admon!=null) {
            return new ResponseEntity<>(citaService.findByAll(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }            
    }

    @GetMapping("/list/{id}") 
    @ResponseBody
    public ResponseEntity<Cita> consultaPorId(@PathVariable Integer id,@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario){ 
        Administrador admon=new Administrador();
        admon=administradorRepository.login(usuario, Hash.sha1(clave));
        if (admon!=null) {
            return new ResponseEntity<>(citaService.findById(id),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }   
    }

    //Consumo de Recursos userr
    @PostMapping(value="/agendar")
    @ResponseBody
    public ResponseEntity<Cita> agendar(@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario, @Valid @RequestBody Cita cita){   
        Userr userr=new Userr();
        userr=userrRepository.login(usuario, Hash.sha1(clave));
        if (userr!=null) {
            return new ResponseEntity<>(citaService.save(cita), HttpStatus.OK); 
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); 
        }            
    }

    @GetMapping("/consulta_cita_paciente")
    @ResponseBody
    public ResponseEntity<List<Cita>> consulta_cita_paciente(@RequestParam ("idp") String idp,@RequestHeader ("usuario") String usuario,@RequestHeader ("clave") String clave) { 
        Userr userr=new Userr();
        userr=userrRepository.login(usuario, Hash.sha1(clave));
        if (userr!=null) {
            return new ResponseEntity<>(citaService.consulta_cita_paciente(idp),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }        
    }

    @GetMapping("/consulta_cita_medico")
    @ResponseBody
    public ResponseEntity<List<Cita>> consulta_cita_medico(@RequestParam ("idm") String idm,@RequestHeader ("usuario") String usuario,@RequestHeader ("clave") String clave) { 
        Userr userr=new Userr();
        userr=userrRepository.login(usuario, Hash.sha1(clave));
        if (userr!=null) {
            return new ResponseEntity<>(citaService.consulta_cita_medico(idm),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }        
    }  

    @GetMapping("/consulta/{id}") 
    @ResponseBody
    public ResponseEntity<Cita> consultaPorId_userr(@PathVariable Integer id,@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario){ 
        Userr userr=new Userr();
        userr=userrRepository.login(usuario, Hash.sha1(clave));
        if (userr!=null) {
            return new ResponseEntity<>(citaService.findById(id),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }   
    }

    @PutMapping(value="/agregar_observacion") 
    public void agregar_observacion(@RequestParam("idc") String idc,@RequestParam("observacion") String observacion, @RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario){ 
        Userr userr1=new Userr();
        userr1=userrRepository.login(usuario, Hash.sha1(clave));
        if (userr1!=null) {
           citaService.agregar_observacion(idc, observacion);
        }          
    }

    @PutMapping(value="/terminar_cita") 
    public void terminar_cita(@RequestParam("idc") String idc,@RequestParam("estado") String estado, @RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario){ 
        Userr userr1=new Userr();
        userr1=userrRepository.login(usuario, Hash.sha1(clave));
        if (userr1!=null) {
           citaService.terminar_cita(idc, estado);
        }          
    }

    @PutMapping(value="/cancelar_cita") 
    public void cancelar_cita(@RequestParam("idc") String idc,@RequestParam("estado") String estado, @RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario){ 
        Userr userr1=new Userr();
        userr1=userrRepository.login(usuario, Hash.sha1(clave));
        if (userr1!=null) {
           citaService.cancelar_cita(idc, estado);
        }          
    }
    
}
