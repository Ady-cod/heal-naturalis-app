import {useCartContext} from "../../hooks/useCartContext";

import CartItem from "../CartItem/CartItem";

import "./Cart.css";

const Cart = () => {
    const {cart, totalProducts, totalPrice} = useCartContext();

    return (
        <>
            {totalProducts > 0 &&
                <div className="fixed-proceed-button">
                    <button className="btn btn-success">Proceed to Checkout</button>
                </div>
            }
            <div className="container">
                <div className="cart-header">
                    <h1 className="cart-title">
                        CART
                        <span className="text-muted">/ </span>
                        <span className="cart-subtitle text-muted">Shopping cart</span>
                    </h1>
                </div>
                {totalProducts === 0 && <div className="alert alert-info">The cart is empty</div>}
                {cart.cartItems.map((item) => (
                    <div key={item.product.id} className="cart-item">
                        <CartItem item={item}/>
                    </div>
                ))}
                {totalProducts > 0 &&
                    <div className="cart-footer">
                        <div className=" row cart-summary">
                            <div className="col-md-4 mb-2 cart-summary-column text-center">
                                <h3>Total Products: <span className="text-muted">{totalProducts}</span></h3>
                            </div>
                            <div className="col-md-4 mb-3 text-center">
                                <button className="btn btn-success">Proceed to Checkout</button>
                            </div>
                            <div className="col-md-4 cart-summary-column text-center">
                                <h3>Total Price: <i className="euro-icon fas fa-euro text-muted"></i> <span
                                    className="text-muted">{totalPrice}</span></h3>
                            </div>
                        </div>
                    </div>
                }
            </div>
        </>
    );
};

export default Cart;