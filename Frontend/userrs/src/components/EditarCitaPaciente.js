import swal from "sweetalert"
import axios from "axios";
import { useState,useEffect }  from "react";
import { useNavigate, useParams} from "react-router-dom";
const URI = "http://localhost:8080/cita/"

const EditarCitaPaciente = () => {
    return(
        <div>
            <h3>Editar Cita Paciente</h3>
        </div>
    )
}

export default EditarCitaPaciente;