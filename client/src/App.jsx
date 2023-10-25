import './App.css';
import TicketList from "./Components/ShowTickets";
import React from "react";
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import MainBoard from './Components/mainBoard/ShowMainBoard';
import CounterScreen from './Components/officer/counterScreen'
import CustomerScreen from './Components/customer/customerScreen'

function App() {
  return (
      //<TicketList/>
      <>
        <BrowserRouter>
          <Routes>
            <Route path='/mainBoard' element={<MainBoard/>}/>
            <Route path='/counter/:id' element={<CounterScreen/>}/>
            <Route path='/customer' element={<CustomerScreen/>}/>
          </Routes>
        </BrowserRouter>
      </>
  );
}

export default App;
