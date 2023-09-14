import {Link} from "react-router-dom";

import {useEffect, useState} from "react";

import {fetchAllTherapies} from "../../services/therapyService";

import Loading from "../Loading/Loading";

import "./Navbar.css";

const Navbar = () => {
    const [therapies, setTherapies] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchTherapies = async () => {
            try {
                const therapies = await fetchAllTherapies();
                setTherapies(therapies);
                setLoading(false);
            } catch (error) {
                console.log(error);
                setLoading(false);
            }
        }
        fetchTherapies();
    }, []);
    return (
        <nav className="navbar navbar-expand-lg navbar-dark main-color py-3">
            <div className="container-fluid">
                <span className="navbar-brand">Heal Naturalis</span>
                <button
                    className="navbar-toggler"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarNavdropdown"
                    aria-controls="navbarNavdropdown"
                    aria-expanded="false"
                    aria-label="Toggle Navigation"
                >
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarNavdropdown">
                    <ul className="navbar-nav">
                        <li className="nav-item">
                            <Link className="nav-link" to="/">Home</Link>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="#">Articles</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="#">Comunity</a>
                        </li>
                        <li className="nav-item dropdown">
                            <button className="btn nav-link dropdown-toggle" type="button" data-bs-toggle="dropdown"
                                    aria-expanded="false">
                                Therapies
                            </button>
                            <ul className="dropdown-menu">
                                {loading ? (
                                    <li><Loading dropdown={true} /></li>
                                ) : (
                                    therapies.map((therapy, index) => {
                                        return (
                                            <li key={therapy.id}>
                                                <Link className="dropdown-item"
                                                      to={`/therapy/${index}`}>{therapy.name}</Link>
                                            </li>
                                        )
                                    })
                                )
                                }
                            </ul>
                        </li>
                        <li className="nav-item dropdown">
                            <button className="btn nav-link dropdown-toggle" type="button" data-bs-toggle="dropdown"
                                    aria-expanded="false">
                                Products
                            </button>
                            <ul className="dropdown-menu">
                                <li><a className="dropdown-item" href="#">Books</a></li>
                                <li><a className="dropdown-item" href="#">Plant Extracts</a></li>
                                <li><a className="dropdown-item" href="#">Herbs and Spices</a></li>
                                <li><a className="dropdown-item" href="#">Herbal Teas</a></li>
                                <li><a className="dropdown-item" href="#">Natural supplements</a></li>
                                <li><a className="dropdown-item" href="#">Essential Oils</a></li>
                                <li><a className="dropdown-item" href="#">Organic Foods</a></li>
                                <li><a className="dropdown-item" href="#">Eco-friendly Beauty Products</a></li>
                                <li><a className="dropdown-item" href="#">Natural Skincare Products</a></li>
                                <li><a className="dropdown-item" href="#">Holistic Wellness Kits</a></li>
                                <li><a className="dropdown-item" href="#">Healing Crystals</a></li>
                                <li><a className="dropdown-item" href="#">Yoga and Meditation Accessories</a></li>
                                <li><a className="dropdown-item" href="#">Natural Home Cleaning Products</a></li>
                            </ul>
                        </li>
                    </ul>
                    <ul className="navbar-nav ms-auto">
                        <li className="nav-item m-1">
                            <a type="button" className="btn btn-outline-light" href="#">Sign in</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    )
}

export default Navbar;