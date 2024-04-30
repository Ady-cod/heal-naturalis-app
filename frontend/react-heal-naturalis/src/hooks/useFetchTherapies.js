import { useState, useEffect } from 'react';

import { fetchAllTherapies } from '../services/therapyService';
import { createErrorDataObject } from '../services/errorService';
import { createDelay } from '../services/fetchService';

import { IS_DEVELOPMENT } from '../utils/constants';

export const useFetchTherapies = () => {
    const [therapies, setTherapies] = useState(null);
    const [isLoadingTherapies, setIsLoadingTherapies] = useState(null);
    const [errorTherapiesData, setErrorTherapiesData] = useState(null);

    useEffect(() => {
        const fetchTherapies = async () => {
            try {
                setIsLoadingTherapies(IS_DEVELOPMENT); // Set to true in development to demonstrate the loading animation
                await createDelay(); // Delay the process to give the loading animation a chance to play

                const therapies = await fetchAllTherapies();
                setTherapies(therapies);
            } catch (error) {
                console.error("Error captured while fetching therapies: ", error);

                const errorDataObject = createErrorDataObject(error);
                setErrorTherapiesData(errorDataObject);
            } finally {
                setIsLoadingTherapies(false);
            }
        }
        fetchTherapies();

    }, []);

    return { therapies, isLoadingTherapies, errorTherapiesData };
};