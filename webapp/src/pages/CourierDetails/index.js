import React, { useEffect, useState } from "react";
import { Container, ListGroup } from "react-bootstrap";
import { Link, useParams } from "react-router-dom";

const CourierDetails = () => {

    // get url path variables
    let params = useParams();

    // state variables
    const [fetchError, setFetchError] = useState(false);
    const [rider, setItem] = useState(null);
    const [hasPastDeliveries , setPurchase] = useState(false);

    // fetch delivery details on load
    useEffect(()=>{
        fetch(
            `${process.env.REACT_APP_API_URL}/riders/Rider?id=${params.courierId}`,
            {
                headers: {'Access-Control-Allow-Origin': '*'}
            }
        )
        .then(response => response.json())
        .then(data => {
            setItem(data)
            if (data.purchases.length!==0){
                setPurchase=true;
            }
        })
        .catch((reason) => {
            console.log(reason)
            setFetchError(true)
        })
    }, [params.courierId])


    
    


    return(
        <Container>
            <Link className="btn btn-primary" to="/couriers">Back</Link>
            
            {
                rider == null ?
                <h3>Loading...</h3>
                :
                fetchError ?
                <h3>An error ocorred while fetching data</h3>
                :
                <>
                <h2 className="mt-3">Courier #{params.courierId}: {/* rider.name */}</h2>

                <dl className="fs-4">
                    <dt>Information:</dt>
                    <dd>Total deliveries {rider.totalReviews}</dd>
                    <dd>Average reviews: {rider.averageReview}</dd>
    

                    <dt>Other bit of info:</dt>
                    <dd>Another value</dd>
                </dl>

                <h3>Delivering:</h3>
                <ListGroup>
                    <ListGroup.Item>a delivery</ListGroup.Item>
                </ListGroup>

                    {
                        hasPastDeliveries ?
                        <div>
                            <h3>Past Deliveries:</h3>
                            <ListGroup>
                                <ListGroup.Item>Past Deliveries</ListGroup.Item>
                            </ListGroup>
                        </div>
                        :
                        <h3>No Past Deliveries</h3>
                    }
                </>
            }
        </Container>
    )

}
export default CourierDetails;