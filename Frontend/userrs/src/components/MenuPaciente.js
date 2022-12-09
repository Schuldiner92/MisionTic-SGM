import swal from "sweetalert";
import {useNavigate } from "react-router-dom";


const MenuPaciente = () => {
    const navigate = useNavigate();

    const Logout = () => {
        sessionStorage.clear()
        swal("Sesión Cerrada","","success")        
        navigate("/")
    }

    const Paciente = () => {               
        navigate("/paciente")
    }    

    const Citaspaciente = () => {               
        navigate("/citaspaciente")
    }

    return(      
        <div>      
        <nav className="navbar navbar-expand-lg ">
            <div className="container-fluid">
                <div className="btn-group" role="group" aria-label="Basic example">
                    <button type="button" className="btn btn-dark" href="/paciente" onClick={Paciente}><i className="fas fa-user-alt fa-lg"></i> Mis Datos</button>
                    <button type="button" className="btn btn-dark" href="/citaspaciente" onClick={Citaspaciente}><i className="fas fa-book-medical fa-lg"></i> Citas</button>
                </div>                
                <form className="d-flex">
                    <button className="btn btn-outline-dark" type="button" onClick={Logout}>
                        Cerrar Sesión
                    </button>
                </form>
            </div>
        </nav>       
        </div>    
    );
}

export default MenuPaciente;