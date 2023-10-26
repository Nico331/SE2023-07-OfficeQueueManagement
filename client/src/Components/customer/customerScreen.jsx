import {useEffect, useState} from "react";
import axios from "axios";
import {Container, Row, Col, Form, NavbarBrand, Navbar, Button, Alert} from "react-bootstrap";

const CustomerScreen = () => {

    const [services, setServices] = useState([]);
    const [sservice, setSservice] = useState("");
    const [nticket, setNticket] = useState('');
    const [message, setMessage] = useState('');

    useEffect(() => {
        axios.get('http://localhost:8080/API/getservicetypes')
            .then((response) => {
                setServices(response.data);
            })
            .catch((error) => {
                console.error('Errore nella richiesta API:', error);
            });
    }, []);

    /*const newticket = (event) => {
        event.preventDefault();
        axios.post('http://localhost:8080/API/ticket/servicetype', {serviceType: sservice})
            .then((response) => {
               setNewticket(response);
            })
            .catch((error) => {
                console.error('Error of post:', error);
            });
        console.log(sservice);
        console.log(newticket);
    };*/

    const newticket = (event) => {
        // if (sservice !== "") {


            event.preventDefault();
            axios.post('http://localhost:8080/API/ticket/servicetype/'+sservice)
                .then((response) => {
                    console.log("New Ticket RESPONSE: " + response);
                    setNticket(response.data);
                })
                .catch((error) => {
                    console.error('Error of post:', error);
                });
/*        }
        else {
            setMessage("Please select a service");
            return;
        }*/
    };

    return (
        <>
            <Container >
                <Row>
                    <Col>
                        <Navbar>

                        </Navbar>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <Form onSubmit={newticket}>
                            <Form.Label>Please select a service:</Form.Label>
                            <Form.Select defaultValue={""} style={{width: '30%'}} onChange={text => setSservice(text.target.value)}>
                                <option disabled={true} value="">Select service</option>
                                {services.map((index) => (
                                    <option value={index.id}>{index.tag}</option>
                                    )
                                )}
                            </Form.Select>
                            {/*{message && <Row> <Alert variant={"danger"} show={true} dismissible>{message}</Alert> </Row>}*/}
                            <Button style={{margin: '1em'}} type="submit">
                                New Ticket
                            </Button>
                        </Form>
                        {nticket === "" ?
                            null
                        :
                            <>
                                <Form.Label>Ticket id:{nticket.id} </Form.Label>
                                <br/>
                                <Form.Label>Waiting time:{nticket.waiting_time}</Form.Label>
                            </>
                        }
                    </Col>
                </Row>
            </Container>
        </>
    )
}

export default CustomerScreen;