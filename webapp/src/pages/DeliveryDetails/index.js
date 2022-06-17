import React, { useEffect, useState } from "react";
import { Col, Container, Row } from "react-bootstrap";
import { Link, useParams } from "react-router-dom";

const DeliveryDetails = () => {

    const getStatusClass = (status) => {
        switch (status.toLowerCase()) {
            case "delivered":
                return "bg-success";
            case "requested":
                return "bg-secondary";
            case "in progress":
                return "bg-primary";
            case "canceled":
                return "bg-danger";
            default:
                return "bg-secondary";
        }
    }

    const parseDate = (dateString) => {
        const date = new Date(Date.parse(dateString))
        return `${date.toTimeString().slice(0, 8)} on ${date.toDateString()}`
    }
    

    // get url path variables
    let params = useParams();

    // state variables
    //const [deliveryId, setDeliveryId] = useState(0);
    const [fetchError, setFetchError] = useState(false);
    const [item, setItem] = useState(null);

    // fetch delivery details on load
    useEffect(()=>{
        fetch(
            `${process.env.REACT_APP_API_URL}/purchases/Purchase?id=${params.deliveryId}`,
            {
                headers: {'Access-Control-Allow-Origin': '*'}
            }
        )
        .then(response => response.json())
        .then(data => {
            setItem(data)
        })
        .catch((reason) => {
            console.log(reason)
            setFetchError(true)
        })
    }, [params.deliveryId])

    return(
        <Container>
            <Link className="btn btn-primary" to="..">Back</Link>

            {
                item == null ?
                <h3>Loading...</h3>
                :
                fetchError ?
                <h3>An error ocorred while fetching data</h3>
                :
                <>
                    <h2 className="mt-3">Delivery #{item.id}: From <strong>{item.store.address.road}</strong> to <strong>{item.address.road}</strong></h2>
                    <h3 className="mb-3">Status: <span className={`h3 badge ${getStatusClass(item.status)}`}>{item.status}</span></h3>

                    <Row className="mb-4">
                        <Col md="6">
                            <h3>Origin</h3>
                            <p className="fs-4 mb-0">{item.store.address.road}, {item.store.address.city}</p>
                            <p className="fs-4 mb-0">Zipcode: {item.store.address.zipcode}</p>
                        </Col>
                        <Col md="6">
                            <h3>Destination</h3>
                            <p className="fs-4 mb-0">{item.address.road}, {item.address.city}</p>
                            <p className="fs-4 mb-0">Zipcode: {item.address.zipcode}</p>
                        </Col>
                    </Row>

                    <dl className="fs-4">
                        <dt>Requested on:</dt>
                        <dd>{parseDate(item.date)}</dd>

                        <dt>Business:</dt>
                        <dd>{item.store.name}</dd>

                        <dt>Client:</dt>
                        <dd>{item.client}</dd>
                    </dl>
                </>
            }
        </Container>
    )

}
export default DeliveryDetails;