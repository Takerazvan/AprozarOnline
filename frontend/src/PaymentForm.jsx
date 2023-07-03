import React, { useState } from "react";
import "./paymentForm.css"
function PaymentForm() {
  const [cardNumber, setCardNumber] = useState("");
  const [cardName, setCardName] = useState("");
  const [cardMonth, setCardMonth] = useState("");
  const [cardYear, setCardYear] = useState("");
  const [cardCvv, setCardCvv] = useState("");
  const [isCardFlipped, setIsCardFlipped] = useState(false);

  const handleCardNumberChange = (e) => {
    setCardNumber(e.target.value);
  };

  const handleCardNameChange = (e) => {
    setCardName(e.target.value);
  };

  const handleCardMonthChange = (e) => {
    setCardMonth(e.target.value);
  };

  const handleCardYearChange = (e) => {
    setCardYear(e.target.value);
  };

  const handleCardCvvChange = (e) => {
    setCardCvv(e.target.value);
  };

  const flipCard = (isFlipped) => {
    setIsCardFlipped(isFlipped);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Perform form submission or validation here
  };

  return (
    <div
      id="payment-form"
      className="container"
      style={{
        marginTop: "100px",
        borderRadius: "10px",
        backgroundColor: "white",
        opacity: "89%",
       
      }}
    >
      <form action="PaymentServlet" method="post">
        <fieldset >
          <legend>
            <h1
              className="form-top"
              style={{ fontSize: "20px", color: "green" }}
            >
              Payment form
            </h1>
            <br />
          </legend>
          <div className="form-group">
            <label style={{ fontSize: "15px", color: "black" }}>Name</label>
            <div className="col-sm-9">
              <input
                type="text"
                className="form-control"
                name="card-holder-name"
                id="card-holder-name"
                placeholder="Card Holder's Name"
              />
            </div>
          </div>
          <div className="form-group">
            <label style={{ fontSize: "15px", color: "black" }}>Number</label>
            <div className="col-sm-9">
              <input
                type="text"
                className="form-control"
                name="card-number"
                id="card-number"
                placeholder="Debit/Credit Card Number"
              />
            </div>
          </div>
          <div className="form-group">
            <label style={{ fontSize: "15px", color: "black" }}>
              Expiration
            </label>
            <div className="col-sm-9">
              <div className="row">
                <div className="col-xs-6">
                  <select
                    className="form-control col-sm-2"
                    name="expiry-month"
                    id="expiry-month"
                  >
                    <option>Month</option>
                    <option value="01">Jan (01)</option>
                    <option value="02">Feb (02)</option>
                    <option value="03">Mar (03)</option>
                    <option value="04">Apr (04)</option>
                    <option value="05">May (05)</option>
                    <option value="06">June (06)</option>
                    <option value="07">July (07)</option>
                    <option value="08">Aug (08)</option>
                    <option value="09">Sep (09)</option>
                    <option value="10">Oct (10)</option>
                    <option value="11">Nov (11)</option>
                    <option value="12">Dec (12)</option>
                  </select>
                </div>
                <div className="col-xs-6">
                  <select className="form-control col-sm-2" name="expiry-year">
                    <option value="17">2017</option>
                    <option value="18">2018</option>
                    <option value="19">2019</option>
                    <option value="20">2020</option>
                    <option value="21">2021</option>
                    <option value="22">2022</option>
                    <option value="23">2023</option>
                  </select>
                </div>
              </div>
            </div>
          </div>
          <div className="form-group">
            <label style={{ fontSize: "15px", color: "black" }}>Card CVV</label>
            <div className="col-sm-6">
              <input
                type="text"
                className="form-control"
                name="cvv"
                id="cvv"
                placeholder="Security Code"
              />
            </div>
          </div>
          <br />
          <div className="form-group">
            <div className="col-sm-12">
              <button
                type="button"
                className="btn btn-success"
                id="pay-now"
                style={{ fontSize: "15px", color: "blue" }}
              >
                Pay Now
              </button>
            </div>
          </div>
        </fieldset>
      </form>
    </div>
  );
}

export default PaymentForm;
