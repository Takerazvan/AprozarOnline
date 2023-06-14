import React, { useState } from "react";
import { Container, Row, Col, Form, Button } from "react-bootstrap";
import "./RegisterForm.css";

function RegisterForm() {
  const backgroundImageUrl =
    "https://img.freepik.com/free-vector/hand-drawn-flat-design-farmers-market-illustration_23-2149344902.jpg?w=2000";
  const mainDivStyle = {
    maxWidth: "800px",
    margin: "0 auto",
    padding: "60px",
    backgroundImage: `url(${backgroundImageUrl})`,
    backgroundSize: "cover",
    backgroundPosition: "center",
    borderRadius: "32px",
    boxShadow: "0px 2px 6px rgba(0, 0, 0, 0.1)",
    marginBottom:"-50px",
  };
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    confirmPassword: "",
    role: "",
  });
  const [passwordError, setPasswordError] = useState("");

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Password validation
    if (formData.password !== formData.confirmPassword) {
      setPasswordError("Passwords do not match");
      return;
    }

    // Create the payload object
    const payload = {
      firstName: formData.firstName,
      lastName: formData.lastName,
      email: formData.email,
      password: formData.password,
      role: formData.role,
    };
    console.log(payload);


      try {
      // Send the POST request
      const response = await fetch("http://localhost:8080/api/auth/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(payload),
      });

      if (response.ok) {
        // Registration successful, handle the response as needed
        console.log("Registration successful");
      } else {
        // Registration failed, handle the error
        console.error("Registration failed:", response.statusText);
      }
    } catch (error) {
      console.error("Error:", error);
    }


    // Reset the form
    setFormData({
      firstName: "",
      lastName: "",
      email: "",
      password: "",
      confirmPassword: "",
      role: "",
    });
    setPasswordError("");
  };

  return (
    <div id="container" style={mainDivStyle}>
      <Form onSubmit={handleSubmit} className="center-form">
        <fieldset>
          <br />
          <Form.Control
            type="text"
            name="firstName"
            placeholder="First Name"
            value={formData.firstName}
            onChange={handleChange}
            required
            autoFocus
          />
          <br />
          <br />
          <Form.Control
            type="text"
            name="lastName"
            placeholder="Last Name"
            value={formData.lastName}
            onChange={handleChange}
            required
          />
          <br />
          <br />
          <Form.Control
            type="email"
            name="email"
            placeholder="E-mail"
            value={formData.email}
            onChange={handleChange}
            required
          />
          <br />

          <br />
          <Form.Control
            type="password"
            name="password"
            placeholder="Password"
            value={formData.password}
            onChange={handleChange}
            required
          />
          <br />

          <br />
          <Form.Control
            type="password"
            name="confirmPassword"
            placeholder="Confirm Password"
            value={formData.confirmPassword}
            onChange={handleChange}
            required
          />
        

          <div className="select-container">
            <Form.Select
              name="role"
              value={formData.role}
              onChange={handleChange}
              required
            >
              <option value="">Select Role</option>
              <option value="BUYER">BUYER</option>
              <option value="SELLER">SELLER</option>
            </Form.Select>
            <button className="login-button" type="submit">
              REGISTER
            </button>
              {passwordError && <h1 className="error">{passwordError}</h1>}
          </div>
        </fieldset>
      </Form>
      
    </div>
  );
}

export default RegisterForm;
