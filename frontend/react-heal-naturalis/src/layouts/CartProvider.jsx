import {useState, useMemo, useEffect} from "react";

import {useSaveToLocalStorage} from "../hooks/useSaveToLocalStorage";

import {CartContext} from "../services/cartService";

export const CartProvider = ({children}) => {
    const [cart, setCart] = useState(() => {

        // Attempt to load the cart from localStorage initially
        const storedCart = localStorage.getItem("cart");
        return storedCart ? JSON.parse(storedCart) : {
            status: null,
            user: null,
            checkoutDate: null,
            cartItems: []
        };
    });

    const shouldSaveCart = () => {
        return cart.status !== null;
    }

// save cart to local storage when the cart changes
    useSaveToLocalStorage("cart", cart, shouldSaveCart);

    const addToCart = (product, indexInCategory) => {
        setCart(prev => {
            const itemContainingProduct = prev.cartItems.find(item => item.product.id === product.id);
            if (itemContainingProduct) {
                return {
                    ...prev, cartItems: prev.cartItems.map(item =>
                        item.product.id === product.id ? {...item, quantity: item.quantity + 1} : item)
                };
            }
            if (prev.status === null) {
                return {...prev, status: "OPEN", cartItems: [{product, quantity: 1, indexInCategory}]};
            }
            return {...prev, cartItems: [...prev.cartItems, {product, quantity: 1, indexInCategory}]};
        });
    };

    const removeFromCart = (product) => {
        setCart(prev => ({...prev, cartItems: prev.cartItems.filter(item =>
                        item.product.id !== product.id) }));
    };

    const increaseQuantity = (product) => {
        setCart(prev => ({...prev, cartItems: prev.cartItems.map(item =>
                        item.product.id === product.id ? {...item, quantity: item.quantity + 1} : item) }));
    };

    const decreaseQuantity = (product) => {
        setCart(prev => {
            const itemContainingProduct = prev.cartItems.find(item => item.product.id === product.id);
            if (itemContainingProduct.quantity === 1) {
                return {...prev, cartItems: prev.cartItems.filter(item =>
                        item.product.id !== product.id) };
            }
            return {...prev, cartItems: prev.cartItems.map(item =>
                        item.product.id === product.id ? {...item, quantity: item.quantity - 1} : item) };
        });
    };

    const totalProducts = useMemo(
        () => cart.cartItems.reduce((acc, item) => acc + item.quantity, 0)
        , [cart.cartItems]);

    const totalPrice = useMemo(
        () => {
            // Round to 2 decimal places
            const rawTotal = cart.cartItems.reduce((acc, item) => acc + item.product.price * item.quantity, 0)
            return Math.round(rawTotal * 100) / 100;
        }
        , [cart.cartItems]);

    return (
        <CartContext.Provider value={{cart, addToCart, removeFromCart, increaseQuantity, decreaseQuantity, totalProducts, totalPrice}}>
            {children}
        </CartContext.Provider>
    );

};