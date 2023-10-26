import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Modal, Button, Spinner, Dropdown } from 'react-bootstrap';

const AddServiceToCounter = ({ counter, onClose }) => {
    const [serviceTypes, setServiceTypes] = useState([{}]);
    const [selectedServiceType, setSelectedServiceType] = useState(null);
    const [loading, setLoading] = useState(true);
    const [serviceTypesOfCounter, setServiceTypesOfCounter] = useState([{}])
    useEffect(() => {
        const fetchServiceTypes = async () => {
            try {
                const response = await axios.get("http://localhost:8080/API/getservicetypes");
                setServiceTypes(response.data);
            } catch (error) {
                console.error("Error fetching service types:", error);
            }
        };

        fetchServiceTypes();
    }, []);
    useEffect(() => {
        const fetchServiceTypes = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/API/getservices/${counter.id}`);
                setServiceTypesOfCounter(response.data);
            } catch (error) {
                console.error("Error fetching service types:", error);
            }
        };

        fetchServiceTypes();
    }, []);
    useEffect(() => {
        setTimeout(() => {
            setLoading(false);
        }, 250); // 1000 ms = 1 secondo
    }, [serviceTypes.length]);
    useEffect(()=>{
        if(serviceTypes.length>0 && serviceTypesOfCounter.length>0){
            setServiceTypes(serviceTypes.filter(st => !serviceTypesOfCounter.some(soc => soc.id === st.id)))
            console.log(serviceTypes)
        }
    },[serviceTypes.length, serviceTypesOfCounter.length])
    const handleSubmit = async () => {
        try {
            await axios.post("http://localhost:8080/API/counterServiceTypes/addCounterServiceType", {
                counterId: counter.id,
                serviceTypeId: selectedServiceType.id
            });
            onClose();
        } catch (error) {
            console.error("Error associating service type to counter:", error);
        }
    };

    return (
        <Modal show={true} onHide={onClose}>
            <Modal.Header closeButton>
                <Modal.Title>Set Service Type for Counter #{counter.number}</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                {serviceTypesOfCounter && serviceTypesOfCounter.length > 0 && (
                    <div>
                        <h5>Current Service Types:</h5>
                        <ul>
                            {serviceTypesOfCounter.map(st => (
                                <li key={st.id}>{st.tag}</li>
                            ))}
                        </ul>
                        <hr />
                    </div>
                )}
                {(loading || serviceTypes.length===0) ?
                    <Spinner animation="border" />
                :
                    <Dropdown onSelect={(id) => setSelectedServiceType(serviceTypes.find(st => st.id.toString() === id))}>
                        <Dropdown.Toggle variant="success" id="dropdown-basic">
                            {selectedServiceType ? selectedServiceType.tag : "Select Service Type"}
                        </Dropdown.Toggle>
                        <Dropdown.Menu>
                            {serviceTypes.map(st => (
                                <Dropdown.Item key={st.id} eventKey={st.id}>{st.tag}</Dropdown.Item>
                            ))}
                        </Dropdown.Menu>
                    </Dropdown>
                }
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={onClose}>Close</Button>
                <Button variant="primary" onClick={handleSubmit} disabled={!selectedServiceType}>Set Service Type</Button>
            </Modal.Footer>
        </Modal>
    );
};

export default AddServiceToCounter;