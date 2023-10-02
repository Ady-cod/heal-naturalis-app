import {useEffect, useState} from "react";

import {createDelay, fetchWithTimeout} from "../../services/fetchService";
import {createErrorDataObject} from "../../services/errorService";

import Loading from "../Loading/Loading";
import Error from "../Error/Error";

import {SERVER_ERROR_TEST_URL, FETCH_TIMEOUT_DURATION} from "../../utils/constants";

const TestServerError = () => {
    const [loading, setLoading] = useState(true);
    const [errorData, setErrorData] = useState(null);

    useEffect(() => {
        const fetchServerError = async () => {
            try {
                // Delay the fetch to demonstrate the loading animation
                await createDelay();

                const data = await fetchWithTimeout(SERVER_ERROR_TEST_URL, FETCH_TIMEOUT_DURATION);

                if (data) {
                    throw new Error(`Data was unexpectedly returned from an endpoint designed to throw an exception. ` +
                    `Received data: ${JSON.stringify(data)}`);
                }
            } catch (error) {
                console.error("Error captured while fetching Server Error: ", error);

                const errorDataObject = createErrorDataObject(error);
                setErrorData(errorDataObject);
            } finally {
                setLoading(false);
            }
        }
        fetchServerError();
    }, []);

    if (loading) {
        return <Loading />
    }
    if (errorData) {
        return <Error errorData={errorData} />
    }
    return (
        <div className="container d-flex align-items-center justify-content-center" style={{minHeight: "86vh"}}>
            <div className="col-12 alert alert-danger text-center">
                <h1>Server Error Test</h1>
            </div>
        </div>
    );
}
export default TestServerError;