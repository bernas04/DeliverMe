import React, {Component} from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './index.css';
import App from './App';
import Dashboard from './pages/Dashboard';
import Deliveries from './pages/Deliveries';
import DeliveryDetails from './pages/DeliveryDetails';
import Couriers from './pages/Couriers';
import CourierDetails from './pages/CourierDetails';
import LogIn from './pages/Login'
import ProtectedRoutes from './pages/ProtectedRoutes';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <BrowserRouter>
    <Routes>
      <Route path='login' element={<LogIn/>} />
      <Route path='/' element={<App />}>
        <Route element={<ProtectedRoutes/>}>
          <Route path='manage' element={<Dashboard />}>
            <Route path=':deliveryId' element={<DeliveryDetails />} />
          </Route>
          <Route path='deliveries' element={<Deliveries />} />
          <Route path='deliveries/:deliveryId' element={<DeliveryDetails />} />
          <Route path='couriers' element={<Couriers />} />
          <Route path='couriers/:courierId' element={<CourierDetails />} />
        </Route>
      </Route>
    </Routes>
  </BrowserRouter>
);