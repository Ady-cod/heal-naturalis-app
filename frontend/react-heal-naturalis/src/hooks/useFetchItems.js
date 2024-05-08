import {useState, useEffect} from 'react';

import {createErrorDataObject} from '../services/errorService';
import {createDelay, fetchWithTimeout} from '../services/fetchService';

import {IS_DEVELOPMENT} from '../utils/constants';

export const useFetchItems = (url, isDataRest = false, itemsName = null) => {
    const [items, setItems] = useState(null);
    const [isLoading, setIsLoading] = useState(null);
    const [errorData, setErrorData] = useState(null);

    useEffect(() => {
        const fetchItems = async () => {
            try {
                setIsLoading(IS_DEVELOPMENT); // Set to true in development to demonstrate the loading animation
                await createDelay(); // Delay the process to give the loading animation a chance to play

                const itemsData = await fetchWithTimeout(url);
                setItems(isDataRest? itemsData._embedded[itemsName] : itemsData);
            } catch (error) {
                console.error("Error captured in useFetchItems: ", error);

                const errorDataObject = createErrorDataObject(error);
                setErrorData(errorDataObject);
            } finally {
                setIsLoading(false);
            }
        }
        fetchItems();

    }, [url]);

    return {items, isLoading, errorData};
}