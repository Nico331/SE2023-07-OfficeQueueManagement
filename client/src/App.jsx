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

    return (
        // <TicketList/>
        <>
            <BrowserRouter>
                <Routes>
                    <Route path='/' element={<Navigate replace to='/mainBoard'/>}/>
                    <Route path='/mainBoard' element={<MainBoard/>}/>
                    <Route path='/counter/:id' element={<CounterScreen/>}/>
                    <Route path='/customer' element={<CustomerScreen/>}/>
                </Routes>
            </BrowserRouter>
        </>
    );
}

export default App;
