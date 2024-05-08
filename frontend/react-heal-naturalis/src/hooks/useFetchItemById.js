import {useEffect, useState} from "react";

import {createDelay, fetchWithTimeout} from "../services/fetchService";
import {createErrorDataObject} from "../services/errorService";

import {IS_DEVELOPMENT} from "../utils/constants";

export const useFetchItemById = (itemUrl, itemId) => {
    const [item, setItem] = useState(null);
    const [isLoading, setIsLoading] = useState(null);
    const [errorData, setErrorData] = useState(null);

    useEffect(()=> {
        const fetchItemById = async () => {
            try {
                setIsLoading(IS_DEVELOPMENT); // Set to true in development to demonstrate the loading animation
                await createDelay(); // Delay the process to give the loading animation a chance to play

                const itemData = await fetchWithTimeout(`${itemUrl}/${itemId}`);
                setItem(itemData);
            } catch (error) {
                console.error("Error captured while fetching item: ", error);

                const errorDataObject = createErrorDataObject(error);
                setErrorData(errorDataObject);
            } finally {
                setIsLoading(false);
            }
        };
        fetchItemById();
    },[itemUrl, itemId]);

    return {item, isLoading, errorData};
};