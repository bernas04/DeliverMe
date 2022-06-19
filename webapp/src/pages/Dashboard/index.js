import React, { useEffect, useState } from "react";
import { Col, Container, Row } from "react-bootstrap";
import { Outlet } from "react-router-dom";
import DeliveryList from "../../components/DeliveryList";

const Dashboard = () => {

    const [fetchError, setFetchError] = useState(false);
    const [deliveries, setDeliveries] = useState([]);
    // change to requested and in-progress only
    useEffect(()=>{
        fetch(
            `${process.env.REACT_APP_API_URL}/purchases/Purchases`,
            {
                headers: {'Access-Control-Allow-Origin': '*'}
            }
        )
        .then(response => response.json())
        .then(data => {
            console.log(data)
            setDeliveries(data)
        })
        .catch((reason) => {
            console.log(reason)
            setFetchError(true)
        })
    }, [])



    
    return(
        <Container>
            <h2>Ongoing Deliveries</h2>
            {
                fetchError ?
                <h3>An error ocorred while fetching data</h3>
                :
                <Row>
                    <Col md="6">
                        <DeliveryList items={deliveries} link="" />
                    </Col>
                    <Col md="6">
                        <Outlet />
                    </Col>
                </Row>
            }
        </Container>
    );
};
export default Dashboard;