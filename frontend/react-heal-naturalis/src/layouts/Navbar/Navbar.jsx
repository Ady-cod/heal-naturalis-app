import {Link} from "react-router-dom";

import {useRef} from "react";
import {useTherapies} from "../../hooks/useTherapies";
import {useBootstrapCollapse} from "../../hooks/useBootstrapCollapse";

import Loading from "../Loading/Loading";
import Error from "../Error/Error";

import {IS_DEVELOPMENT} from "../../utils/constants";

import "./Navbar.css";

const Navbar = () => {
    const { therapies, loading, errorData } = useTherapies();

    // This useRef will allow us to manipulate the navbar directly using Bootstrap's Collapse class.
    const navbarRef = useRef(null);

    const { collapseNavbar } = useBootstrapCollapse(navbarRef);

    const handleDropdownItemClick = () => {
        collapseNavbar();
    };

    return (
        <nav className="navbar navbar-expand-md navbar-dark main-color py-3">
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
                {/*<div className={isNavbarCollapsed ? "navbar-collapse collapse" : "navbar-collapse collapse show"} id="navbarNavdropdown">*/}
                <div className="collapse navbar-collapse" id="navbarNavdropdown" ref={navbarRef}>
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
                                    <li className="dropdown-item"><Loading dropdown={true}/></li>
                                ) : errorData ? (
                                    <li className="dropdown-item"><Error errorData={errorData} dropdown={true}/></li>
                                ) : (
                                    therapies.map((therapy, index) => {
                                        return (
                                            <li key={therapy.id}>
                                                <Link
                                                    onClick={handleDropdownItemClick}
                                                    className="dropdown-item"
                                                    to={`/therapy/${index}`}>{therapy.name}
                                                </Link>
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
                                {IS_DEVELOPMENT && (
                                    <>
                                        <hr/>
                                        <li><Link
                                            onClick={handleDropdownItemClick}
                                            className="dropdown-item"
                                            to="/testServerError">
                                            Test Server Error
                                        </Link></li>
                                    </>
                                )
                                }
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