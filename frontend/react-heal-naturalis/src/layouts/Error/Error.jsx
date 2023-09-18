import "./Error.css"

const Error = ({message, dropdown}) => {
    return (
        <div className={dropdown ? "error-dropdown" : "error"}>
            <div className="alert alert-danger" role="alert">
                <h5 className="alert-heading">System Error:</h5>
                <p>{message}</p>
            </div>
        </div>
    )
}

export default Error;