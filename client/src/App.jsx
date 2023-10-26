import './App.css';
import TicketList from "./Components/ShowTickets";
import React, {useEffect, useState} from "react";
import { BrowserRouter, Route, Routes, Navigate } from 'react-router-dom';
import MainBoard from './Components/mainBoard/ShowMainBoard';
import CounterScreen from './Components/officer/counterScreen'
import CustomerScreen from './Components/customer/customerScreen'
import './Components/customer/customerScreen.css'
import axios from "axios";

function App() {
    const [counters, setCounters] = useState([]);
    const [firstLoad, setFirstLoad] = useState(false);
    const [refresh, setRefresh] = useState(false);
    let list = [];

    useEffect(()=>{
        if(!firstLoad){
            setFirstLoad(!firstLoad);
        }
        else{
            axios.get("http://localhost:8080/API/getcounters")
                .then(async result => {
                    for (const element of result.data) {
                        await axios.get(`http://localhost:8080/API/ticket/counter/${element.id}`)
                            .then(result => {
                                list.push({counterID: element.id, nextCustomer: result.data.id, service: result.data.serviceTypeId})
                                console.log(result.data);
                            })
                            .catch(err => {
                                console.log(err);
                            });
                    }
                })
                .then(() => {
                    setCounters(list);
                    console.log("prova app");
                    console.log("app"+counters);
                    list = [];
                })
                .catch(err => {
                    console.log('error in catch', err);
                });
        }
    }, [refresh, firstLoad]);

    return (
        // <TicketList/>
        <>
            <BrowserRouter>
                <Routes>
                    <Route path='/' element={<Navigate replace to='/mainBoard'/>}/>
                    <Route path='/mainBoard' element={<MainBoard counters={counters}/>}/>
                    <Route path='/counter/:id' element={<CounterScreen counters={counters} refresh={refresh} setRefresh={setRefresh}/>}/>
                    <Route path='/customer' element={<CustomerScreen/>}/>
                </Routes>
            </BrowserRouter>
        </>
    );
}

export default App;
