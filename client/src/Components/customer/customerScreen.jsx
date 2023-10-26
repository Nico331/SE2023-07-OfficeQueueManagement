import {useEffect, useState} from "react";
import axios from "axios";
import {Container, Row, Col, Form, NavbarBrand, Navbar, Button, Alert} from "react-bootstrap";

const CustomerScreen = () => {

    const [services, setServices] = useState([]);
    const [sservice, setSservice] = useState("");
    const [nticket, setNticket] = useState('');
    const [message, setMessage] = useState(false);

    useEffect(() => {
        axios.get('http://localhost:8080/API/getservicetypes')
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
            axios.post('http://localhost:8080/API/ticket/servicetype/'+sservice)
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

    return (
        <>
            <Container fluid>
                <Row className={"justify-content-md-center"} id={"mainform_customer"} style={{marginTop: '1em'}}>
                    <Col>
                        <Form onSubmit={newticket}>
                            <Form.Label>Please select a service:</Form.Label>
                            <Form.Select style={{marginBottom: '0.5em'}} defaultValue={""} onChange={text => setSservice(text.target.value)}>
                                <option disabled={true} value="">Select service</option>
                                {services.map((index) => (
                                    <option key={index.id} value={index.id}>{index.tag}</option>
                                    )
                                )}
                            </Form.Select>
                            {message && <Row> <Alert style={{marginLeft: '0.80em', marginTop: '0.5em'}} variant={"danger"} show={true} onClose={() => setMessage(false)} dismissible>Please select a service</Alert> </Row>}
                            <Button style={{}} type="submit">
                                New Ticket
                            </Button>
                        </Form>
                    </Col>
                </Row>
                <Row style={{marginTop: '0.5em'}} id={"newticket"}>
                    {nticket === "" ?
                        null
                        :
                        <>
                            <Col style={{border: 'solid 1px black'}}>
                                <Form.Label>Ticket id: {sservice.id + sservice.code} </Form.Label>
                                <br/>
                                {/*<Form.Label>Type of Service: {services.find((index) => {

                                })}</Form.Label>*/}
                                
                                <Form.Label>Waiting time: {nticket.waitingTime}</Form.Label>
                            </Col>
                        </>
                    }
                </Row>
            </Container>
        </>
    )
}

export default CustomerScreen;