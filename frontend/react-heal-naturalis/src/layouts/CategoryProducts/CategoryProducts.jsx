import {useParams} from "react-router-dom";

import {useFetchItemsByParentId} from "../../hooks/useFetchItemsByParentId";

import Loading from "../Loading/Loading";
import Error from "../Error/Error";

import {PRODUCTS_BY_CATEGORY_URL, CATEGORY_BASE_URL} from "../../utils/constants";

import "./CategoryProducts.css";

const CategoryProducts = () => {
    const {categoryId} = useParams();
    const {items:products, isLoading, errorData, parent:category} = useFetchItemsByParentId(PRODUCTS_BY_CATEGORY_URL, CATEGORY_BASE_URL,categoryId);

    if (isLoading) {
        return <Loading/>
    }

    if (errorData) {
        return <Error errorData={errorData}/>
    }

    return (
        <div className="container">
            <h2 className="sub-categories-title">Products for category: {category?.name}</h2>
            <div className="row">
                {products && products.map((product, index) => (
                    <div key={product.id} className="col-12 col-md-6 col-lg-4">
                        <div className="card mb-3">
                            <img src={product.imageUrl} className="card-img-top" alt={`${category?.name} ${index+1} - Image`}/>
                            <div className="card-body">
                                <h5 className="card-title">{category?.name}</h5>
                                <h6 className="card-subtitle mb-2 text-muted">Stock: {product.stock}</h6>
                                <h6 className="card-subtitle mb-2 text-muted">
                                    Price:
                                    <img className="price-icon m-1" src="https://heal-naturalis-bucket.s3.eu-central-1.amazonaws.com/icons/euro.svg"/>
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
                                <button className="btn btn-success">Add to Cart</button>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    )
};

export default CategoryProducts;