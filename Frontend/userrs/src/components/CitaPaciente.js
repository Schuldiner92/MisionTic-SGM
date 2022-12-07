import swal from "sweetalert"
import axios from "axios";
import { useState,useEffect }  from "react";
import { Link,useNavigate,useParams} from "react-router-dom";
const URI2 = "http://localhost:8080/paciente/"
const URI = "http://localhost:8080/cita/"

let headers = {
    "usuario" : sessionStorage.getItem("usuario"),
    "clave"   : sessionStorage.getItem("clave")
};

const CitaPaciente= () => {

    const navigate = useNavigate();
    const[id_paciente, setId_paciente] =useState();    
    const [citas, setCitas] = useState([])      
    
    //Obtener el ID del paciente del userr logeado
    useEffect(()=>{
        getPacienteById();
    }) 
    
    const getPacienteById = async () => {
        try{
            const res =  await axios({
                method: "GET",
                url : URI2 + "consulta/"+sessionStorage.getItem("usuario"),
                headers: headers 
            });           
            setId_paciente(res.data.id_paciente)                                                             
        }        
        catch (error) {
            swal("¡No tiene Acceso a esta Opción!", "Presiona el botón!", "error");
            navigate("/menupaciente");
        }        
    }
    //Consulta de citas del paciente
    useEffect(() =>{
        getCitas()
    })
  
    const getCitas = async () =>{        
        try {            
            const res = await axios({
                method : "GET",
                url : URI + "consulta_cita_paciente?idp="+id_paciente, //usamos el id_paciente de la funcion getPacienteById
                headers: headers                
            });                       
            setCitas(res.data)
        }
        catch (error) {
            swal("¡No tiene Acceso a esta Opción!", "Presiona el botón!", "error");
            navigate("/menupaciente");
        }
    }
    
    const Regresar = () => {               
        navigate("/menupaciente")

    }

    return(
        <div className='container'>
            <div className='row'>
                <div className='col'>
                    <table className='table'>
                        <thead className='table-primary'>
                            <tr>
                                <th>ID Cita</th>
                                <th>Fecha y Hora</th>
                                <th>Estado</th>
                                <th>ID Medico</th>
                                <th>ID Paciente</th>
                            </tr>
                        </thead>
                        <tbody>
                            { citas.map ( (cita) => (
                               
                                <tr key={ cita.id_cita}>
                                    <td> { cita.id_cita} </td>
                                    <td> { cita.fecha_hora.substring(0,10)} </td>
                                    <td> { cita.estado } </td>
                                    <td> { cita.id_medico } </td>
                                    <td> { cita.id_paciente} </td>                                    
                                    <td>
                                        <Link to={`/editarcitapaciente/${cita.id_cita}`} className='btn btn-dark'><i className="fas fa-trash"></i> Cancelar</Link>                                        
                                    </td>
                                </tr>
                            )) }
                        </tbody>
                    </table>                    
                    <form className="d-flex">
                    <button className="btn btn-outline-dark" type="button" onClick={Regresar}>
                        Volver
                    </button>
                </form>
                </div>    
            </div>
        </div>
    );       
}

export default CitaPaciente;