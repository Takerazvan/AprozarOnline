import { BrowserRouter, Routes, Route } from "react-router-dom";

import HomePage from "./HomePage";
import ProductPage from "./ProductPage";
import NavBar from "./NavBar";


function App() {
  return (
    <BrowserRouter>
      <NavBar />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/products" element={<ProductPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
