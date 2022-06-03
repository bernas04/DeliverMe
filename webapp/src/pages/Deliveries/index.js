import React, { useEffect, useState } from "react";
import { Button, Container, Form } from "react-bootstrap";
import CheckboxGroup from "../../components/CheckboxGroup";
import DeliveryList from "../../components/DeliveryList";

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
            <Form.Group className="mb-2">
                <Form.Control type="text" placeholder="Search by delivery name or ID" />
            </Form.Group>
            <Form.Group className="mb-2">
                <Form.Control type="text" placeholder="Search by courier name or ID" />
            </Form.Group>

            <CheckboxGroup values={possibleStates} selectedValues={selectedStates} setSelectedValues={setSelectedStates} />
            <div className="d-flex justify-content-end">
                <Button className="me-2">Reset</Button>
                <Button>Search</Button>
            </div>

            <DeliveryList items={testItems} link="/deliveries/" />

        </Container>
    );
};
export default Deliveries;