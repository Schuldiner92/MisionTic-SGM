import swal from "sweetalert";
import {useNavigate } from "react-router-dom";


const MenuMedico = () => {
    const navigate = useNavigate();

    const Logout = () => {
        sessionStorage.clear()
        swal("Sesión Cerrada","","success")        
        navigate("/")
    }
    
    return(      
        <div>
        <nav className="navbar navbar-expand-lg ">
            <div className="container-fluid">
                <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                    <li className="nav-item">
                    <a className='btn btn-dark' href="/paciente"><i className="fas fa-user-alt fa-lg"></i> Mis Datos</a>
                    </li>
                    <li className="nav-item">
                    <a className='btn btn-dark' href="/citaspaciente"><i className="fas fa-book-medical fa-lg"></i> Citas</a>
                    </li>
                </ul>
                <form className="d-flex">
                    <button className="btn btn-outline-dark" type="button" onClick={Logout}>
                        Cerrar Sesión
                    </button>
                </form>
            </div>
        </nav>   
        <div>
            <h3>Medico</h3>
        </div>     
        </div>           
    );
}

export default MenuMedico;