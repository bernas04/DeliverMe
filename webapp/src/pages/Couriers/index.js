import React from "react";
import { Container } from "react-bootstrap";
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


            {
                testItems.map(item => <CourierCard key={item.id} courierDetails={item} />)
            }

        </Container>
    );
};
export default Couriers;