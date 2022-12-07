import swal from "sweetalert"
import axios from "axios";
import { useState,useEffect }  from "react";
import { useNavigate, useParams} from "react-router-dom";
const URI = "http://localhost:8080/paciente/"

let headers = {
    "usuario" : sessionStorage.getItem("usuario"),
    "clave"   : sessionStorage.getItem("clave")
};

const EditarPaciente = () => {
    
    const[id_paciente, setId_paciente] =useState("");
    const[nombre_paciente, setNombre_paciente] =useState("");
    const[apellido_paciente, setApellido_paciente] =useState("");
    const[sexo, setSexo] =useState("");
    const[fecha_nacimiento, setFecha_nacimiento] =useState("");
    const navigate = useNavigate();    
    const {id} = useParams()

    const editar = async (e) => {
        e.preventDefault();
        const UpdatePaciente= await axios({
            method: "PUT",
            url: URI + "cambiar_nom_ape?idp="+id_paciente+"&nombre="+nombre_paciente+"&apellido="+apellido_paciente,
            headers: headers 
        });
        swal("Datos Actualizados", "", "info");
        navigate("/paciente");
    }
    
    useEffect(()=>{
        getPacienteById();
    },[]) //Los corchetes [] evitan que se autodigiten los datos en los input de forma infinita
    
    const getPacienteById = async () => {
        try{
            const res =  await axios({
                method: "GET",
                url : URI + "consulta/"+id,
                headers: headers 
            });           
            setId_paciente(res.data.id_paciente)
            setNombre_paciente(res.data.nombre_paciente)
            setApellido_paciente(res.data.apellido_paciente)
            setSexo(res.data.sexo)
            setFecha_nacimiento(res.data.fecha_nacimiento)
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
    <div>
    <h3>Editar Datos</h3>
    <div className="container col-2">
    <form onSubmit={editar}>
        <div className="mb-3">
        <label className="form-label">ID Paciente</label>
        <input
            value={id_paciente}
            onChange={(e) => setId_paciente(e.target.value)}
            type="text"
            disabled="false"
            className="form-control"
        />
        </div>
        <div className="mb-3">
        <label className="form-label">Nombres</label>
        <input
            value={nombre_paciente}
            onChange={(e) => setNombre_paciente(e.target.value)}
            type="String"            
            className="form-control"
            onInvalid={e => e.target.setCustomValidity('obligatorio')}
            onInput={e => e.target.setCustomValidity('')}
            required
        />
        </div>
        <div className="mb-3">
        <label className="form-label">Apellidos</label>
        <input
            value={apellido_paciente}
            onChange={(e) => setApellido_paciente(e.target.value)}
            type="String"            
            className="form-control"
            onInvalid={e => e.target.setCustomValidity('obligatorio')}
            onInput={e => e.target.setCustomValidity('')}
            required
        />
        </div>
        <div className="mb-3">
        <label className="form-label">Sexo</label>
        <input
            value={sexo}
            onChange={(e) => setSexo(e.target.value)}
            type="text"
            disabled="false"
            className="form-control"
        />
        </div>
        <div className="mb-3">
        <label className="form-label">Fecha Nacimiento</label>
        <input
            value={fecha_nacimiento.substring(0,10)}
            onChange={(e) => setFecha_nacimiento(e.target.value)}
            type="text"
            disabled="false"
            className="form-control"
        />
        </div>            
        <button type="submit" className="btn btn-dark">
        Actualizar Datos
        </button>
    </form>
    <p></p>
    <button className="btn btn-outline-dark" type="button" onClick={Regresar}>
        Regresar
    </button>
    </div>
    </div>

    );
}

export default EditarPaciente;