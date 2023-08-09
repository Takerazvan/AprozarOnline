import React, { useState } from "react";
import "./resetpassform.css"; // Import your resetpassform.css stylesheet
import loginBackground from "./loginBackground.jpg";
import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEye, faEyeSlash } from "@fortawesome/free-solid-svg-icons";

export default function ResetPassForm() {
  const mainDivStyle = {
    margin: "0 auto",
    padding: "30px",
    backgroundImage: `url("https://w0.peakpx.com/wallpaper/929/367/HD-wallpaper-fruits-fruits-vegetables-fruit-vegetable.jpg")`,
    backgroundSize: "cover",
    backgroundPosition: "center",
    borderRadius: "8px",
    boxShadow: "0px 2px 6px rgba(0, 0, 0, 0.1)",
  };

  const [email, setEmail] = useState("");
  const [error, setError] = useState("");
  const [success, setSuccess] = useState(""); // State to hold the success message

  const handleEmail = (e) => {
    setEmail(e.target.value);
  };

  const handleFormSubmit = async (e) => {
    e.preventDefault();

    const data = email;

    try {
      const response = await fetch(
        "http://localhost:8080/api/auth/reset-password",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(data),
        }
      );

      if (!response.ok) {
        const errorMessage = await response.json();
        throw new Error(errorMessage.message);
      }

      setError("");
      setSuccess("Email sent"); // Set success message
    } catch (error) {
      setError("Error: " + error.message);
      console.log("Error: " + error);
    }
  };

  return (
    <div style={{ marginTop: "120px" }}>
      {error && <div style={{ color: "white", fontSize: "21px" }}>{error}</div>}
      {success && (
        <div style={{ color: "purple", fontSize: "22px" }}>{success}</div>
      )}
      <form
        className="login-form"
        onSubmit={handleFormSubmit}
        style={mainDivStyle}
      >
        <input
          className="login-input"
          type="email"
          placeholder="Enter your email"
          value={email}
          onChange={handleEmail}
        />

        <button
          className="login-button"
          type="submit"
          style={{ fontSize: "2.9rem", backgroundColor: "white" ,color:"black"}}
        >
          Send password reset email
        </button>
      </form>
    </div>
  );
}
