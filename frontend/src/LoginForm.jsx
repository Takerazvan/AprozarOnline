import React, { useState } from "react";
import "./Login.css";
import loginBackground from "./loginBackground.jpg";
export default function LoginForm() {
 
  
  const mainDivStyle = {
    
    margin: "0 auto",
    padding: "30px",
    backgroundImage: `url(${loginBackground})`,
    backgroundSize: "cover",
    backgroundPosition: "center",
    borderRadius: "8px",
    boxShadow: "0px 2px 6px rgba(0, 0, 0, 0.1)",
  };

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleUsernameChange = (e) => {
    setUsername(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

 const handleSubmit = async (e) => {
   e.preventDefault();
   const payload = {
     email: username,
     password: password,
   };

   try {
     const response = await fetch("http://localhost:8080/api/auth/login", {
       method: "POST",
       headers: {
         "Content-Type": "application/json",
       },
       body: JSON.stringify(payload),
     });

     if (response.ok) {
       const responseJson = await response.json();
       
       const token = responseJson.token;
   
 
      // Save the token in local storage or state, and use it for subsequent requests
       
      localStorage.setItem("token", token);
       window.location.replace("/");
      
       //
     }
     
     else {
       // Login failed, handle the error
       alert("Login failed:", response.statusText);
     }
   } catch (error) {
     console.error("Error:", error);
   }

   // Reset the form inputs
   setUsername("");
   setPassword("");
 };


  return (
    <div style={{marginTop:"120px"}}>
      <form className="login-form" onSubmit={handleSubmit} style={mainDivStyle}>
        <input
          className="login-input"
          type="email"
          placeholder="Username"
          value={username}
          onChange={handleUsernameChange}
        />

        <input
          className="login-input"
          type="password"
          placeholder="Password"
          value={password}
          onChange={handlePasswordChange}
        />

        <button
          className="login-button"
          type="submit"
          style={{ fontSize: "2.8rem",backgroundColor:"purple"}}
        >
          
          LOGIN
        </button>
        {/* <p className="login-link" style={{ fontSize: "3.8rem", color:"yellow" }}>
          Forgot your password?
        </p> */}
      </form>
    </div>
  );
}
