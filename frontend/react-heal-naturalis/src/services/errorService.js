import * as Const from "../utils/constants";

const parseErrorMessage = (error) => {
    try {
        return JSON.parse(error.message);
    } catch {
        console.error(error);
        return null;
    }
}

const createCustomErrorObject = (error) => {
    let errorData;
    if (error.name && (error.name === Const.ABORT_ERROR_NAME)) {
        // Detected an abort error due to a timeout
        errorData = {
            error_id: null,
            error_code: Const.CUSTOM_ABORT_ERROR_CODE,
            message: Const.CUSTOM_ABORT_ERROR_MESSAGE,
            timestamp: new Date().toISOString(),
            status: null
        }
    } else if (error.message && (error.message.includes(Const.FETCH_FAILURE_IDENTIFIER) ||
        error.message.includes(Const.NETWORK_FAILURE_IDENTIFIER))) {
        // Detected a network error
        errorData = {
            error_id: null,
            error_code: Const.CUSTOM_NETWORK_ERROR_CODE,
            message: Const.CUSTOM_NETWORK_ERROR_MESSAGE,
            timestamp: new Date().toISOString(),
            status: null
        }
    } else {
        // Detected an unknown error
        errorData = {
            error_id: null,
            error_code: Const.CUSTOM_UNKNOWN_ERROR_CODE,
            message: Const.CUSTOM_UNKNOWN_ERROR_MESSAGE,
            timestamp: new Date().toISOString(),
            status: null
        }
    }
    return errorData;
}

export const createErrorDataObject = (error) => {

    // Try to parse the error message as JSON (indicating a server response)
    let errorData = parseErrorMessage(error.message);

    if (!errorData) {
        // If parsing fails, categorize the error and create a custom error object accordingly
        // to match the error object type returned by the server
        errorData = createCustomErrorObject(error);
    }
    return errorData;
}
