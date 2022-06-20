import React from "react";
import { Link } from "react-router-dom";
import { Card, Row, Col } from 'react-bootstrap';
import { FiEye } from 'react-icons/fi';

const DeliveryCard = ({deliveryDetails, link}) => {
    let statusBadgeClass = "";
    switch (deliveryDetails.status.toLowerCase()) {
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
    console.log(deliveryDetails);
    return(
        <Card className="my-3">
            <Card.Body className="row">
                <Col xs="10">
                    <Card.Title>
                        #{deliveryDetails.id} - From <strong>{deliveryDetails.store.address.road}</strong> to <strong>{deliveryDetails.address.road}</strong>
                    </Card.Title>
                    <div className="card-text">
                        <Row>
                            <Col xs="3">
                                Status: <span className={"badge " + statusBadgeClass}>{deliveryDetails.status}</span>
                            </Col>
                            <Col xs="3">
                                Courier: {deliveryDetails.rider==null ? "None" : deliveryDetails.rider.name}
                            </Col>
                            <Col xs="3">
                                Business: <strong>{deliveryDetails.store.name}</strong>
                            </Col>
                            <Col xs="3">
                                Client: <strong>{deliveryDetails.client}</strong>
                            </Col>
                        </Row>
                    </div>
                </Col>
                <Col xs="2" className="d-flex flex-column justify-content-center">
                    <Link className="view-item-btn ms-auto" to={link + deliveryDetails.id}><FiEye size="2rem" /></Link>
                </Col>
            </Card.Body>
            
        </Card>
    )

};


const DeliveryList = ({items, link}) => {
    return(
        <div>
            {items.map(item =>  <DeliveryCard key={item.id} deliveryDetails={item} link={link} />)}
        </div>
    )

};
export default DeliveryList;