import React, { useEffect, useState } from "react";
import Card from "react-bootstrap/Card";
import { Link } from "react-router-dom";
import "./sellers.css";

export default function Sellers() {

  const [sellers, setSellers] = useState([]);

 useEffect(() => {
   fetch("http://localhost:8080/api/user/seller")
     .then((response) => response.json())
     .then((data) => {
       
       setSellers(data);
     })
     .catch((error) => console.error("Error:", error));
 }, []);
  return (
    <>
      <div className="main">
        {sellers.map((seller, index) => (
          <div className="card-container" key={index}>
            <Link to={`/shop?sellerId=${seller.id}`}>
              <h1 className="card-title" style={{ textAlign: "center" }}>
                {seller.email}
              </h1>
              <div
                className="card"
                style={{
                  backgroundImage: `url("https://images.unsplash.com/photo-1542838132-92c53300491e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1974&q=80")`,
                  backgroundSize: "80%",
                }}
              >
                <div className="effect-box">
                  <div className="details">
                    <h2 className="name">Buy from:</h2>
                    <h1 className="card-title">{seller.email}</h1>
                    <p className="job" style={{ color: "white" }}></p>
                  </div>
                </div>
              </div>
            </Link>
          </div>
        ))}
      </div>
      <br />
    </>
  );
}
