import {Routes,Route} from "react-router-dom";
import "./App.css";

import Navbar from "./layouts/NavbarAndFooter/Navbar";
import HomePage from "./layouts/HomePage/HomePage";
import Therapy from "./layouts/Therapy/Therapy";

function App() {
    return (
        <>
            <Navbar/>
            <Routes>
                <Route path="/" element={<HomePage/>}/>
                <Route path="/therapy/:page" element={<Therapy/>}/>
            </Routes>
        </>
    );
}

export default App;
