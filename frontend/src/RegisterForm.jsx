import React, { useState } from "react";
import { Container, Row, Col, Form, Button } from "react-bootstrap";
import "./RegisterForm.css";

function RegisterForm() {

//PASSWORD
  
    const [meter, setMeter] = useState(false);
    const [password, setPassword] = useState("");

    const atLeastOneUppercase = /[A-Z]/g; // capital letters from A to Z
    const atLeastOneLowercase = /[a-z]/g; // small letters from a to z
    const atLeastOneNumeric = /[0-9]/g; // numbers from 0 to 9
    const atLeastOneSpecialChar = /[#?!@$%^&*-]/g; // any of the special characters within the square brackets
    const eightCharsOrMore = /.{8,}/g; // eight characters or more

    const passwordTracker = {
      uppercase: password.match(atLeastOneUppercase),
      lowercase: password.match(atLeastOneLowercase),
      number: password.match(atLeastOneNumeric),
      specialChar: password.match(atLeastOneSpecialChar),
      eightCharsOrGreater: password.match(eightCharsOrMore),
    };

    const passwordStrength = Object.values(passwordTracker).filter(
      (value) => value
    ).length;

 
  
   

    const [input, setInput] = useState({
      password: "",
      confirmPassword: "",
    });

    const [error, setError] = useState({
      password: "",
      confirmPassword: "",
    });

    const onInputChange = (e) => {
      const { name, value } = e.target;
      setInput((prev) => ({
        ...prev,
        [name]: value,
      }));
      validateInput(e);
    };

    const validateInput = (e) => {
      let { name, value } = e.target;

      setError((prev) => {
        const stateObj = { ...prev, [name]: "" };

        switch (name) {
          case "password":
            if (!value) {
              stateObj[name] = "Please enter Password.";
            } else if (
              input.confirmPassword &&
              value !== input.confirmPassword
            ) {
              stateObj["confirmPassword"] =
                "Password and Confirm Password does not match.";
            } else {
              stateObj["confirmPassword"] = input.confirmPassword
                ? ""
                : error.confirmPassword;
            }
            break;

          case "confirmPassword":
            if (!value) {
              stateObj[name] = "Please enter Confirm Password.";
            } else if (input.password && value !== input.password) {
              stateObj[name] = "Password and Confirm Password does not match.";
            } else {
              stateObj[name] = " Access granted";
            }
            break;

          default:
            break;
        }

        return stateObj;
      });
  };
  ///


  const backgroundImageUrl =
    "https://w0.peakpx.com/wallpaper/929/367/HD-wallpaper-fruits-fruits-vegetables-fruit-vegetable.jpg";
  const mainDivStyle = {
    maxWidth: "800px",
    margin: "0 auto",
    
    
    padding: "20px",
    backgroundImage: `url(${backgroundImageUrl})`,
    backgroundSize: "cover",
    backgroundPosition: "center",
    borderRadius: "32px",
    boxShadow: "0px 2px 6px rgba(0, 0, 0, 0.1)",
    marginBottom: "-110px",
   
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
    if (input.password !== input.confirmPassword) {
      setPasswordError("Passwords do not match");
      return;
    }
    

    // Create the payload object
    const payload = {
      firstName: formData.firstName,
      lastName: formData.lastName,
      email: formData.email,
      password: input.password,
      role: formData.role,
    };
   

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
           alert("User Already Exists");
        console.error("Registration failed:", response.statusText);
      }
    } catch (error) {
      console.error("Error:", error);
   
    }

    // Reset the form
    setInput({
      password: "", // Reset password field
      confirmPassword: "", // Reset confirm password field
    });
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
    setMeter(false);
  };
//
  
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
            placeholder="Enter Password"
            value={input.password}
            onChange={onInputChange}
            onFocus={() => setMeter(true)}
            onInput={(e) => setPassword(e.target.value)}
          />
          <br />

          <br />
          <Form.Control
            type="password"
            name="confirmPassword"
            placeholder="Confirm Password"
            value={input.confirmPassword}
            onChange={onInputChange}
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
            {input.password ? (
              <>
                {password.length < 5 && (
                  <p
                    id="err"
                    className="error"
                    style={{
                      fontFamily: "Monserat",
                      fontSize: "18px",
                      fontWeight: "bold",
                      color: "white",
                    }}
                  ></p>
                )}

                {passwordStrength < 5 ? (
                  <p
                    id="err"
                    className="error"
                    style={{
                      fontFamily: "Monserat",
                      fontSize: "19px",
                      fontWeight: "bold",
                      color: "red",
                      fontStyle: "oblique",
                      backgroundColor: "lightgrey",
                      borderRadius: "10%",
                    }}
                  >
                    Password must contain:
                    {!passwordTracker.eightCharsOrGreater &&
                      "at least 8 characters"}
                    {!passwordTracker.uppercase && " uppercase"}
                    {!passwordTracker.lowercase && " lowercase"}
                    {!passwordTracker.specialChar && " special character"}
                    {!passwordTracker.number && ", number"}
                  </p>
                ) : null}
               
                {passwordStrength >= 5 && (
                  <h1 style={{ fontSize: "25px", color: "black" }}>
                    Strong Password
                  </h1>
                )}
                {passwordError && (
                  <h1 className="error" style={{ fontSize: "18px" }}>
                    {passwordError}
                  </h1>
                )}
              </>
            ) : null}
            <div className="error">
              {meter && <div className="password-strength-meter"></div>}
            </div>
            <button
              className="login-button"
              type="submit"
              style={{
                fontSize: "3.6rem",
                backgroundColor: "yellow",
                color: "#0D971C",
              }}
            >
              REGISTER
            </button>
          </div>
        </fieldset>
      </Form>
      <style >
       {`
          .password-strength-meter {
            height: 1.2rem;
            background-color: white;
            border-radius: 3px;
            margin: 0.5rem 0;
          }

          .password-strength-meter::before {
            content: "";
            background-color: ${[
              "red",
              "orange",
              "#03a2cc",
              "#03a2cc",
              "#0ce052",
            ][passwordStrength - 1] || ""};
            height: 100%;
            width: ${(passwordStrength / 5) * 100}%;
            display: block;
            border-radius: 3px;
            transition: width 0.2s;
          }
        `}
      </style>
    </div>
  );
}

export default RegisterForm;
