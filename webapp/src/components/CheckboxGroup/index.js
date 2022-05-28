import React from "react";
import { Form } from "react-bootstrap";

const CheckboxGroup = ({values, selectedValues, setSelectedValues}) => {

    const updateValue = (event, value) => {
        if (event.target.value === "on" && selectedValues.includes(value)) {
            let new_values = selectedValues.filter(val=>val !== value);
            setSelectedValues(new_values);
        }
        else if (event.target.value === "on" && !selectedValues.includes(value)) {
            setSelectedValues(selectedValues.concat(value));
        }
    }

    return(
        <Form.Group>
            {
                values.map(val =>
                <Form.Check
                    key={val}
                    className="d-inline-block me-4"
                    type="checkbox"
                    label={val}
                    onChange={(e)=>updateValue(e, val)}
                    checked={selectedValues.includes(val)}
                />)
            }
        </Form.Group>
    );
}
export default CheckboxGroup;