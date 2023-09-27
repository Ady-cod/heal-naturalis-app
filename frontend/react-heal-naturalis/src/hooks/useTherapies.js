import { useState, useEffect } from 'react';

import { fetchAllTherapies } from '../services/therapyService';
import { createErrorDataObject } from '../services/errorService';
import { createDelay } from '../services/fetchService';

import { FETCH_DELAY_DURATION } from '../utils/constants';

export const useTherapies = () => {
    const [therapies, setTherapies] = useState([]);
    const [loading, setLoading] = useState(true);
    const [errorData, setErrorData] = useState(null);

    useEffect(() => {
        const fetchTherapies = async () => {
            try {
                // Delaying the fetch to demonstrate the loading animation
                await createDelay(FETCH_DELAY_DURATION);

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