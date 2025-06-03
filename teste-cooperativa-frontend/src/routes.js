import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Login from './pages/Login.js';
import Menu from './pages/Menu.js';
import AssociateForm from './pages/AssociateForm.js';

function AppRoutes() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/menu" element={<Menu />} />
        <Route path="/associateForm" element={<AssociateForm />} />
      </Routes>
    </BrowserRouter>
  );
}

export default AppRoutes;