import React, { useState } from "react";
import { Container, Row, Col, Form, Button } from "react-bootstrap";
import "./RegisterForm.css";

function RegisterForm() {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    password: "",
    confirmPassword: "",
   
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Create the payload object
    const payload = {
      name: formData.name,
      email: formData.email,
      password: formData.password,
      
    };

    // Make the POST request
    fetch("http://localhost:8080/customers", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(payload),
    })
      .then((response) => response.json())
      .then((data) => {
        // Handle the response data if needed
        console.log(data);
      })
      .catch((error) => {
        // Handle any errors
        console.error(error);
      });

    // Reset the form
    setFormData({
      name: "",
      email: "",
      password: "",
      confirmPassword: "",
     
    });
  };

  return (
    <div id="container">
      <header>Register new account</header>
      <Form onSubmit={handleSubmit}>
        <fieldset>
          <br />
          <Form.Control
            type="text"
            name="name"
            placeholder="name"
            value={formData.name}
            onChange={handleChange}
            required
            autoFocus
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
          <br />
          <br />
          <Form.Check
            type="radio"
            id="seller"
            name="role"
            label="Seller"
            value="seller"
            checked={formData.role === "seller"}
            onChange={handleChange}
            required
          />
          <Form.Check
            type="radio"
            id="customer"
            name="role"
            label="Customer"
            value="customer"
            checked={formData.role === "customer"}
            onChange={handleChange}
            required
          />
          <br />
          <br />
          <Button type="submit">REGISTER</Button>
        </fieldset>
      </Form>
    </div>
  );
}

export default RegisterForm;
