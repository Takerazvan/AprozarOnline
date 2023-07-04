import React from 'react';
import Card from "react-bootstrap/Card";
import { Link } from "react-router-dom";
import "./sellers.css";
export default function Sellers() {
  return (
    <>
      <div className="main">
        <div className="card-container">
          <h1 className="card-title">Camara Bunicii</h1>
          <Link to="/shop">
            <div
              className="card"
              style={{
                backgroundImage: `url(${"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQE36CtthEfoVgdqeOMY7Rwf5jD3-3V3xWGXw&usqp=CAU"})`,
              }}
            >
              <div className="effect-box">
                <div className="details">
                  <h2 className="name">Buy from : </h2>
                  <p className="job" style={{ color: "white" }}>
                    Camara Bunicii
                  </p>
                  <div className="contact">
                    <a href="https://t.me/EndOFrontend" target="_blank">
                      <i className="fab fa-telegram"></i>
                    </a>
                    <a
                      href="https://www.linkedin.com/in/mehrshad-zarifian-558311260"
                      target="_blank"
                    >
                      <i className="fab fa-linkedin"></i>
                    </a>
                  </div>
                </div>
              </div>
            </div>
          </Link>
        </div>
        <br />
        <div className="card-container">
          <h1 className="card-title">Besties Vegan Paradise</h1>
          <div
            className="card"
            style={{
              backgroundImage: `url(${"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR7J_5wCUc_04a-DVIKDsIHyGyLoSGWd89gAw&usqp=CAU"})`,
            }}
          >
            <div className="effect-box">
              <div className="details">
                <h2 className="name">Buy from : </h2>
                <p className="job" style={{ color: "white" }}>
                  BESTIES Vegan Paradise
                </p>
                <div className="contact">
                  <a href="https://t.me/EndOFrontend" target="_blank">
                    <i className="fab fa-telegram"></i>
                  </a>
                  <a
                    href="https://www.linkedin.com/in/mehrshad-zarifian-558311260"
                    target="_blank"
                  >
                    <i className="fab fa-linkedin"></i>
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
        <br />
        <div className="card-container">
          <h1 className="card-title">Natural Food Shop</h1>
          <div
            className="card"
            style={{
              backgroundImage: `url(${"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSr6B1fr0SVE63V9sm2POPj5rCRySrT2yubxA&usqp=CAU"})`,
            }}
          >
            <div className="effect-box">
              <div className="details">
                <h2 className="name">Buy from : </h2>
                <p className="job" style={{ color: "white" }}>
                  Natural Food Shop
                </p>
                <div className="contact">
                  <a href="https://t.me/EndOFrontend" target="_blank">
                    <i className="fab fa-telegram"></i>
                  </a>
                  <a
                    href="https://www.linkedin.com/in/mehrshad-zarifian-558311260"
                    target="_blank"
                  >
                    <i className="fab fa-linkedin"></i>
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <br />
    </>
  );
}
