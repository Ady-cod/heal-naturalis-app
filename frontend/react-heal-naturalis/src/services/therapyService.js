import {fetchWithTimeout} from "./fetchService";

import {THERAPY_BASE_URL, FETCH_TIMEOUT_DURATION} from "../utils/constants";

export const fetchSingleTherapyByPageIndex = async (page) => {
    try {
        const data = await fetchWithTimeout(`${THERAPY_BASE_URL}?page=${page}&size=1`, FETCH_TIMEOUT_DURATION);
        const therapyObject = data._embedded.therapies[0];
        return therapyObject;

    } catch (error) {
        console.error("Error fetching single therapy data: ", error);
        throw error;
    }
}

export const fetchAllTherapies = async () => {
    try {
        // First fetch to get the total number of therapies
        let data = await fetchWithTimeout(THERAPY_BASE_URL, FETCH_TIMEOUT_DURATION);
        const totalElements = data.page.totalElements;

        // Second fetch to get all the therapies in one page
        data = await fetchWithTimeout(`${THERAPY_BASE_URL}?page=0&size=${totalElements}`, FETCH_TIMEOUT_DURATION);
        const therapyList = data._embedded.therapies;
        return therapyList;

    } catch (error) {
        console.error("Error fetching all therapies: ", error);
        throw error;
    }
}