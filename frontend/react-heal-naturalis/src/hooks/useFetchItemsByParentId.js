import {useEffect, useState} from "react";

import {createDelay, fetchWithTimeout} from "../services/fetchService";
import {createErrorDataObject} from "../services/errorService";

import {IS_DEVELOPMENT} from "../utils/constants";

export const useFetchItemsByParentId = (itemsUrl, parentUrl, parentId) => {
    const [items, setItems] = useState(null);
    const [isLoading, setIsLoading] = useState(null);
    const [errorData, setErrorData] = useState(null);
    const [parent, setParent] = useState(null);

    useEffect(()=> {
        const fetchItemsByParent = async () => {
            try {
                setIsLoading(IS_DEVELOPMENT); // Set to true in development to demonstrate the loading animation
                await createDelay(); // Delay the process to give the loading animation a chance to play

                // Fetch the subcategories of the parent category
                const itemsData = await fetchWithTimeout(`${itemsUrl}/${parentId}`);
                const parentData = await fetchWithTimeout(`${parentUrl}/${parentId}`);
                setItems(itemsData);
                setParent(parentData);
            } catch (error) {
                console.error("Error captured while fetching items: ", error);

                const errorDataObject = createErrorDataObject(error);
                setErrorData(errorDataObject);
            } finally {
                setIsLoading(false);
            }
        };
        fetchItemsByParent();
    },[itemsUrl, parentUrl, parentId]);

    return {items, isLoading, errorData, parent};
};