import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useMutation, useQuery } from 'react-query';
import { Container, Row, Col, ListGroup, Button, FormControl, InputGroup } from 'react-bootstrap';

const ServiceTypeManagement = () => {
    const [serviceTypes, setServiceTypes] = useState([]);
    const [newServiceType, setNewServiceType] = useState({
        tag: '',
        code: '',
        serviceTime: 0,
        working: true
    });

    // Fetch all service types
    const { data, refetch } = useQuery("serviceTypes", async () => {
        const response = await axios.get("http://localhost:8080/API/getservicetypes");
        return response.data;
    });

    useEffect(() => {
        if (data) {
            setServiceTypes(data.sort((a,b)=>a.tag.localeCompare(b.tag)));
        }
    }, [data]);

    const addServiceTypeMutation = useMutation(async (serviceType) => {
        await axios.post("http://localhost:8080/API/addservicetype", serviceType);
    }, {
        onSuccess: () => {
            refetch();
        }
    });

    const removeServiceTypeMutation = useMutation(async (tag) => {
        await axios.delete(`http://localhost:8080/API/removeservicetype/${tag}`);
    }, {
        onSuccess: () => {
            refetch();
        }
    });

    const handleAddServiceType = () => {
        addServiceTypeMutation.mutate(newServiceType);
    };

    return (
        <Container style={{
            height: '100%',
            marginTop:'10px'
        }}>
            <Row className="justify-content-center">
                <Col >
                    <h1 className="text-center" style={{fontSize:'60px'}}>Service Type Management</h1>

                    {serviceTypes.map(service => (
                        <ListGroup.Item
                            key={service.id}
                            style={{
                                fontSize: '30px',
                                marginLeft: '210px',
                                marginRight: '110px',
                                paddingRight: '0px',
                                borderBottom: '2px solid black',
                                display: 'flex', // Aggiungi il display flex qui
                                justifyContent: 'space-between', // Allinea gli elementi ai lati opposti
                                alignItems: 'center' // Centra verticalmente gli elementi
                            }}>
                            <span>
                                {service.tag} - {service.code} ({service.serviceTime} mins)
                            </span>

                            <span style={{
                                width: '220px',
                                display: "flex",
                                justifyContent: "center",
                            }}>
                            {service.working
                                ? <Button variant="danger" onClick={() => removeServiceTypeMutation.mutate(service.tag)}>Deactivate</Button>
                                : <Button variant="success" onClick={() => addServiceTypeMutation.mutate(service)}>Activate</Button>}
                        </span>
                        </ListGroup.Item>

                    ))}

                    <div className="mt-4">
                        <InputGroup>
                            <FormControl
                                style={{marginLeft:'210px'}}
                                type="text"
                                placeholder="New Service Name"
                                value={newServiceType.tag}
                                onChange={e => setNewServiceType(prev => ({ ...prev, tag: e.target.value }))}
                            />
                            <FormControl
                                type="text"
                                placeholder="Code"
                                value={newServiceType.code}
                                onChange={e => setNewServiceType(prev => ({ ...prev, code: e.target.value }))}
                            />
                            <FormControl
                                type="number"
                                placeholder="Service Time (mins)"
                                value={newServiceType.serviceTime}
                                onChange={e => setNewServiceType(prev => ({ ...prev, serviceTime: parseInt(e.target.value) }))}
                            />
                            <Button style={{marginRight:'110px'}} variant="primary" onClick={handleAddServiceType}>Add Service Type</Button>
                        </InputGroup>
                    </div>
                </Col>
            </Row>
        </Container>
    );
};

export default ServiceTypeManagement;
