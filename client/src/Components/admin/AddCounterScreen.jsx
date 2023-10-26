import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useMutation, useQuery } from 'react-query';
import { Container, Row, Col, ListGroup, Button, FormControl, InputGroup } from 'react-bootstrap';
import AddServiceToCounter from "./AddServiceToCounter";

const CounterManagement = () => {
    const [counters, setCounters] = useState([]);
    const [newCounterNumber, setNewCounterNumber] = useState(0);
    const [isModalVisible, setIsModalVisible] = useState(false);
    const [selectedCounter, setSelectedCounter] = useState(null);
    const handleCounterClick = (counter) => {
        setSelectedCounter(counter);
        setIsModalVisible(true);
    }
    // Fetch all counters
    const { data, refetch } = useQuery("counters", async () => {
        const response = await axios.get("http://localhost:8080/API/getcounters");
        return response.data;
    });

    useEffect(() => {
        if (data) {
            setCounters(data.sort((a,b)=> a.number - b.number));
        }
    }, [data]);

    const addCounterMutation = useMutation(async (number) => {
        await axios.post("http://localhost:8080/API/addcounter", { number });
    }, {
        onSuccess: () => {
            refetch();
        }
    });

    const removeCounterMutation = useMutation(async (number) => {
        await axios.delete(`http://localhost:8080/API/removecounter/${number}`);
    }, {
        onSuccess: () => {
            refetch();
        }
    });

    const handleAddCounter = () => {
        addCounterMutation.mutate(newCounterNumber);
    };

    return (
        <Container style={{
            height: '100%',
            marginTop:'10px'
        }}>
            <Row className="justify-content-center">
                <Col >
                    <h1 className="text-center" style={{fontSize:'60px'}}>Counter Management</h1>

                    {counters.map(counter => (
                        <ListGroup.Item
                            key={counter.id}
                            style={{
                                fontSize: '30px',
                                marginLeft: '110px',
                                marginRight: '110px',
                                paddingRight: '50px',
                                borderBottom: '2px solid black'
                            }}>
                            <span onClick={() => handleCounterClick(counter)} >
                                Counter #{counter.number}
                            </span>

                            <span style={{
                                width: '220px',
                                display: "inline-block",
                                "text-align": "center",
                            }}>
                                {counter.working
                                    ? <Button variant="danger" onClick={() => removeCounterMutation.mutate(counter.number)}>Deactivate</Button>
                                    : <Button variant="success" onClick={() => addCounterMutation.mutate(counter.number)}>Activate</Button>}
                            </span>
                            <div style={{height: "10px"}}></div>
                        </ListGroup.Item>
                    ))}

                    <div className="mt-4">
                        <InputGroup>
                            <FormControl style={{marginLeft:'110px'}}
                                type="number"
                                placeholder="New Counter Number"
                                value={newCounterNumber}
                                onChange={e => setNewCounterNumber(e.target.value)}
                            />
                            <Button style={{marginRight:'110px'}} variant="primary" onClick={handleAddCounter}>+</Button>
                        </InputGroup>
                    </div>
                </Col>
            </Row>
            {
                isModalVisible &&
                <AddServiceToCounter
                    counter={selectedCounter}
                    onClose={() => setIsModalVisible(false)}
                />
            }
        </Container>

    );
};

export default CounterManagement;
