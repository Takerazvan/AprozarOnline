import React, { useState } from "react";

function AddProductForm() {
  const [productData, setProductData] = useState({
    productName: "",
    sellerName: "",
  });
  
  //hardcoded seller
  const sellerId = 1;


  const handleChange = (e) => {
    setProductData({ ...productData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Create a new product object
  
  const newProduct = {
    productType: "Fruits",
    customer: null,
    name: productData.productName,
    quantity: productData.quantity
  };

  fetch(`http://localhost:8080/sellers/${sellerId}/products`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(newProduct)
  })
    .then((response) => response.json())
    .then((data) => {
      console.log("Product added successfully:", data);
      setProductData({
        productName: "",
        quantity: 0
      });
    })
    .catch((error) => {
      console.error("Error adding product:", error);
    });
};
  

  return (
    <div>
      <h2>Add Product</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="productName">Product Name:</label>
          <input
            type="text"
            id="productName"
            name="productName"
            value={productData.productName}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label htmlFor="sellerName">Seller Name:</label>
          <input
            type="text"
            id="sellerName"
            name="sellerName"
            value={productData.sellerName}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit">Add Product</button>
      </form>
    </div>
  );
}

export default AddProductForm;
