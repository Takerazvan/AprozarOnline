import React from "react";
import { Link } from "react-router-dom";

function NavBar() {
  return (
    <div
      id="menu"
      style={{
        display: "flex",
        justifyContent: "center",
        backgroundColor: "#8DA4A5",
      }}
    >
      <ul>
        <li>
          <Link to="/" style={{ fontSize: "20px" }}>
            APROZAR ONLINE
         
          </Link>
        </li>

        <li>
          <Link to="/" style={{ fontSize: "20px" }}>
            Home
          </Link>
        </li>
        <Link to="/about">
          <li>
            <a href="#" style={{ fontSize: "20px" }}>
              About
            </a>
          </li>
        </Link>
        <li>
          <Link to="/products" style={{ fontSize: "20px" }}>
            Products
          </Link>
        </li>

        <li>
          <a style={{ fontSize: "20px" }}>Contact</a>
        </li>
      </ul>
    </div>
  );
}

export default NavBar;