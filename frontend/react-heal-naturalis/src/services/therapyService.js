import { THERAPY_BASE_URL } from "../utils/constants";

export const fetchTherapyByPage = async (page) => {
    try {
        const response = await fetch(`${THERAPY_BASE_URL}?page=${page}&size=1`);
        const data = await response.json();
        const therapyObject = data._embedded.therapies[0];
        return therapyObject;
    } catch(error) {
        console.error("Error fetching therapy data: ", error);
        throw error;
    }
}

export const fetchAllTherapies = async () => {
    try {
        // First fetch to get the total number of therapies
        let response = await fetch(THERAPY_BASE_URL);
        let data = await response.json();
        let totalElements = data.page.totalElements;

        // Second fetch to get all the therapies in one page
        response = await fetch(`${THERAPY_BASE_URL}?page=0&size=${totalElements}`);
        data = await response.json();
        return data._embedded.therapies;
    } catch(error) {
        console.error("Error fetching all therapies: ", error);
        throw error;
    }
}