import React from "react";
import { Link } from "react-router-dom";
import { Card, Col } from 'react-bootstrap';

const CourierCard = ({courierDetails}) => {

    return(
        <Card className="my-3">
            <Card.Body className="row">
                <Col xs="10">
                    <Card.Title>{courierDetails.name}</Card.Title>
                </Col>
                <Col xs="2" className="d-flex flex-column justify-content-center">
                    <Link className="view-item-btn ms-auto" to={"/couriers/" + courierDetails.id}>View</Link>
                </Col>
            </Card.Body>
        </Card>
    )

};
export default CourierCard;