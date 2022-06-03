import React from "react";
import { Col, Container, Row } from "react-bootstrap";
import { Outlet } from "react-router-dom";
import DeliveryList from "../../components/DeliveryList";

const Dashboard = () => {

    const testItems = [
        {
            "id": 100,
            "name": "delivery1",
            "status": "requested",
            "courier": 200,
            "business": 10,
        },
        {
            "id": 101,
            "name": "delivery2",
            "status": "in progress",
            "courier": 200,
            "business": 11,
        },
        {
            "id": 102,
            "name": "delivery3",
            "status": "in progress",
            "courier": 200,
            "business": 10,
        },
    ]


    return(
        <Container>
            <h2>Ongoing Deliveries</h2>
            <Row>
                <Col md="6">
                    <DeliveryList items={testItems} link="" />
                </Col>
                <Col md="6">
                    <Outlet />
                </Col>
            </Row>
        </Container>
    );
};
export default Dashboard;