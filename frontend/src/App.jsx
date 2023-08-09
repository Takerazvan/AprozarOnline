import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import HomePage from "./HomePage";
import ProductPage from "./ProductPage";
import NavBar from "./NavBar";
import RegisterForm from "./RegisterForm";
import AddProductForm from "./AddProductForm";
import LoginForm from "./LoginForm";
import AboutUs from "./pages/AboutUs";
import ShoppingCart from "./ShoppingCart";
import PaymentForm from "./PaymentForm";
import Sellers from "./Sellers";
import ResetPasswordForm from "./components/ResetPassForm";
import ResetUserPasswordForm from "./components/ResetUserPasswordForm";
function App() {

 
  return (
    <Router>
      <div>
        <NavBar
          style={{
            position: "sticky",
            top: "0",
            left: "0",
            width: "100%",
            zIndex: "999",
          }}
        />
      </div>
      <div>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/products" element={<Sellers />} />
          <Route path="/about" element={<AboutUs />} />
          <Route path="/shop" element={<ProductPage />} />
          <Route path="/add" element={<AddProductForm />} />
          <Route path="/register" element={<RegisterForm />} />
          <Route path="/login" element={<LoginForm />} />

          <Route path="/shoppingCart" element={<ShoppingCart />} />
          <Route path="/payment-form" element={<PaymentForm />} />
          <Route path="/reset-password" element={<ResetPasswordForm />} />
          <Route path="/reset-password-form" element={<ResetUserPasswordForm />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
