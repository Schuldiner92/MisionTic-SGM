
import swal from "sweetalert"
import axios from "axios";
import { useState,useEffect }  from "react";
import { useNavigate} from "react-router-dom";
//ms y moment se usan para validar fechas
import ms from 'ms';
import moment from 'moment';//
const URI = "http://localhost:8080/cita/"
const URI2 = "http://localhost:8080/paciente/"
const URI3 = "http://localhost:8080/medico/"

let headers = {
    "usuario" : sessionStorage.getItem("usuario"),
    "clave"   : sessionStorage.getItem("clave")
};
const AgendarCita = () => {    
    const [fecha_hora, setFecha_hora] = useState("");
    const [estado, setEstado] = useState("");
    const [medico, setMedico] = useState([]);
    const [medicos, setMedicos] = useState([]);
    const[id_paciente,setId_Paciente] = useState("");
    const[nombre_paciente,setNombre_pacientes] = useState("");
    const[apellido_paciente,setApellido_pacientes] = useState("");
    const navigate = useNavigate();  
    
    //Fecha minima y máxima usando ms y moment
    const [minDate, setMinDate] = useState(null)
    const [maxDate, setMaxDate] = useState(null)
    useEffect(() => {
        const minsec = ms('60d') //Citas con un máximo 60 días en el futuro
        const aux = ms('1d')
        const min_date = new Date(+new Date()+aux);
        const max_date = new Date(+new Date()+minsec);
        setMinDate(moment(min_date).format('YYYY-MM-DD'));
        setMaxDate(moment(max_date).format('YYYY-MM-DD'))
    }) //
       
    const guardar = async (e) => {

        e.preventDefault();

        const insertCita = await axios({
            method: "POST",
            url: URI+"agendar",
            data: {
                fecha_hora: fecha_hora, 
                estado: estado, 
                medico: {
                    id_medico: medico, 
                    nombre_medico: null, 
                    apellido_medico:null,
                    especialidad:null,
                    userr: {
                        id_userr: null,
                        email: null,
                        clave_userr: null,
                        rol: null                   
                    }
                },
                paciente: {
                    id_paciente: id_paciente,
                    nombre_paciente: null,
                    apellido_paciente: null,
                    sexo: null,
                    fecha_nacimiento: null,
                    userr: {
                      id_userr: null,
                      email: null,
                      clave_userr: null,
                      rol: null
                    }
                }
            },
            headers: headers 
        });
        swal("Cita Agendada",{ 
            icon: "info",
        });
        navigate("/citaspaciente");

    }
    //Obtener todos los médicos
    const ObtenerMedicos = async () =>{
        try {
            
            const res1 = await axios({
                method : "GET",
                url : URI3 + "consulta",
                headers: headers                
            });            
            setMedicos(res1.data)            
        }
        catch (error) {            
        }
    }
    useEffect(() =>{
        ObtenerMedicos([]);
    })    
    //Obtener el paciente logeado
    const ObtenerPaciente = async () =>{
        try {            
            const res2 = await axios({
                method : "GET",
                url : URI2 + "consulta/"+sessionStorage.getItem("usuario"),
                headers: headers                
            });            
            setId_Paciente(res2.data.id_paciente)  
            setNombre_pacientes(res2.data.nombre_paciente)   
            setApellido_pacientes(res2.data.apellido_paciente)  
            setEstado("A") //Estado no un es campo de paciente, pero al ponerlo aquí no genera problemas
        }
        catch (error) {            
        }
    }
    useEffect(() =>{
        ObtenerPaciente([]);
    }) 
    
    const Cancelar = () => {               
        navigate("/citaspaciente")
    }

    return(
        <div>
        <h3>Agendar Cita</h3>
        <div className="container col-2">
        <form onSubmit={guardar}>
            <div className="mb-3">
            <label className="form-label">Fecha</label> 
            <input     
                type="date" 
                min={minDate}
                max={maxDate}
                onChange={(e) => setFecha_hora(e.target.value)}              
                className="form-control"
                required
                onInvalid={e => e.target.setCustomValidity('La fecha es obligatoria')}
                onInput={e => e.target.setCustomValidity('')}
            />
            </div>
            <div className="mb-3">
            <label className="form-label">Estado</label>
            <input   
                value={estado}  
                type="text" 
                onChange={(e) => setEstado(e.target.value)}              
                className="form-control"                             
                disabled
            />
            </div>
            <div className="mb-3">
            <label className="form-label">Paciente</label>
            <select
                value={id_paciente}
                onChange={(e) => setId_Paciente(e.target.value)}
                disabled
                className="form-control">
                    <option value={id_paciente}>{nombre_paciente+" "+apellido_paciente}</option>                                      
            </select>
            </div>
            <div className="mb-3">
            <label className="form-label">Médico</label>
            <select
                value={medico}
                onChange={(e) => setMedico(e.target.value)}                
                className="form-control" required>
                    <option value="">Seleccione un Médico</option>
                    { medicos.map ( (medico) => (
                        <option value={medico.id_medico}>{medico.nombre_medico+" "+medico.apellido_medico+" ("+medico.especialidad+")"}</option>
                    )) 
                    }                
            </select>
            </div>
            <button type="submit" className="btn btn-dark">
            Guardar
            </button>
        </form>
        <p></p>
        <button className="btn btn-outline-dark" type="button" onClick={Cancelar}>
        Cancelar
        </button>
    </div>
    </div>
    
    )
}

export default AgendarCita;
