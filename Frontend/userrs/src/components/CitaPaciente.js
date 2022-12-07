import swal from "sweetalert"
import axios from "axios";
import { useState,useEffect }  from "react";
import { useNavigate, useParams} from "react-router-dom";
const URI = "http://localhost:8080/cita/"

let headers = {
    "usuario" : sessionStorage.getItem("usuario"),
    "clave"   : sessionStorage.getItem("clave")
};

const CitaPaciente= () => {
    return(
        <div>
            <button className="btn btn-outline-dark" type="button" >
                        Cerrar Sesión
            </button>
        </div>
    );       
}

export default CitaPaciente;