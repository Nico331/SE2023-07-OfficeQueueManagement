import {useParams} from "react-router-dom";
import {Button} from "react-bootstrap";
import axios from "axios";
import {useState} from "react";

const CounterScreen = (props) => {
    const [buttonText, setButtonText] = useState("Client served");
    const refresh = props.refresh;
    const {counterID} = useParams();
    console.log(counterID);
    // const counters = props.counters;
    console.log(props.counters);
    const counter = props.counters.find(count => count.counterID === counterID);
    const [ticketID, setTicketID] = useState(counter.nextCustomer);
    //const ticketId = counter.nextCustomer;

    const serveClient = () => {
        axios.put(`http://localhost:8080/API/counter/${counterID}/stop`)
            .then(response => {
                //console.log("PUT okay for Client Served");
                props.setRefresh(true);
                setButtonText("Next Customer");
                setTicketID(response.data.ticketID);
            })
            .catch(error => {
                console.log((error));
            });
    };

    const nextCustomer = () => {
        axios.put(`http://localhost:8080/API/counter/${counterID}/next`)
            .then(response => {
                //console.log("PUT okay for Next Customer");
            })
            .catch(error => {
                console.log(error);
            });
    };
    return (
        <>
            <div>
                {
                    {ticketID}
                }
            </div>
            <div>

                <Button className='my-2' type='button' onClick={serveClient}>
                    {buttonText}
                </Button>
                {buttonText === "Next Customer" && (
                    <Button className='my-2' type='button' onClick={nextCustomer}>
                        Next Customer
                    </Button>
                )}

            </div>
        </>
    )

}

export default CounterScreen;