import {useParams} from "react-router-dom";
import {Button} from "react-bootstrap";
import axios from "axios";
import {useEffect, useState} from "react";
import './counterScreen.css'

const CounterScreen = (props) => {
    const [buttonText, setButtonText] = useState("Next Customer");
    const [serviceType, setServiceType] = useState({});
    const [showServeButton, setShowServeButton] = useState(false);
    const [ticket, setTicket] = useState({});
    //console.log("counterID: ")
    const {id} = useParams();
    console.log(showServeButton);

    //const ticketId = counter.nextCustomer;
    const getTicket = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/API/ticket/counter/${id}`);
            setTicket(response.data);
            if(response.data.status === "in progress"){
                setButtonText("Customer served")
                setShowServeButton(true);
            } else {
                setShowServeButton(false);
                setButtonText("Next Customer")
            }
            if (response.data.id) {
                const serviceTypeResponse = await axios.get(`http://localhost:8080/API/getservicetype/${response.data.serviceTypeId}`);
                setServiceType(serviceTypeResponse.data);
            }
        } catch (error) {
            console.log(error);
        }
    };
    const getServiceType = async () =>{
        try {
            const serviceTypeResponse = await axios.get(`http://localhost:8080/API/getservicetype/${ticket.serviceTypeId}`);
            setServiceType(serviceTypeResponse.data);
        } catch (error) {
            console.log(error);
        }
    }
    useEffect(() => {
        /*
        const pollingInterval = 1000;

        const pollingTimer = setInterval(() => {
            if (!ticket.id) {
                axios.put(`http://localhost:8080/API/counter/${id}/next`)
                    .then(response => {
                        if (response.data && response.data.id) {
                            clearInterval(pollingTimer);

                            setTicket(response.data);
                            setButtonText("Customer served");
                            setShowServeButton(true);
                            getServiceType();
                        }
                    })
                    .catch(error => {
                        console.log(error);
                    });
            } else {
                clearInterval(pollingTimer);
            }
        }, pollingInterval);


        return () => {
            clearInterval(pollingTimer);
        };
         */
        getTicket().then(r => {})
    }, []);

    const serveClient = (event) => {
        console.log("A")
        event.preventDefault();
        axios.put(`http://localhost:8080/API/counter/${id}/stop`)
            .then(response => {
                //console.log("PUT okay for Client Served");
                setButtonText("Next Customer");
                setShowServeButton(false);
            })
            .catch(error => {
                console.log((error));
            });
    };

    const nextCustomer = (event) => {
        console.log("b")
        event.preventDefault();
        axios.put(`http://localhost:8080/API/counter/${id}/next`)
            .then(response => {
                //console.log("PUT okay for Next Customer");
                getTicket().then(r => {})
                //setTicket(response.data)
                //setButtonText("Customer served")
                //setShowServeButton(true);
                getServiceType();
            })
            .catch(error => {
                console.log(error);
            });
    };
    return (
        <div className="container">
                <>
                    {ticket && <div className="square">
                        Serving now: <br/>
                        Ticket id: {serviceType.code + ticket.number} <br/>
                        Service type: {serviceType.tag}
                    </div>}
                    <div className="button-container">

                        {showServeButton && (
                            <Button className='my-2' type='button' onClick={serveClient}>
                                {buttonText}
                            </Button>
                        )}
                        {!showServeButton && (
                            <Button className='my-2' type='button' onClick={nextCustomer}>
                                {buttonText}
                            </Button>
                        )}

                    </div>
                </>

        </div>
    )

}

export default CounterScreen;