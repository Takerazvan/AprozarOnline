import React, { useState } from "react";
import "./reset.css"
import loginBackground from "./loginBackground.jpg"
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEye, faEyeSlash } from "@fortawesome/free-solid-svg-icons";
export default function ResetUserPasswordForm() {
  const [newPassword, setNewPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const [message, setMessage] = useState("");

  const handleNewPasswordChange = (e) => {
    setNewPassword(e.target.value);
  };
   const handleTogglePassword = () => {
     setShowPassword(!showPassword);
   };
const handleFormSubmit = async (e) => {
  e.preventDefault();
   

  // Function to extract token from URL parameters
  const extractTokenFromURL = () => {
    const queryParams = new URLSearchParams(window.location.search);
    return queryParams.get("token");
  };
//MOVE FETCHDATA 
  // Extract the token from URL params
  const token = extractTokenFromURL();

  try {
   
    const response = await fetch(
      `http://localhost:8080/api/user/reset/password?token=${token}`,
      {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: newPassword, 
      }
    );

    if (response.ok) {
      setMessage("Password reset successfully!");
    } else {
      setMessage("Password reset failed.");
    }
  } catch (error) {
    console.error("Error:", error);
    setMessage("An error occurred.");
  }
};


  

  return (
    <div
      className="reset-password-container"
      style={{ backgroundImage: `url(${loginBackground})` }}
    >
      <h1>Reset Password</h1>
      {message && <p className="message">{message}</p>}
      <form className="reset-password-form" onSubmit={handleFormSubmit}>
        <label style={{ fontSize: "15px" }}>
          New Password:
          <input
            type={showPassword ? "text" : "password"}
            value={newPassword}
            onChange={handleNewPasswordChange}
            required
          />
          <FontAwesomeIcon
            icon={showPassword ? faEyeSlash : faEye}
            onClick={handleTogglePassword}
            className="eye-icon"
            style={{color:"white"}}
          />
        </label>
        <button className="reset-button" type="submit">
          Reset Password
        </button>
      </form>
    </div>
  );
}
