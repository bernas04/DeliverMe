import React from "react";
import { Container, Form, Button } from "react-bootstrap";
import CourierCard from './Components/CourierCard'

const Couriers = () => {

    const testItems = [
        {
            "id": 100,
            "name": "Dee Liverman",
        },
        {
            "id": 101,
            "name": "Will Packer",
        },
        {
            "id": 102,
            "name": "Susan Rider",
        },
    ]


    return(
        <Container>
            <h2 className="my-2">Couriers</h2>

            <br />
            <Form.Group className="mb-2">
                <Form.Control type="text" placeholder="Search by courier name or ID" />
            </Form.Group>

            <div className="d-flex justify-content-end">
                <Button className="me-2">Reset</Button>
                <Button>Search</Button>
            </div>


            {
                testItems.map(item => <CourierCard key={item.id} courierDetails={item} />)
            }

        </Container>
    );
};
export default Couriers;