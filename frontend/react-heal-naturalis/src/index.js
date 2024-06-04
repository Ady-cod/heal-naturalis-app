import React from 'react';

import ReactDOM from 'react-dom/client';

import App from './App';
import {CartProvider} from "./layouts/CartProvider";

import './index.css';
import '@fortawesome/fontawesome-free/css/all.min.css';


import {BrowserRouter as Router} from "react-router-dom";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <React.StrictMode>
        <Router>
            <CartProvider>
                <App/>
            </CartProvider>
        </Router>
    </React.StrictMode>
);


