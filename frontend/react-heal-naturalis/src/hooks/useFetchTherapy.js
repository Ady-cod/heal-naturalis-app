import {useEffect, useState} from "react";

import {createDelay} from "../services/fetchService";
import {fetchSingleTherapyByPageIndex} from "../services/therapyService";
import {createErrorDataObject} from "../services/errorService";

import {IS_DEVELOPMENT} from "../utils/constants";


export const useFetchTherapy = (page) => {
    const [therapy, setTherapy] = useState(null);
    const [loading, setLoading] = useState(null);
    const [errorData, setErrorData] = useState(null);

    useEffect(()=> {
        const fetchTherapy = async () => {
            try {
                setLoading(IS_DEVELOPMENT); // Set to true in development to demonstrate the loading animation
                await createDelay(); // Delay the process to give the loading animation a chance to play

                const therapyObject = await fetchSingleTherapyByPageIndex(page);
                setTherapy(therapyObject);
            } catch (error) {
                console.error("Error captured while fetching therapy: ", error);

                const errorDataObject = createErrorDataObject(error);
                setErrorData(errorDataObject);
            } finally {
                setLoading(false);
            }
        };
        fetchTherapy();
    },[page]);

    return {therapy, loading, errorData};

};