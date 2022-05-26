import React from "react";
import { Link } from "react-router-dom";
import { Card, Row, Col } from 'react-bootstrap';

const DeliveryCard = ({deliveryDetails}) => {

    let statusBadgeClass = "";
    switch (deliveryDetails.status) {
        case "delivered":
            statusBadgeClass = "bg-success";
            break;
        case "requested":
            statusBadgeClass = "bg-secondary";
            break;
        case "in progress":
            statusBadgeClass = "bg-primary";
            break;
        case "canceled":
            statusBadgeClass = "bg-danger";
            break;
        default:
            statusBadgeClass = "bg-secondary";
    }

    return(
        <Card className="my-3">
            <Card.Body className="row">
                <Col xs="10">
                    <Card.Title>{deliveryDetails.name}</Card.Title>
                    <Card.Text>
                        <Row>
                            <Col xs="3">
                                Status: <span className={"badge " + statusBadgeClass}>{deliveryDetails.status}</span>
                            </Col>
                            <Col xs="3">
                                Courier: {deliveryDetails.courier ? deliveryDetails.courier : "None"}
                            </Col>
                            <Col xs="3">
                                Business: {deliveryDetails.business}
                            </Col>
                        </Row>
                    </Card.Text>
                </Col>
                <Col xs="2" className="d-flex flex-column justify-content-center">
                    <Link className="view-item-btn ms-auto" to={"/deliveries/" + deliveryDetails.id}>View</Link>
                </Col>
            </Card.Body>
            
        </Card>
    )

};
export default DeliveryCard;