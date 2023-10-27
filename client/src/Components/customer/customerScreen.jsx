import {useEffect, useState} from "react";
import axios from "axios";
import {Container, Row, Col, Form, NavbarBrand, Navbar, Button, Alert} from "react-bootstrap";

const CustomerScreen = () => {

    const [services, setServices] = useState([]);
    const [sservice, setSservice] = useState("");
    const [nticket, setNticket] = useState('');
    const [message, setMessage] = useState(false);

    useEffect(() => {
        axios.get('http://localhost:8080/API/counterServiceTypes/distinctServiceTypes')
            .then((response) => {
                setServices(response.data);
            })
            .catch((error) => {
                console.error('Errore nella richiesta API:', error);
            });
    }, []);
    const newticket = (event) => {
        event.preventDefault();
        if (sservice !== "") {
            axios.post('http://localhost:8080/API/ticket/servicetype/'+sservice.id)
                .then((response) => {
                    setNticket(response.data);
                })
                .catch((error) => {
                    console.error('Error of post:', error);
                });
            setMessage(false);
        }
        else {
            setMessage(true);
            return;
        }
    };
    const handleServiceChange = (event) => {
        setNticket('')
        const selectedId = Number(event.target.value);
        const selectedService = services.find(service => service.id === selectedId);
        setSservice(selectedService);
    };
    return (
        <>
            <Container fluid style={{transform: 'scale(2)', marginTop:'100px'}}>
                <Row className={"justify-content-center"} id={"mainform_customer"} style={{marginTop: '1em'}}>
                    <Col xs={12} md={6}>
                        <Form onSubmit={newticket} className="text-center">
                            <Form.Label style={{width:"200px"}}>Please select a service:</Form.Label>
                            <Form.Select style={{marginBottom: '0.5em', width:"200px"}} defaultValue={""} onChange={handleServiceChange}>
                                <option disabled={true} value="">Select service</option>
                                {services.map((service) => (
                                    <option key={service.id} value={service.id}>{service.tag}</option>
                                ))}
                            </Form.Select>
                            {message &&
                                <Row className={"justify-content-center"}>
                                    <Alert style={{marginLeft: '0.80em', marginTop: '0.5em'}} variant={"danger"} show={true} onClose={() => setMessage(false)} dismissible>Please select a service</Alert>
                                </Row>
                            }
                            <Button type="submit" style={{width:"200px"}}>
                                New Ticket
                            </Button>
                        </Form>
                    </Col>
                </Row>
                <Row className={"justify-content-center"} style={{marginTop: '0.5em'}} id={"newticket"}>
                    {nticket === "" ?
                        null
                        :
                        <>
                            <Col xs={12} md={6} style={{width:"200px", marginLeft:"75px", border: 'solid 1px black', textAlign: 'center'}}>
                                <Form.Label>Ticket id: {sservice.code+nticket.number.toString()} </Form.Label>
                                <br/>
                                <Form.Label>Waiting time: {nticket.waitingTime/60} min</Form.Label>
                            </Col>
                        </>
                    }
                </Row>
            </Container>
        </>
    )
}

export default CustomerScreen;