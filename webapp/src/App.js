import './App.css';
import { Outlet, useLocation, useNavigate } from 'react-router-dom';
import { Row, Col } from 'react-bootstrap';
import SideNav from './components/SideNav';
import { useEffect } from 'react';

function App() {

  const location = useLocation();
  const navigate = useNavigate();

  useEffect(()=>{
    if (location.pathname === "/")
      navigate("login");
  })

  return (
    <Row className='h-100 justify-content-end'>
      <Col md="2" className='h-100 position-fixed start-0 ps-0'>
        <SideNav />
      </Col>
      <Col md="10" className='pt-4'>
        <Outlet />
      </Col>
    </Row>
  );
}

export default App;
