import swal from "sweetalert"
import axios from "axios";
import { useState,useEffect }  from "react";
import { Link,useNavigate} from "react-router-dom";
const URI = "http://localhost:8080/paciente/"

let headers = {
    "usuario" : sessionStorage.getItem("usuario"),
    "clave"   : sessionStorage.getItem("clave")
};

const Paciente = () => {
    const navigate = useNavigate();
    const [pacientes, setPacientes] = useState([])

    useEffect(() =>{
        getPacientes([])
    })

    const getPacientes = async () =>{        
        try {            
            const res = await axios({
                method : "GET",
                url : URI + "consulta_paciente?idu="+sessionStorage.getItem("usuario"),
                headers: headers                
            });                       
            setPacientes(res.data)                  
        }
        catch (error) {
            swal("¡No tiene Acceso a esta Opción!", "Presiona el butón!", "error");
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
                    <h2>Mis Datos Personales</h2>                   
                    <table className='table'>
                        <thead className='table-primary'>
                            <tr>
                                <th>ID</th>
                                <th>Nombres</th>
                                <th>Apellidos</th>
                                <th>Sexo</th>
                                <th>Fecha Nacimiento</th>
                            </tr>
                        </thead>
                        <tbody>
                            { pacientes.map ( (paciente) => (
                               
                                <tr key={ paciente.id_paciente}>
                                    <td> { paciente.id_paciente } </td>
                                    <td> { paciente.nombre_paciente } </td>
                                    <td> { paciente.apellido_paciente } </td>
                                    <td> { paciente.sexo } </td>
                                    <td> { paciente.fecha_nacimiento.substring(0,10) } </td>                                    
                                    <td>
                                        <Link to={`/editarpaciente/${paciente.id_paciente}`} className='btn btn-dark'><i className="far fa-edit fa-lg"></i> Editar</Link>                                        
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

export default Paciente