import {useState} from "react";
import {useParams} from "react-router-dom";

import {useFetchItemsByParentId} from "../../hooks/useFetchItemsByParentId";
import {useCartContext} from "../../hooks/useCartContext";

import Loading from "../Loading/Loading";
import Error from "../Error/Error";

import {PRODUCTS_BY_CATEGORY_URL, CATEGORY_BASE_URL} from "../../utils/constants";

import "./CategoryProducts.css";

const CategoryProducts = () => {
    const {addToCart} = useCartContext();
    const {categoryId} = useParams();
    const {
        items: products,
        isLoading,
        errorData,
        parent: category
    } = useFetchItemsByParentId(PRODUCTS_BY_CATEGORY_URL, CATEGORY_BASE_URL, categoryId);
    const [alerts, setAlerts] = useState([]);

    const addAlert = (message) => {
        const timestamp = new Date().getTime();
        setAlerts(prev => [...prev, {message, timestamp}]);
        setTimeout(() => {
            setAlerts(prev => prev.filter(alert => alert.timestamp !== timestamp));
        }, 5000);
    };

    const handleAddToCart = (product, index) => {
        addToCart(product, index);
        addAlert(`${product.category.name} ${index+1} added to cart`);
    };

    if (isLoading) {
        return <Loading/>
    }

    if (errorData) {
        return <Error errorData={errorData}/>
    }

    return (
        <>
            <div className="container container-centered-text">
                <h2 className="category-products-title">Products in category: {category?.name}</h2>
                <div className="row">
                    {products && products.map((product, index) => (
                        <div key={product.id} className="col-12 col-md-6 col-lg-4">
                            <div className="card mb-3">
                                <img src={product.imageUrl} className="card-img-top"
                                     alt={`${category?.name} ${index + 1} - Image`}/>
                                <div className="card-body">
                                    <h5 className="card-title">{category?.name} {index + 1}</h5>
                                    <h6 className="card-subtitle mb-2 text-muted">Stock: {product.stock}</h6>
                                    <h6 className="card-subtitle mb-2 text-muted">
                                        Price:
                                        <img className="price-icon m-1"
                                             src="https://heal-naturalis-bucket.s3.eu-central-1.amazonaws.com/icons/euro.svg"
                                        alt="Euro icon"/>
                                        {product.price}
                                    </h6>
                                    <div className="product-characteristics">
                                        <strong> Characteristics:</strong>
                                        <ul className="text-muted">
                                            {product.productOptionValues.map((optionValue) => (
                                                <li key={optionValue.id}>
                                                    <strong>{optionValue.productOption.name}:</strong> {optionValue.value}
                                                </li>
                                            ))}
                                        </ul>
                                    </div>
                                    <button className="btn btn-success"
                                            onClick={() => handleAddToCart(product, index)}>Add to Cart
                                    </button>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
            <div className="alert-container">
                {alerts.map((alert) => (
                    <div key={alert.timestamp} className="alert alert-success alert-dismissible fade show" role="alert">
                        {alert.message}
                    </div>
                ))}
            </div>
        </>
    )
};

export default CategoryProducts;