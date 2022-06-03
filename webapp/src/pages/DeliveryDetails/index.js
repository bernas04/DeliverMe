import React, { useEffect, useState } from "react";
import { Container } from "react-bootstrap";
import { Link, useParams } from "react-router-dom";

const DeliveryDetails = () => {

    // get url path variables
    let params = useParams();

    // state variables
    //const [deliveryId, setDeliveryId] = useState(0);
    const [item, setItem] = useState({});

    // fetch delivery details on load
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
            <Link className="btn btn-primary" to="..">Back</Link>
            <h2 className="mt-3">Delivery #{params.deliveryId}: {item.name}</h2>
            <h3 className="mb-3">Status: <span className="h3 badge bg-secondary">{item.status}</span></h3>

            <dl className="fs-4">
                <dt>Information:</dt>
                <dd>Value</dd>

                <dt>Other bit of info:</dt>
                <dd>Another value</dd>

                <dt>Courier:</dt>
                <dd>{item.courier}</dd>
            </dl>
        </Container>
    )

}
export default DeliveryDetails;