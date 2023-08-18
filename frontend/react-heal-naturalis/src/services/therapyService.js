const BASE_URL = 'http://localhost:8080/api/therapies';

export const fetchTherapyByPage = async (page) => {
    try {
        const response = await fetch(`${BASE_URL}?page=${page}&size=1`);
        const data = await response.json();
        const therapyObject = data._embedded.therapies[0];
        return therapyObject;
    } catch(error) {
        console.error("Error fetching therapy data: ", error);
        throw error;
    }
}