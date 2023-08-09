import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import logo from "./assets/11zon_cropped.png";
import "./NavBar.css";
import shoppingCartIcon from "./assets/shopping.png";
import { useAtom } from "jotai";
import { cartItemsAtom } from "./Atom";
function NavBar() {
    
  const userRole = localStorage.getItem("Role");

  const [isSeller, setIsSeller] = useState(false);

 
  const [cartItems, setCartItems] = useAtom(cartItemsAtom);


  useEffect(() => {
    if (userRole != null && userRole === "SELLER") {
     setIsSeller(true)
   }
  }, [])
  

  useEffect(() => {
    // Retrieve cart items from local storage on component mount
    const storedCartItems = localStorage.getItem("cartItems");
    if (storedCartItems) {
      setCartItems(JSON.parse(storedCartItems));
    }
  }, [setCartItems]);


  return (
    <>
      <nav className="header__nav">
        <ul className="header__nav-list">
          <li className="header__nav-item">
            <Link to="/">
              <a
                className="header__nav-link"
                href="#"
                style={{ color: "green" }}
              >
                Home
              </a>
            </Link>
            <Link to="/about">
              <a
                className="header__nav-link"
                href="#"
                style={{ color: "green" }}
              >
                About
              </a>
            </Link>
          </li>
          <Link to="/">
            <img src={logo} alt="Logo" style={{ height: "100px" }} />
          </Link>
          <li className="header__nav-item">
            <Link to="/products">
              <a
                className="header__nav-link"
                href="#"
                style={{ color: "green" }}
              >
                Market
              </a>
            </Link>
          </li>
          { isSeller ? (
            <li className="header__nav-item">
              <Link to="/add">
                <a
                  className="header__nav-link"
                  href="#"
                  style={{ color: "green" }}
                >
                  Add Product
                </a>
              </Link>
            </li>
          ):null}
          <Link to="/shoppingCart  ">
            <div className="cart-icon-container">
              <img
                src={shoppingCartIcon}
                className="meal-img"
                alt={"name"}
                style={{ width: "50px", height: "50px" }}
              />
              {cartItems.length > 0 && (
                <span className="cart-items-count">{cartItems.length}</span>
              )}
            </div>
          </Link>
        </ul>
      </nav>
    </>
  );
}

export default NavBar;
