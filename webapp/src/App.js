import './App.css';
import { Outlet } from 'react-router-dom';
import { Row, Col } from 'react-bootstrap';
import SideNav from './components/SideNav';

function App() {
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
