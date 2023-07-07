const Navbar = () => {
    return (
        <nav className="navbar navbar-expand-lg navbar-dark main-color py-3">
      <div className="container-fluid">
        <span className="navbar-brand">Heal Naturalis</span>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNavdropdown"
          aria-controls="navbarNavdropdown"
          aria-expanded="false"
          aria-label="Toggle Navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNavdropdown">
          <ul className="navbar-nav">
            <li className="nav-item">
              <a className="nav-link" href="#">Home</a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="#">Articles</a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="#">Comunity</a>
            </li>
            <li className="nav-item dropdown">
              <button className="btn nav-link dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                Services
              </button>
              <ul className="dropdown-menu">
                <li><a className="dropdown-item" href="#">Ayurvedic Medicine</a></li>
                <li><a className="dropdown-item" href="#">Blood analysis through dark field microscopy & Sanum therapy</a></li>
                <li><a className="dropdown-item" href="#">Acupuncture</a></li>
                <li><a className="dropdown-item" href="#">Massage / Touch Therapy</a></li>
                <li><a className="dropdown-item" href="#">Crystal Therapy</a></li>
              </ul>
            </li>
            <li className="nav-item dropdown">
              <button className="btn nav-link dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                Products
              </button>
              <ul className="dropdown-menu">
                <li><a className="dropdown-item" href="#">Books</a></li>
                <li><a className="dropdown-item" href="#">Plant Extracts</a></li>
                <li><a className="dropdown-item" href="#">Herbs and Spices</a></li>
                <li><a className="dropdown-item" href="#">Herbal Teas</a></li>
                <li><a className="dropdown-item" href="#">Natural supplements</a></li>
                <li><a className="dropdown-item" href="#">Essential Oils</a></li>
                <li><a className="dropdown-item" href="#">Organic Foods</a></li>
                <li><a className="dropdown-item" href="#">Eco-friendly Beauty Products</a></li>
                <li><a className="dropdown-item" href="#">Natural Skincare Products</a></li>
                <li><a className="dropdown-item" href="#">Holistic Wellness Kits</a></li>
                <li><a className="dropdown-item" href="#">Healing Crystals</a></li>
                <li><a className="dropdown-item" href="#">Yoga and Meditation Accessories</a></li>
                <li><a className="dropdown-item" href="#">Natural Home Cleaning Products</a></li>
              </ul>
            </li>
          </ul>
          <ul className="navbar-nav ms-auto">
            <li className="nav-item m-1">
              <a type="button" className="btn btn-outline-light" href="#">Sign in</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    )
}

export default Navbar;