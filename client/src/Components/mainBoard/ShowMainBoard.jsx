import '../../App.css'
import React, { useEffect, useState } from 'react';
import axios from 'axios';

const MainBoard = (props) => {
    const [tickets, setTickets] = useState([]);
    const [services, setServices] = useState([]);
    const test = [];

    const fetchTickets = () => {
        axios.get('http://localhost:8080/API/gettickets')
            .then((response) => {
                setTickets(response.data);
            })
            .catch((error) => {
                console.error(error);
            });
    };

    useEffect(() => {
        // Imposta il polling per fare una richiesta ogni secondo
        const pollingInterval = 1000; // 1000 ms = 1 secondo
        const pollingTimer = setInterval(fetchTickets, pollingInterval);

        // Chiudi il timer quando il componente viene smontato
        return () => {
            clearInterval(pollingTimer);
        };

        // Inizialmente, esegui la prima richiesta
        fetchTickets();
    }, []); // Assicurati di passare un array vuoto come secondo argomento per eseguire useEffect solo una volta all'avvio del componente.

    useEffect(() => {
        axios.get('http://localhost:8080/API/getservicetypes')
            .then((response) => {
                setServices(response.data);
            })
            .catch((error) => {
                console.error(error);
            });
    }, []);

    const showTickets = ((tickets) => {
        if(tickets.length === 0) {
            return (<></>)
        }
        else {
            const newTicket = tickets[0];
            // const newTicketService = services.find((service) => service.id === newTicket.serviceType );
            // console.log(newTicketService);
            tickets.shift();
            return(
                <>
                <div className="main-box">
                    <p className="lastTicket" style={{fontSize:"80px", fontWeight:"bold"}}>NOW SERVING:</p>
                    <p className="lastTicket" style={{fontSize:"400px", fontWeight:"bold"}}>{newTicket.number}</p>
                    <p className="lastTicket" style={{fontSize:"80px", fontWeight:"bold"}}>AT COUNTER {newTicket.counterId}</p>
                </div>
                <div className="sidebar">
                    <p style={{fontSize:"20px", fontWeight:"bold"}}>LAST FIVE TICKET CALLED:</p>
                    {tickets.map(ticket => {
                        // let S = services.find((service) => service.id === ticket.serviceType );
                        return(
                            <p style={{fontSize:"70px", fontWeight:"bold"}}>{ticket.number}</p>
                        )
                    })}
                </div>
                </>
            )}
    })

    return (
        <body>
        <div className="container">
            {showTickets(tickets)}
        </div>
        </body>
    );
};

export default MainBoard;
