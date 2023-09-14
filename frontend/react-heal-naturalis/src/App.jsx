import {Routes,Route} from "react-router-dom";

import Navbar from "./layouts/Navbar/Navbar";
import HomePage from "./layouts/HomePage/HomePage";
import Therapy from "./layouts/Therapy/Therapy";
import Loading from "./layouts/Loading/Loading";

import "./App.css";

function App() {
    return (
        <>
            <Navbar/>
            <Routes>
                {/*<Route path="/" element={<Loading/>}/>*/}
                <Route path="/" element={<HomePage/>}/>
                <Route path="/therapy/:page" element={<Therapy/>}/>
            </Routes>
        </>
    );
}

export default App;
