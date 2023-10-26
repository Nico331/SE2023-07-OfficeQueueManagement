import './App.css';
import TicketList from "./Components/ShowTickets";
import React, {useEffect, useState} from "react";
import { BrowserRouter, Route, Routes, Navigate } from 'react-router-dom';
import MainBoard from './Components/mainBoard/ShowMainBoard';
import CounterScreen from './Components/officer/counterScreen'
import CustomerScreen from './Components/customer/customerScreen'
import './Components/customer/customerScreen.css'
import axios from "axios";
import CounterManagement from "./Components/admin/AddCounterScreen";
import {QueryClient, QueryClientProvider} from 'react-query';
import ServiceTypeManagement from "./Components/admin/AddServiceTypeScreen";

const queryClient = new QueryClient();

function App() {

    return (
        // <TicketList/>
        <>
            <BrowserRouter>
                <QueryClientProvider client={queryClient}>
                <Routes>
                    <Route path='/' element={<Navigate replace to='/mainBoard'/>}/>
                    <Route path='/mainBoard' element={<MainBoard/>}/>
                    <Route path='/counter/:id' element={<CounterScreen/>}/>
                    <Route path='/customer' element={<CustomerScreen/>}/>
                    <Route path='/admin/counterManagement' element={<CounterManagement/>}/>
                    <Route path='/admin/serviceTypeManagement' element={<ServiceTypeManagement/>}/>
                </Routes>
                </QueryClientProvider>
            </BrowserRouter>
        </>
    );
}

export default App;
