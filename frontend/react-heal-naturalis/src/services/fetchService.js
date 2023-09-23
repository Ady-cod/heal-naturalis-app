export const fetchWithTimeout = async (url, timeoutDuration) => {

    // Implementing an AbortController to cancel the fetch request if the server takes too long to respond
    const controller = new AbortController();
    const timeout = setTimeout(() => controller.abort(), timeoutDuration);

    try {
        const response = await fetch(url, {signal: controller.signal});
        clearTimeout(timeout);
        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(JSON.stringify(errorData));
        }
        const data = await response.json();
        return data;
    } catch (error) {
        clearTimeout(timeout);
        console.error("Error fetching data: ", error);
        throw error;
    }

}