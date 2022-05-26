import './App.css';
import { Outlet } from 'react-router-dom';
import { Row, Col } from 'react-bootstrap';
import SideNav from './components/SideNav/SideNav';

function App() {
  return (
    <Row>
      <Col md="2">
        <SideNav />
      </Col>
      <Col md="10">
        <Outlet />
      </Col>
    </Row>
  );
}

export default App;
