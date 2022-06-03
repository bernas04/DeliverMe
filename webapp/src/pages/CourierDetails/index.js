import React, { useEffect, useState } from "react";
import { Container, ListGroup } from "react-bootstrap";
import { Link, useParams } from "react-router-dom";

const CourierDetails = () => {

    // get url path variables
    let params = useParams();

    // state variables
    //const [deliveryId, setDeliveryId] = useState(0);
    const [item, setItem] = useState({});

    // fetch courier details on load
    useEffect(()=>{
        setItem({
            "id": 100,
            "name": "delivery1",
            "status": "requested",
            "courier": 200,
            "business": 10,
        })
    }, [])

    return(
        <Container>
            <Link className="btn btn-primary" to="/couriers">Back</Link>
            <h2 className="mt-3">Courier #{params.courierId}: {item.name}</h2>

            <dl className="fs-4">
                <dt>Information:</dt>
                <dd>Value</dd>

                <dt>Other bit of info:</dt>
                <dd>Another value</dd>
            </dl>

            <h3>Delivering:</h3>
            <ListGroup>
                <ListGroup.Item>a delivery</ListGroup.Item>
                <ListGroup.Item>another delivery</ListGroup.Item>
            </ListGroup>
        </Container>
    )

}
export default CourierDetails;