import swal from "sweetalert"
import axios from "axios";
import { useState,useEffect }  from "react";
import { useNavigate,Link} from "react-router-dom";
const URI = "http://localhost:8080/cita/"
const URI2 = "http://localhost:8080/medico/"

let headers = {
    "usuario" : sessionStorage.getItem("usuario"),
    "clave"   : sessionStorage.getItem("clave")
};

const CitaMedico = () => {    
    const[id_medico, setId_medico] =useState();    
    const [citas, setCitas] = useState([])  
    const navigate = useNavigate();
    
    //Obtener el ID del medico del userr logeado
    useEffect(()=>{
        getMedicoById();
    }) 
    const getMedicoById = async () => {
        try{
            const res1 =  await axios({
                method: "GET",
                url : URI2 + "consulta/"+sessionStorage.getItem("usuario"),
                headers: headers 
            });           
            setId_medico(res1.data.id_medico)                                                             
        }        
        catch (error) {
            swal("¡No tiene Acceso a esta Opción!", "Presiona el botón!", "error");
            navigate("/");
        }        
    }
    //Consulta de citas del médico logeado
    useEffect(() =>{
        getCitas([])
    })  
    const getCitas = async () =>{        
        try {            
            const res = await axios({
                method : "GET",
                url : URI + "consulta_cita_medico?idm="+id_medico, //usamos el id_medico de la funcion getMedicoById
                headers: headers                
            });                       
            setCitas(res.data)           
        }
        catch (error) {
            swal("¡No tiene Acceso a esta Opción!", "Presiona el botón!", "error");
            navigate("/");
        }
    }


    const Regresar = () => {               
        navigate("/menumedico")

    }
    
    return(      
        <div className='container'>
            <div className='row'>
                <div className='col'>                     
                    <h2>Mis Citas</h2>                    
                    <p></p>
                    <table className='table'>
                        <thead className='table-primary'>
                            <tr>
                                <th>ID Cita</th>
                                <th>Fecha y Hora</th>
                                <th>Observación</th>
                                <th>Estado</th>
                                <th>Médico</th>
                                <th>Paciente</th>
                            </tr>
                        </thead>
                        <tbody>
                            { citas.map ( (cita) => (
                               
                                <tr key={ cita.id_cita}>
                                    <td> { cita.id_cita} </td>
                                    <td> { cita.fecha_hora.substring(0,10)} </td>
                                    <td> { cita.observacion } </td>
                                    <td> { cita.estado } </td>
                                    <td> { cita.medico.nombre_medico+" "+cita.medico.apellido_medico } </td>
                                    <td> { cita.paciente.nombre_paciente+" "+cita.paciente.apellido_paciente} </td>                                    
                                    <td>                                                      
                                        <Link to={`/editarcita/${cita.id_cita}`} className='btn btn-dark fa-lg'><i className="fas fa-edit"></i></Link>                                   
                                    </td>
                                </tr>
                            )) }
                        </tbody>
                    </table>  
                    <b>*A: Activa  -  C: Cancelada  -  F: Finalizada*</b>                  
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

export default CitaMedico;