import { BrowserRouter, Routes, Route } from "react-router-dom";

import HomePage from "./HomePage";
import ProductPage from "./ProductPage";
import NavBar from "./NavBar";
import RegisterForm from "./RegisterForm";
import AddProductForm from "./AddProductForm";
import LoginForm from "./LoginForm";
import AboutUs from "./AboutUs";



function App() {
  return (
    <BrowserRouter>
      <NavBar />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/products" element={<ProductPage />} />
        <Route path="/about" element={<AboutUs />} />
        <Route path="/register" element={<RegisterForm />} />
        <Route path="/login" element={<LoginForm />} />
        <Route path="/add" element={<AddProductForm />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
