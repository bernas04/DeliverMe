import React, { useEffect, useState } from "react";
import { Container } from "react-bootstrap";
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
                    <h2 className="mt-3">Delivery #{item.id}: From <strong>{item.store.address.road}</strong> to <strong>{item.client.address.road}</strong></h2>
                    <h3 className="mb-3">Status: <span className={`h3 badge ${getStatusClass(item.status)}`}>{item.status}</span></h3>

                    <dl className="fs-4">
                        <dt>Information:</dt>
                        <dd>Value</dd>

                        <dt>Other bit of info:</dt>
                        <dd>Another value</dd>

                        <dt>Courier:</dt>
                        <dd>{item.courier}</dd>
                    </dl>
                </>
            }
        </Container>
    )

}
export default DeliveryDetails;