import React, { useEffect, useState } from "react";
import { Container, Form, Button } from "react-bootstrap";
import CourierCard from './Components/CourierCard'

const Couriers = () => {

    const [fetchError, setFetchError] = useState(false);
    const [riders, setRiders] = useState([]);

    useEffect(()=>{
        fetch(
            `${process.env.REACT_APP_API_URL}/riders/Riders`,
            {
                headers: {'Access-Control-Allow-Origin': '*'}
            }
        )
        .then(response => response.json())
        .then(data => {
            setRiders(data)
        })
        .catch((reason) => {
            console.log(reason)
            setFetchError(true)
        })
    }, [])


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
                fetchError ? 
                <h3>An error ocorred while fetching data</h3>
                :
                riders.map(item => <CourierCard key={item.id} courierDetails={item} />)
            }

        </Container>
    );
};
export default Couriers;