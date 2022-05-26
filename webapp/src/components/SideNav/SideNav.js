import React from "react";
import { Nav } from "react-bootstrap";
import { Link } from "react-router-dom";

const SideNav = () => {

    return(
        <Nav defaultActiveKey="/" className="flex-column">
            <Nav.Item><Link to="/">Dashboard</Link></Nav.Item>
            <Nav.Item><Link to="/deliveries">Deliveries</Link></Nav.Item>
        </Nav>
    );
};
export default SideNav;