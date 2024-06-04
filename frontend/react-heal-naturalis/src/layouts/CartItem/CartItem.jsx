import {useCartContext} from "../../hooks/useCartContext";

import "./CartItem.css";

const CartItem = ({item}) => {
    const {removeFromCart, increaseQuantity, decreaseQuantity} = useCartContext();
    return (
        <div className="card mb3">
            <div className="card-body">
                <div className="row">
                    <div className="col-md-8">
                        <h5 className="card-title">{item.product?.category.name}</h5>
                        <h6 className="card-subtitle mb-2 text-muted">
                            Price: <i className="euro-icon fas fa-euro"></i> {item.product?.price}
                        </h6>
                        <div className="product-characteristics">
                            <strong> Characteristics:</strong>
                            <ul className="text-muted">
                                {item.product?.productOptionValues.map((optionValue) => (
                                    <li key={optionValue.id}>
                                        <strong>{optionValue.productOption.name}:</strong> {optionValue.value}
                                    </li>
                                ))}
                            </ul>
                        </div>
                    </div>
                    <div className="col-md-4 cart-item-buttons">
                        <button className="btn btn-outline-secondary btn-sm" onClick={()=>decreaseQuantity(item.product)}>
                            <i className="fas fa-minus"></i>
                        </button>
                        <span className="mx-2">{item.quantity}</span>
                        <button className="btn btn-outline-secondary btn-sm" onClick={()=>increaseQuantity(item.product)}>
                            <i className="fas fa-plus"></i>
                        </button>
                        <button className="btn btn-outline-danger btn-sm mx-4" onClick={()=>removeFromCart(item.product)}>
                            <i className="fas fa-trash-alt"></i>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default CartItem;