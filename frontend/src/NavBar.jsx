import React from "react";
import { Link } from "react-router-dom";
import logo from "./11zon_cropped.png";
import "./NavBar.css"
function NavBar() {
  return (
    <>
      <nav className="header__nav">
        <ul className="header__nav-list">
          <img src={logo} alt="Logo" style={{ height: "100px" }} />
          <li className="header__nav-item">
            <Link to="/home">
              <a
                className="header__nav-link"
                href="#"
                style={{ color: "black" }}
              >
                Home
              </a>
            </Link>
            <Link to="/about">
              <a
                className="header__nav-link"
                href="#"
                style={{ color: "black" }}
              >
                About
              </a>
            </Link>
          </li>
          <li className="header__nav-item">
            <Link to="/products">
              <a
                className="header__nav-link"
                href="#"
                style={{ color: "black" }}
              >
                Products
              </a>
            </Link>
          </li>
          <li className="header__nav-item">
            <a className="header__nav-link" href="#" style={{ color: "black" }}>
              Contact
            </a>
          </li>
        </ul>
      </nav>

      <label className="toggle-button__label" for="toggleInput">
        <i className="fa-solid fa-bars toggle-button__burger"></i>
        <i className="fa-solid fa-xmark toggle-button__cross"></i>
      </label>
    </>
  );
}

export default NavBar;