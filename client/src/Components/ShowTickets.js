import React, { useState, useEffect } from 'react';
import axios from 'axios';

/**
 * Solo di esempio, da togliere!
 * @returns {JSX.Element}
 * @constructor
 */
const TicketList = () => {
    const [tickets, setTickets] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8080/API/gettickets')
            .then((response) => {
                setTickets(response.data);
            })
            .catch((error) => {
                console.error('Errore nella richiesta API:', error);
            });
    }, []);

    return (
        <div className="container">
            <h1>Ticket List</h1>
            <ul className="list-group">
                {tickets.map((ticket) => (
                    <li key={ticket.id} className="list-group-item">
                        <div className="d-flex justify-content-between">
                            <div>
                                <h5 className="mb-1">{ticket.title}</h5>
                                <p className="mb-1">{ticket.description}</p>
                                <small>{ticket.created_at}</small>
                            </div>
                            <span className="badge badge-primary badge-pill">{ticket.status}</span>
                        </div>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default TicketList;
