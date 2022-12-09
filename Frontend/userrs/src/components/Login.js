import swal from "sweetalert";
import axios from "axios";
import { useState }  from "react";
import { useNavigate } from "react-router-dom";
const URI = "http://localhost:8080/userr/"

const Login = () => {
    const navigate = useNavigate();
    const [userrs, setUserrs] = useState([])   
    const [id_userr, setId_userr] = useState("");
    const [clave_userr, setClave_userr] = useState("");

    const guardar = async (e) => {
        e.preventDefault();
        
        try {          
            const res = await axios({
                method : "GET",
                url: URI + "login?usuario="+id_userr+"&clave="+clave_userr
            });          
            setUserrs(res.data) 
                     
            if (res.data.id_userr==null) {                
                swal("Usuario o Contraseña incorrectos", "Presiona el botón!", "error");
                navigate("/");
                
            } else {                
                sessionStorage.setItem("usuario",id_userr);
                sessionStorage.setItem("clave",clave_userr);                                                       
                swal("Bienvenido "+res.data.email+"!", "Presiona el botón!", "success");
                
                if(res.data.rol==="P")  
                    navigate("/menupaciente");
                else
                    navigate("/menumedico");
            }
        }
        catch (error) {
            swal("Operación NO realizada","")
        }

      };

    return(
        
        <div>
             
            <h2>Iniciar Sesión</h2>
            <div className="container col-2">
            <form onSubmit={guardar}>
                
                <div className="mb-3">
                    <label className="form-label">ID Usuario</label>
                    <input
                        value={id_userr}
                        onChange={(e) => setId_userr(e.target.value)}
                        type="text"
                        maxLength={15}
                        required
                        onInvalid={e => e.target.setCustomValidity('Debe digitar su ID de usuario')}
                        onInput={e => e.target.setCustomValidity('')}
                        className="form-control"
                    />
                </div>
                <div className="mb-3">
                    <label className="form-label">Contraseña</label>
                    <input
                        value={clave_userr}
                        onChange={(e) => setClave_userr(e.target.value)}
                        type="password"
                        maxLength={50}
                        required
                        onInvalid={e => e.target.setCustomValidity('Debe digitar la contraseña')}
                        onInput={e => e.target.setCustomValidity('')}
                        className="form-control"
                    />
                </div>
                <button type="submit" className="btn btn-dark">
                Login
                </button>
            </form>
        </div>
        </div> 
    );
};

export default Login;