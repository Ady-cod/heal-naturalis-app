import "./HomePage.css";
const HomePage = () => {
  return (
    <div
      id="carouselExampleIndicators"
      className="carousel slide"
      data-bs-ride="carousel"
    >
      <div className="carousel-indicators">
        <button
          type="button"
          data-bs-target="#carouselExampleIndicators"
          data-bs-slide-to="0"
          className="active"
        ></button>
        <button
          type="button"
          data-bs-target="#carouselExampleIndicators"
          data-bs-slide-to="1"
        ></button>
        <button
          type="button"
          data-bs-target="#carouselExampleIndicators"
          data-bs-slide-to="2"
        ></button>
        {/* More indicators as necessary */}
      </div>
      <div className="carousel-inner">
        <div className="carousel-item active">
          <img src="https://heal-naturalis-bucket.s3.eu-central-1.amazonaws.com/carousel-images/books.jpg" className="d-block w-100" alt="Books" />
          <div className="carousel-caption ">
            <h4>Books:</h4>
            <p className="d-none d-md-block">
              Explore our variety of wellness books.
            </p>
          </div>
        </div>
        <div className="carousel-item">
          <img
            src="https://heal-naturalis-bucket.s3.eu-central-1.amazonaws.com/carousel-images/plant-extracts.jpg"
            className="d-block w-100"
            alt="Plant Extracts"
          />
          <div className="carousel-caption ">
            <h4>Plant Extracts:</h4>
            <p className="d-none d-md-block">
              Discover the healing power of nature.
            </p>
          </div>
        </div>
        <div className="carousel-item">
          <img
            src="https://heal-naturalis-bucket.s3.eu-central-1.amazonaws.com/carousel-images/natural-supplements.jpg"
            className="d-block w-100"
            alt="Natural Supplements"
          />
          <div className="carousel-caption ">
            <h4>Natural Supplements:</h4>
            <p className="d-none d-md-block">
              Empower your health with our diverse range of natural supplements.
            </p>
          </div>
        </div>

        {/*  More slides as necessary */}
      </div>
      <button
        className="carousel-control-prev"
        type="button"
        data-bs-target="#carouselExampleIndicators"
        data-bs-slide="prev"
      >
        <span className="carousel-control-prev-icon" aria-hidden="true"></span>
        <span className="visually-hidden">Previous</span>
      </button>
      <button
        className="carousel-control-next"
        type="button"
        data-bs-target="#carouselExampleIndicators"
        data-bs-slide="next"
      >
        <span className="carousel-control-next-icon" aria-hidden="true"></span>
        <span className="visually-hidden">Next</span>
      </button>
    </div>
  );
};

export default HomePage;
