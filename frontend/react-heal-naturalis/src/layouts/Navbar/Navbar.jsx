import {Link} from "react-router-dom";

import {useRef} from "react";

import {useBootstrapCollapse} from "../../hooks/useBootstrapCollapse";
import {useFetchItems} from "../../hooks/useFetchItems";

import Loading from "../Loading/Loading";
import Error from "../Error/Error";

import {IS_DEVELOPMENT, THERAPY_BASE_URL, MAIN_CATEGORIES_URL} from "../../utils/constants";

import "./Navbar.css";

const Navbar = () => {
    const {items:therapies, isLoading:isLoadingTherapies, errorData:errorTherapiesData} = useFetchItems(THERAPY_BASE_URL, true, "therapies");
    const {items:mainCategories, isLoading:isLoadingCategories, errorData:errorCategoriesData} = useFetchItems(MAIN_CATEGORIES_URL);

    // This useRef will allow us to manipulate the navbar directly using Bootstrap's Collapse class.
    const navbarRef = useRef(null);

    const {collapseNavbar} = useBootstrapCollapse(navbarRef);

    const handleDropdownItemClick = () => {
        collapseNavbar();
    };

    return (
        <nav className="navbar navbar-expand-md navbar-dark main-color py-3">
            <div className="container-fluid">
                <span className="navbar-brand">
                <img src="https://heal-naturalis-bucket.s3.eu-central-1.amazonaws.com/logo/logo-variant-13.png"
                     alt="Heal Naturalis Logo"/>
                    {/*Heal Naturalis*/}
                </span>
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
                <div className="collapse navbar-collapse" id="navbarNavdropdown" ref={navbarRef}>
                    <ul className="navbar-nav">
                        <li className="nav-item">
                            <Link className="nav-link" to="/">Home</Link>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="#">Articles</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="#">Community</a>
                        </li>
                        <li className="nav-item dropdown">
                            <button className="btn nav-link dropdown-toggle" type="button" data-bs-toggle="dropdown"
                                    aria-expanded="false">
                                Therapies
                            </button>
                            <ul className="dropdown-menu">
                                {isLoadingTherapies ? (
                                    <li className="dropdown-item"><Loading isDropdown={true}/></li>
                                ) : errorTherapiesData ? (
                                    <li className="dropdown-item error-li"><Error errorData={errorTherapiesData}
                                                                                  isDropdown={true}/></li>
                                ) : (
                                    therapies && therapies.map((therapy, index) => {
                                        return (
                                            <li key={therapy.id}>
                                                <Link
                                                    onClick={handleDropdownItemClick}
                                                    className="dropdown-item"
                                                    to={`/therapy/${therapy.id}`}>{therapy.name}
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
                                {isLoadingCategories ? (
                                    <li className="dropdown-item"><Loading isDropdown={true}/></li>
                                ) : errorCategoriesData ? (
                                    <li className="dropdown-item error-li"><Error errorData={errorCategoriesData}
                                                                                  isDropdown={true}/></li>
                                ) : (
                                    mainCategories && mainCategories.map((category) => {
                                        return (
                                            <li key={category.id}>
                                                <Link
                                                    onClick={handleDropdownItemClick}
                                                    className="dropdown-item"
                                                    to={category.subCategoryIds.length > 0 ?
                                                        `/sub-categories/${category.id}` :
                                                        `/category-products/${category.id}`}>{category.name}
                                                </Link>
                                            </li>
                                        )
                                    })
                                )
                                }
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