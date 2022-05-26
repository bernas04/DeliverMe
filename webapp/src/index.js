import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './index.css';
import App from './App';
import Dashboard from './pages/Dashboard';
import Deliveries from './pages/Deliveries';
import DeliveryDetails from './pages/DeliveryDetails';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <BrowserRouter>
    <Routes>
      <Route path='/' element={<App />}>
        <Route index element={<Dashboard />} />
        <Route path='deliveries' element={<Deliveries />} />
        <Route path='deliveries/:deliveryId' element={<DeliveryDetails />} />
      </Route>
    </Routes>
  </BrowserRouter>
);