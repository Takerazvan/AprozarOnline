import React, { useEffect, useState } from "react";
import { useAtom } from "jotai";
import { cartItemsAtom } from "./Atom";
import "./shoppingCart.css";
import {
  MDBBtn,
  MDBCard,
  MDBCardBody,
  MDBCardImage,
  MDBCol,
  MDBContainer,
  MDBIcon,
  MDBInput,
  MDBRow,
  MDBTypography,
} from "mdb-react-ui-kit";

export default function ShoppingCart() {
  const [cartItems, setCartItems] = useAtom(cartItemsAtom); // Jotai atom for cart items

  const handleRemoveItem = (itemId) => {
    setCartItems((prevItems) =>
      prevItems.filter((item) => item.product.id !== itemId)
    );
    const updatedCartItems = cartItems.filter(
      (item) => item.product.id !== itemId
    );
    localStorage.setItem("cartItems", JSON.stringify(updatedCartItems));
  };

  const handleDecreaseQuantity = (itemId) => {
    setCartItems((prevItems) =>
      prevItems.map((item) => {
        if (item.product.id === itemId && item.quantity > 0) {
          const updatedItem = {
            ...item,
            quantity: item.quantity - 1,
          };
          // Update local storage
          updateLocalStorage(prevItems, updatedItem);
          return updatedItem;
        }
        return item;
      })
    );
  };

  const handleIncreaseQuantity = (itemId) => {
    setCartItems((prevItems) =>
      prevItems.map((item) => {
        if (item.product.id === itemId) {
          const updatedItem = {
            ...item,
            quantity: item.quantity + 1,
          };
          // Update local storage
          updateLocalStorage(prevItems, updatedItem);
          return updatedItem;
        }
        return item;
      })
    );
  };

  const updateLocalStorage = (prevItems, updatedItem) => {
    const updatedCartItems = prevItems.map((item) => {
      if (item.product.id === updatedItem.product.id) {
        return updatedItem;
      }
      return item;
    });
    localStorage.setItem("cartItems", JSON.stringify(updatedCartItems));
  };

  useEffect(() => {
    // Retrieve cart items from local storage on component mount
    const storedCartItems = localStorage.getItem("cartItems");
    if (storedCartItems) {
      setCartItems(JSON.parse(storedCartItems));
    }
  }, [setCartItems]);
  const calculateTotalPrice = () => {
    let totalPrice = 0;
    cartItems.forEach((item) => {
      totalPrice += item.product.price * item.quantity;
    });
    return totalPrice.toFixed(2);
  };

  //
  const handleBuyNow = () => {
    const token = localStorage.getItem("token");
    if (token) {
      // Redirect to payment form
      window.location.replace("/payment-form");
    } else {
      // Redirect to login
      window.location.replace("/login");
    }
  };
  return (
    <>
      <div className="container">
        <div className="card1" style={{ textAlign: "center", width: "350px" }}>
          <div
            
            className="cardBody"
            style={{
              backgroundImage:
                'url("https://post.healthline.com/wp-content/uploads/2020/09/benefits-of-broccoli-732x549-thumbnail.jpg")',
              backgroundSize: "cover",
              backgroundColor: cartItems.length === 0 ? "green" : "",
              borderRadius: "10px",
              height: cartItems.length === 0 ? "400px" : "auto",
              marginTop: "100px",
            }}
          >
            <MDBTypography style={{ color: "white", fontSize: "32px" }}>
              Shopping Cart
            </MDBTypography>
            {cartItems.length === 0 ? (
              <p style={{ color: "white", fontSize: "24px" }}>
                Your cart is empty
              </p>
            ) : (
              <div>
                {cartItems.map((item) => (
                  <div
                    key={item.product.id}
                    className="cart-item"
                    style={{
                      marginBottom: "20px",
                      borderTop: "2px solid purple",
                      margin: "20px 0",
                    }}
                  >
                    <div
                      style={{
                        position: "relative",
                        display: "inline-block",
                      }}
                    >
                      <img
                        src="https://cdn.romania-insider.com/sites/default/files/styles/article_large_image/public/2020-06/vegetables_in_a_bag_-_photo_julia_sudnitskaya_-_dreamstime.com_.jpg"
                        alt={item.product.name}
                        className="meal-img"
                        style={{
                          width: "100px",
                          height: "100px",
                          marginLeft: "35px",
                        }}
                      />
                      <h1
                        style={{
                          position: "absolute",
                          top: "0",
                          right: "0",
                          cursor: "pointer",
                          backgroundColor: "white",
                          borderRadius: "50%",
                          width: "30px",
                          height: "30px",
                          display: "flex",
                          justifyContent: "center",
                          alignItems: "center",
                        }}
                        onClick={() => handleRemoveItem(item.product.id)}
                      >
                        ❌
                      </h1>
                    </div>
                    <div className="item-details">
                      <div style={{ fontSize: "25px", color: "white" }}>
                        {item.product.name}
                      </div>
                      <MDBTypography className="item-price">
                        Price: ${item.product.price}
                      </MDBTypography>
                      <div className="item-quantity">
                        <button
                          style={{
                            backgroundColor: "white",
                            width: "30px",
                            height: "30px",
                            color: "green",
                            fontSize: "10px",
                          }}
                          onClick={() =>
                            handleDecreaseQuantity(item.product.id)
                          }
                        >
                          -
                        </button>
                        <span
                          style={{
                            fontSize: "20px",
                            color: "white",
                            marginRight: "10px",
                            marginLeft: "10px",
                          }}
                        >
                          {item.quantity}
                        </span>
                        <button
                          style={{
                            backgroundColor: "white",
                            width: "30px",
                            height: "30px",
                            color: "green",
                            fontSize: "10px",
                          }}
                          onClick={() =>
                            handleIncreaseQuantity(item.product.id)
                          }
                        >
                          +
                        </button>
                      </div>
                    </div>
                  </div>
                ))}
                <div className="total-price">
                  <div style={{ fontSize: "23px", color: "black" }}>
                    Total Price: ${calculateTotalPrice()}
                  </div>
                </div>
                <button
                  style={{
                    backgroundColor: "#CEDA86",
                    color: "purple",
                    fontSize: "17px",
                    marginBottom: "5px",
                  }}
                  onClick={handleBuyNow}
                >
                  Buy Now
                </button>
              </div>
            )}
          </div>
        </div>
      </div>
    </>
  );
}
