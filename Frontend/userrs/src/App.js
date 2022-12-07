import './App.css';
import { BrowserRouter, Route, Routes} from "react-router-dom"
import Login from "./components/Login"
import MenuPaciente from "./components/MenuPaciente"
import MenuMedico from "./components/MenuMedico"
import EditarPaciente from './components/EditarPaciente';
import CitaPaciente from './components/CitaPaciente';
import Paciente from './components/Paciente';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Login/>} />
          <Route path="/paciente" element={<Paciente/>} />
          <Route path="/menupaciente" element={<MenuPaciente/>} />
          <Route path="/menumedico" element={<MenuMedico/>} />
          <Route path="/editarpaciente/:id" element={<EditarPaciente/>} />
          <Route path="/citapaciente/:id" element={<CitaPaciente/>} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
