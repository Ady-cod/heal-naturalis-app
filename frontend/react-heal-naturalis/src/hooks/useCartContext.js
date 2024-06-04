import {useContext} from "react";

import {CartContext} from "../services/cartService";

export const useCartContext = () => useContext(CartContext);