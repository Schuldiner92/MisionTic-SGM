import swal from "sweetalert"
import axios from "axios";
import { useState,useEffect }  from "react";
import { useNavigate, useParams} from "react-router-dom";
const URI = "http://localhost:8080/cita/"

let headers = {
    "usuario" : sessionStorage.getItem("usuario"),
    "clave"   : sessionStorage.getItem("clave")
};

const EditarCita= () => {
    const[citas, setcitas] =useState([])
    const[id_cita, setId_cita] =useState("");
    const[observacion, setObservacion] =useState("");
    const[nombre_paciente, setNombre_paciente] =useState("");
    const[apellido_paciente, setApellido_paciente] =useState("");
    const {id} = useParams()
    const navigate = useNavigate();  

    const editar = async (e) => {
        e.preventDefault();
        const UpdateCita= await axios({
            method: "PUT",
            url: URI + "agregar_observacion?idc="+id_cita+"&observacion="+observacion,
            headers: headers 
        });
        swal("Observación agregada", "", "info");
        navigate("/citasmedico");
    }
    useEffect(()=>{
        getCitaById();
    },[]) 

    const getCitaById = async () => {
        try{
            const res =  await axios({
                method : "GET",
                url : URI + "consulta/"+id,
                headers: headers   
            });                                         
            setId_cita(res.data.Id_cita)
            setNombre_paciente(res.data.paciente.nombre_paciente)
            setApellido_paciente(res.data.paciente.apellido_paciente)
            setObservacion(res.data.Observacion)                                                                      
            alert(id_cita+" "+nombre_paciente+" "+apellido_paciente+" "+observacion)
        }
        catch (error) {
            swal("¡No tiene Acceso a esta Opción!", "Presiona el botón!", "error");
            navigate("/menumedico");
        }        
    } 

    const Cancelar = () => {               
        navigate("/citasmedico")
    }


    return(
        <div>
    <h3>Editar Datos</h3>
    <div className="container col-2">
    <form onSubmit={editar}>
        <div className="mb-3">
        <label className="form-label">ID Cita</label>
        <input
            value={id_cita}
            onChange={(e) => setId_cita(parseInt(e.target.value))} //pasamos a entero el id en el campo
            type="number" step="1"
            disabled
            className="form-control"
        />
        </div>
        <div className="mb-3">
        <label className="form-label">Paciente</label>
        <input
            value={nombre_paciente+" "+apellido_paciente}
            onChange={(e) => setNombre_paciente(e.target.value)}
            type="String"            
            className="form-control"
            disabled          
        />
        </div>
        <div className="mb-3">
        <label className="form-label">Observación</label>
        <textarea
            value={observacion}
            onChange={(e) => setObservacion(e.target.value)}
            type="text"            
            className="form-control"
            onInvalid={e => e.target.setCustomValidity('obligatorio')}
            onInput={e => e.target.setCustomValidity('')}
            required
        />
        </div>                         
        <button type="submit" className="btn btn-dark">
        Agregar Observación
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
export default EditarCita;