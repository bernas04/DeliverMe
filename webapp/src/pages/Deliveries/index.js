import React, { useEffect, useState } from "react";
import { Container, Form } from "react-bootstrap";
import CheckboxGroup from "../../components/CheckboxGroup";
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


    const possibleStates = ["requested", "in progress", "delivered", "canceled"];
    const [selectedStates, setSelectedStates] = useState(possibleStates.map(s=>s));

    useEffect(()=>{
        console.log(selectedStates);
    }, [selectedStates])


    return(
        <Container>
            <h2 className="my-2">Deliveries</h2>

            <br />
            <Form.Group>
                <Form.Control type="text" placeholder="Search by delivery name or ID" />
            </Form.Group>
            <Form.Group>
                <Form.Control type="text" placeholder="Search by courier name or ID" />
            </Form.Group>

            <CheckboxGroup values={possibleStates} selectedValues={selectedStates} setSelectedValues={setSelectedStates} />

            {
                testItems.map(item => <DeliveryCard key={item.id} deliveryDetails={item} />)
            }

        </Container>
    );
};
export default Deliveries;