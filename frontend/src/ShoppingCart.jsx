import React, { useEffect, useState } from "react";
import { useAtom } from "jotai";
import { cartItemsAtom } from "./Atom"; 
import "./shoppingCart.css";

export default function ShoppingCart() {
  const [cartItems, setCartItems] = useAtom(cartItemsAtom); //  Jotai atom for cart items
  
const handleRemoveItem = (itemId) => {
  setCartItems((prevItems) =>
    prevItems.filter((item) => item.product.id !== itemId)
  );
  const updatedCartItems = cartItems.filter(
    (item) => item.product.id !== itemId
  );
  localStorage.setItem("cartItems", JSON.stringify(updatedCartItems));
};
  useEffect(() => {
    
      // Retrieve cart items from local storage on component mount
      const storedCartItems = localStorage.getItem("cartItems");
      if (storedCartItems) {
        setCartItems(JSON.parse(storedCartItems));
      }
     
    
  }, [ setCartItems]);

 

  return (
    <div id="basket">
      <h2>Shopping Cart</h2>
      {cartItems.length === 0 ? (
        <p>Your cart is empty.</p>
      ) : (
        <ul>
          {cartItems.map((item) => (
            <li key={item.product.id}>
              <img
                src="https://cdn.romania-insider.com/sites/default/files/styles/article_large_image/public/2020-06/vegetables_in_a_bag_-_photo_julia_sudnitskaya_-_dreamstime.com_.jpg"
                className="meal-img"
                alt={item.product.name}
              />
              <span>{item.product.name}</span>
              <br />
              <span>Quantity: {item.quantity}</span>
              <br />
              <button onClick={() => handleRemoveItem(item.product.id)}>
                Remove
              </button>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}
