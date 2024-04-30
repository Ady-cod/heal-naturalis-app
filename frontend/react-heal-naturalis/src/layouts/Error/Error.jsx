import {formatDateAndTime} from "../../services/dateService";

import "./Error.css"

const Error = ({errorData, isDropdown}) => {

    const {formattedDate, formattedTime} = formatDateAndTime(errorData.timestamp);

    return (
        <div className={isDropdown ? "error-dropdown" : "error"}>
            <div className="alert alert-danger" role="alert">
                <h5 className="alert-heading">Error code: {errorData.error_code}</h5>
                {errorData.status && (<h6 className="alert-heading">Status: {errorData.status}</h6>)}
                {errorData.error_id && (<h6 className="alert-heading">Error id: {errorData.error_id}</h6>)}
                <h6 className="alert-heading">
                        <span className="date">Date: {formattedDate} &</span>
                        <span className="time"> Time: {formattedTime}</span>
                </h6>
                <p>{errorData.message}</p>
            </div>
        </div>
    )
}

export default Error;