import React from "react";
import { Container } from "react-bootstrap";
import DeliveryCard from './Components/DeliveryCard'

const Deliveries = () => {

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
            "status": "delivered",
            "courier": 200,
            "business": 10,
        },
    ]


    return(
        <Container>
            <h2 className="my-2">Deliveries</h2>




            {
                testItems.map(item => <DeliveryCard key={item.id} deliveryDetails={item} />)
            }

        </Container>
    );
};
export default Deliveries;