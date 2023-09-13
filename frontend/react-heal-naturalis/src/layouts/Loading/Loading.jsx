import "./Loading.css";

// The Loading component is responsible for rendering a loading animation with a set of rotating spans.
const Loading = () => {
    // LOADING_SPAN_COUNT determines the number of rotating spans in the loader
    const LOADING_SPAN_COUNT= 20;
    return (
        <div className="loader">
            {
                // Creating an array with LOADING_SPAN_COUNT elements, and mapping over it to create the rotating spans
                [...Array(LOADING_SPAN_COUNT)].map((_, index) => (
                <span key={index} style={{ '--i': index + 1 }}></span>
            ))
            }
            <p className="loader-text" aria-label="Loading">Loading...</p>
        </div>
    );
}

export default Loading;