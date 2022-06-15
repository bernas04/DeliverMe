import React, { useEffect, useState } from "react";
import { Button, Container, Form } from "react-bootstrap";
import CheckboxGroup from "../../components/CheckboxGroup";
import DeliveryList from "../../components/DeliveryList";

const Deliveries = () => {

    const [fetchError, setFetchError] = useState(false);
    const [deliveries, setDeliveries] = useState([]);

    // change to requested and in-progress only
    useEffect(()=>{
        fetch(
            `${process.env.REACT_APP_API_URL}/purchases/Purchases`,
            {
                headers: {'Access-Control-Allow-Origin': '*'}
            }
        )
        .then(response => response.json())
        .then(data => {
            setDeliveries(data)
        })
        .catch((reason) => {
            console.log(reason)
            setFetchError(true)
        })
    }, [])


    const possibleStates = ["requested", "in progress", "delivered", "canceled"];
    const [selectedStates, setSelectedStates] = useState(possibleStates.map(s=>s));

    useEffect(()=>{
        console.log(selectedStates);
    }, [selectedStates])

    console.log(deliveries)
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

            {
                fetchError ?
                <h3>An error ocorred while fetching data</h3>
                :
                <DeliveryList items={deliveries} link="/deliveries/" />
            }
        </Container>
    );
};
export default Deliveries;