import { useState, useEffect } from 'react';

import { fetchAllTherapies } from '../services/therapyService';
import { createErrorDataObject } from '../services/errorService';
import { createDelay } from '../services/fetchService';

import { IS_DEVELOPMENT } from '../utils/constants';

export const useFetchTherapies = () => {
    const [therapies, setTherapies] = useState([]);
    const [loading, setLoading] = useState(null);
    const [errorData, setErrorData] = useState(null);

    useEffect(() => {
        const fetchTherapies = async () => {
            try {
                setLoading(IS_DEVELOPMENT); // Set to true in development to demonstrate the loading animation
                await createDelay(); // Delay the process to give the loading animation a chance to play

                const therapies = await fetchAllTherapies();
                setTherapies(therapies);
            } catch (error) {
                console.error("Error captured while fetching therapies: ", error);

                const errorDataObject = createErrorDataObject(error);
                setErrorData(errorDataObject);
            } finally {
                setLoading(false);
            }
        }
        fetchTherapies();

    }, []);

    return { therapies, loading, errorData };

};