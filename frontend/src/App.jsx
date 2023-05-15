import { BrowserRouter, Routes, Route } from "react-router-dom";

import HomePage from "./HomePage";
import ProductPage from "./ProductPage";
import NavBar from "./NavBar";
import RegisterForm from "./RegisterForm";
import AddProductForm from "./AddProductForm";


function App() {
  return (
    <BrowserRouter>
      <NavBar />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/products" element={<ProductPage />} />
        <Route path="/register" element={<RegisterForm />} />
        <Route path="/add" element={<AddProductForm />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
