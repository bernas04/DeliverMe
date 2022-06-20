import React from "react";
import { Nav } from "react-bootstrap";
import { Link } from "react-router-dom";
import "./index.css";

const handleClick = () => {
    localStorage.clear();
}



const SideNav = () => {

    return(
        <Nav defaultActiveKey="/manage" className="flex-column side-nav bg-primary w-100 h-100">
            <Nav.Item><Link to="/manage">Dashboard</Link></Nav.Item>
            <Nav.Item><Link to="/deliveries">Deliveries</Link></Nav.Item>
            <Nav.Item><Link to="/couriers">Couriers</Link></Nav.Item>
            <Nav.Item><Link id="velhice" to="/login" onClick={handleClick}>Exit</Link></Nav.Item>
        </Nav>
    );
};
export default SideNav;