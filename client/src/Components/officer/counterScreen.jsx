import {useParams} from "react-router-dom";
import {Button} from "react-bootstrap";
import axios from "axios";
import {useEffect, useState} from "react";
import './counterScreen.css'

const CounterScreen = (props) => {
    const [buttonText, setButtonText] = useState("");
    const [serviceType, setServiceType] = useState({});
    const [showServeButton, setShowServeButton] = useState(true);
    const [ticket, setTicket] = useState({});
    //console.log("counterID: ")
    const {id} = useParams();
    //console.log(id);

    //const ticketId = counter.nextCustomer;

    useEffect(() => {
        axios.get(`http://localhost:8080/API/ticket/counter/${id}`)
            .then(response => {
                if((!response.data) || (response.data.status === "served")){
                    setButtonText("Next Customer");
                    setShowServeButton(false);
                }else{
                    setButtonText("Client Served")
                    setTicket(response.data);
                    setShowServeButton(true);

                }

                axios.get(`http://localhost:8080/API/getservicetype/${response.data.id}`)
                    .then(response => {
                        setServiceType(response.data);
                    })

            })
            .catch(error => {
                console.log(error);
            })
    }, []);

    const serveClient = (event) => {
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
        event.preventDefault();
        axios.put(`http://localhost:8080/API/counter/${id}/next`)
            .then(response => {
                //console.log("PUT okay for Next Customer");
                setTicket(response.data)
                setButtonText("Client Served")
                setShowServeButton(true);

            })
            .catch(error => {
                console.log(error);
            });
    };
    return (
        <div className="container">
            <div className="square">
                {
                    "Serving now: " + "\n" + "ticked id: " + serviceType.code
                    + ticket.number + "\n" + "service type: " + serviceType.tag
                }
            </div>
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
     </div>
    )

}

export default CounterScreen;