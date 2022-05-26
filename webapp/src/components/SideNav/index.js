import React from "react";
import { Nav } from "react-bootstrap";
import { Link } from "react-router-dom";
import "./index.css";

const SideNav = () => {

    return(
        <Nav defaultActiveKey="/" className="flex-column side-nav bg-primary w-100 h-100">
            <Nav.Item><Link to="/">Dashboard</Link></Nav.Item>
            <Nav.Item><Link to="/deliveries">Deliveries</Link></Nav.Item>
            <Nav.Item><Link to="/couriers">Couriers</Link></Nav.Item>
        </Nav>
    );
};
export default SideNav;