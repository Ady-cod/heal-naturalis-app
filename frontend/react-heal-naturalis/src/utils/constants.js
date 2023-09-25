export const IS_DEVELOPMENT = process.env.NODE_ENV === "development";

// const BASE_URL = 'http://localhost:8080/api';
const BASE_URL = 'http://192.168.1.164:8080/api';
// const BASE_URL = 'http://192.168.1.139:8080/api';

export const THERAPY_BASE_URL = `${BASE_URL}/therapies`;
export const SERVER_ERROR_TEST_URL = `${BASE_URL}/testException`;
export const FETCH_DELAY_DURATION = 3000;
export const FETCH_TIMEOUT_DURATION = 10000;
export const LOADING_SPAN_COUNT = 20;

export const ABORT_ERROR_NAME = "AbortError";
export const FETCH_FAILURE_IDENTIFIER = "Failed to fetch";
export const NETWORK_FAILURE_IDENTIFIER = "NetworkError";
export const CUSTOM_ABORT_ERROR_CODE = "ABORT_ERROR";
export const CUSTOM_NETWORK_ERROR_CODE = "NETWORK_ERROR";
export const CUSTOM_UNKNOWN_ERROR_CODE = "UNKNOWN_ERROR";
export const CUSTOM_ABORT_ERROR_MESSAGE = "The request took too long, check your network connection and try again.";
export const CUSTOM_NETWORK_ERROR_MESSAGE = "The server is not responding, check your network connection and try" +
    " again.";
export const CUSTOM_UNKNOWN_ERROR_MESSAGE = "An unknown error occurred, please try again.";
