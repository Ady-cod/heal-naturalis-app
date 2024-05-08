import {Routes,Route} from "react-router-dom";

import Navbar from "./layouts/Navbar/Navbar";
import HomePage from "./layouts/HomePage/HomePage";
import Therapy from "./layouts/Therapy/Therapy";
import TestServerError from "./layouts/TestServerError/TestServerError";
import SubCategories from "./layouts/SubCategories/SubCategories";
import CategoryProducts from "./layouts/CategoryProducts/CategoryProducts";

import "./App.css";

function App() {
    return (
        <>
            <Navbar/>
            <Routes>
                <Route path="/" element={<HomePage/>}/>
                <Route path="/therapy/:therapyId" element={<Therapy/>}/>
                <Route path="/testServerError" element={<TestServerError/>}/>
                <Route path="/sub-categories/:parentCategoryId" element={<SubCategories/>}/>
                <Route path="/category-products/:categoryId" element={<CategoryProducts/>}/>
            </Routes>
        </>
    );
}

export default App;
