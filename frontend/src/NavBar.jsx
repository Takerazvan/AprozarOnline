import React from "react";
import { Link } from "react-router-dom";
import logo from "./11zon_cropped.png";
import "./NavBar.css"
function NavBar() {
  return (
    <>
      <nav className="header__nav">
        <ul className="header__nav-list">
          <li className="header__nav-item">
            <Link to="/">
              <a
                className="header__nav-link"
                href="#"
                style={{ color: "#20990D" }}
              >
                Home
              </a>
            </Link>
            <Link to="/about">
              <a
                className="header__nav-link"
                href="#"
                style={{ color: "#20990D" }}
              >
                About
              </a>
            </Link>
          </li>
          <img src={logo} alt="Logo" style={{ height: "100px" }} />
          <li className="header__nav-item">
            <Link to="/products">
              <a
                className="header__nav-link"
                href="#"
                style={{ color: "#20990D" }}
              >
                Products
              </a>
            </Link>
          </li>
          <li className="header__nav-item">
            <a
              className="header__nav-link"
              href="#"
              style={{ color: "#20990D" }}
            >
              Contact
            </a>
          </li>
          <li></li>
        </ul>
      </nav>
    </>
  );
}

export default NavBar;