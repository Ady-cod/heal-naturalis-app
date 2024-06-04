import {Link, useParams} from "react-router-dom";

import {useFetchItemsByParentId} from "../../hooks/useFetchItemsByParentId";

import Loading from "../Loading/Loading";
import Error from "../Error/Error";

import {SUB_CATEGORIES_URL, CATEGORY_BASE_URL} from "../../utils/constants";

import "./SubCategories.css";

const SubCategories = () => {
    const {parentCategoryId} = useParams();
    const {items:subCategories, isLoading, errorData, parent:parentCategory} = useFetchItemsByParentId(SUB_CATEGORIES_URL, CATEGORY_BASE_URL,parentCategoryId);

    if (isLoading) {
        return <Loading/>
    }

    if (errorData) {
        return <Error errorData={errorData}/>
    }

    return (
        <div className="container container-centered-text">
            <h2 className="sub-categories-title">Subcategories for: {parentCategory?.name}</h2>
            <div className="row">
                {subCategories && subCategories.map((category) => (
                    <div key={category.id} className="col-12 col-md-6 col-lg-4">
                        <Link to={category.subCategoryIds.length > 0 ?
                            `/sub-categories/${category.id}` :
                            `/category-products/${category.id}`} style={{ textDecoration: 'none' }}>
                            <div className="card mb-3">
                                <img src={category.imageUrl} className="card-img-top" alt={category.name}/>
                                <div className="card-body">
                                    <h5 className="card-title">{category.name}</h5>
                                    {/*<p className="card-text">{category.description}</p>*/}
                                </div>
                            </div>
                        </Link>
                    </div>
                ))}
            </div>
        </div>
    )
};

export default SubCategories;