import React, { use, useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Home from './pages/Home';
import DenunciarGolpe from './pages/DenunciarGolpe';
import VerificarLink from './pages/VerificarLink';
import axios from 'axios';

function App() {
  const [message, setMessage] = React.useState('');

  useEffect(() => {
    axios.get('http://localhost:3000/api/message')
      .then(response => {
        setMessage(response.data);
      })
      .catch(error => {
        console.error('Error fetching message:', error);
      });
  }, []);

  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/denunciar" element={<DenunciarGolpe />} />
        <Route path="/verificar" element={<VerificarLink />} />
      </Routes>
    </Router>
  );
}

export default App;
