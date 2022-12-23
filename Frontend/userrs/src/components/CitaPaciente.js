import swal from "sweetalert"
import axios from "axios";
import { useState,useEffect }  from "react";
import { useNavigate} from "react-router-dom";
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
            const res1 =  await axios({
                method : "GET",
                url : URI2 + "consulta_paciente?idu="+sessionStorage.getItem("usuario"),
                headers: headers 
            });           
            res1.data.map ( (paciente) => ( //Mapeamos el id del paciente
                setId_paciente(paciente.id_paciente)
            ))                                                                   
        }        
        catch (error) {
            swal("¡No tiene Acceso a esta Opción!", "Presiona el botón!", "error");
            navigate("/menupaciente");
        }        
    }
    //Consulta de citas del paciente
    useEffect(() =>{
        getCitas([])
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
    //Cancelar la cita del paciente
    const cancelarCita = async (id) => {
        swal(
            {
                title: "¿Cancelar Cita?",
                text: "Está seguro que quiere cancelar esta cita?",
                icons: "Warning",
                buttons: true,
                dangerMode: true,
            })
            .then(async (willUpdate) =>{
                if (willUpdate){
                    const res = await axios({
                        method: "PUT",
                        url: URI + "cancelar_cita?idc="+id+"&estado=C", //Agregamos la C(cancelada) en este método, porque no es necesario digitarla
                        headers: headers 
                    });                    
                    getCitas()
                    swal("La cita se ha cancelado",{ 
                        icon: "info",
                    });
                } else{
                    swal("No se canceló la cita",{ 
                        icon: "info",
                    });
                }
            });    
    }

    const Regresar = () => {               
        navigate("/menupaciente")

    }

    return(
        <div className='container'>
            <div className='row'>
                <div className='col'>
                    <h2>Mis Citas</h2>
                    <p></p>
                    <a className='btn btn-dark' href="/agendarcita"><i className="far fa-calendar-plus fa-lg"></i> Agendar</a>
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
                                        <button 
                                            onClick={() => { cancelarCita(cita.id_cita) } } 
                                            className='btn btn-dark'
                                            hidden={cita.estado!=="A"}> {/*Se oculta el botón para citas que no sean A(Activas)*/}
                                            <i className="far fa-calendar-times fa-lg"></i>Cancelar                                            
                                        </button>                                   
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

export default CitaPaciente;