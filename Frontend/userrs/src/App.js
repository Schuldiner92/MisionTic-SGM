import './App.css';
import { BrowserRouter, Route, Routes} from "react-router-dom"
import Login from "./components/Login"
import MenuPaciente from "./components/MenuPaciente"
import CitaMedico from "./components/CitaMedico"
import EditarPaciente from './components/EditarPaciente';
import CitaPaciente from './components/CitaPaciente';
import Paciente from './components/Paciente';
import AgendarCita from './components/AgendarCita'
import MenuMedico from "./components/MenuMedico"
import EditarCita from './components/EditarCita';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Login/>} />
          <Route path="/menupaciente" element={<MenuPaciente/>} />
          <Route path="/paciente" element={<Paciente/>} />
          <Route path="/editarpaciente/:id" element={<EditarPaciente/>} />  
          <Route path="/citaspaciente" element={<CitaPaciente/>} />
          <Route path="/agendarcita" element={<AgendarCita/>} />                  
          <Route path="/citasmedico" element={<CitaMedico/>} />  
          <Route path="/menumedico" element={<MenuMedico/>} />      
          <Route path="/editarcita/:id" element={<EditarCita />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
