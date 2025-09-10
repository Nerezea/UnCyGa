import React from 'react';
import ReactDOM from 'react-dom/client';
import './scss/main.scss';
import { BrowserRouter } from 'react-router-dom';
import App from './components/App';

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </React.StrictMode>
);
