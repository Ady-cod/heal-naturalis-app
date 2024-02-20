import {Routes,Route} from "react-router-dom";

import Navbar from "./layouts/Navbar/Navbar";
import HomePage from "./layouts/HomePage/HomePage";
import Therapy from "./layouts/Therapy/Therapy";
import TestServerError from "./layouts/TestServerError/TestServerError";

import "./App.css";

function App() {
    return (
        <>
            <Navbar/>
            <Routes>
                <Route path="/" element={<HomePage/>}/>
                <Route path="/therapy/:page" element={<Therapy/>}/>
                <Route path="/testServerError" element={<TestServerError/>}/>
            </Routes>
        </>
    );
}

export default App;
