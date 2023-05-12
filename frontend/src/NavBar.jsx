import React from "react";
import { Link } from "react-router-dom";


function NavBar() {
  return (
    <div id="menu">
      <ul>
        <li>
          <Link to="/" style={{ fontSize: "25px" }}>
            APROZAR ONLINE
          </Link>
        </li>
        <li>
          <Link to="/">
            Home
          </Link>
        </li>
        <li>
          <a href="#">About</a>
        </li>
        <li>
          <Link to="/products" >
            Products
          </Link>
        </li>
        <li>
          <a href="#">Portfolio</a>
        </li>
        <li>
          <a>Contact</a>
        </li>
      </ul>
    </div>
  );
}

export default NavBar;