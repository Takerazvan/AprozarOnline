import React, { useEffect, useState } from "react";
import axios from "axios";
import "./form.css";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";

const AddProductForm = () => {
  const [name, setName] = useState("");
  const [quantity, setQuantity] = useState();
  const [price, setPrice] = useState("");
  const [type, setType] = useState("");
  const [productDescription, setProductDescription] = useState("");
  const [photos, setPhotos] = useState("");
  const [productId, setProductId] = useState(""); 
  const userID = localStorage.getItem("userId");

  const handleNameChange = (e) => {
    setName(e.target.value);
  };

  const handleQuantityChange = (e) => {
    setQuantity(e.target.value);
  };

  const handlePriceChange = (e) => {
    setPrice(e.target.value);
  };

  const handleTypeChange = (e) => {
    setType(e.target.value);
  };

  const handleDescriptionChange = (e) => {
    setProductDescription(e.target.value);
  };
  const handleId = () => {
    setProductId(userID);
  };
  const handlePhotosChange = (e) => {
    const files = e.target.files;
    setPhotos(files);
  };

  useEffect(()=>{
   
 },[])

  return (
    <div
      style={{
        display: "block",
        width: 500,
        padding: 0,
        backgroundColor: "white",
        marginTop: "60px",
        backgroundImage: `url(
          "https://w0.peakpx.com/wallpaper/929/367/HD-wallpaper-fruits-fruits-vegetables-fruit-vegetable.jpg"
        )`,
      }}
    >
      <h4
        style={{
          color: "white",
          fontSize: "30px",
          textAlign: "center",
          fontFamily: "Arial, sans-serif",
          padding: "10px 0",
        }}
      >
        Add Product Form
      </h4>
      <Form
        encType="multipart/form-data"
        action="http://localhost:8080/seller/addProduct"
        method="post"
        style={{ fontSize: "18px" }}
      >
        <Form.Group>
          <Form.Label style={{ fontFamily: "Arial, sans-serif" }}>
            Product Name:
          </Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter product name"
            value={name}
            name="name"
            onChange={handleNameChange}
          />
        </Form.Group>
        <Form.Group>
          <Form.Label style={{ fontFamily: "Arial, sans-serif" }}>
            Quantity:
          </Form.Label>
          <Form.Control
            type="number"
            name="quantity"
            placeholder="Enter quantity"
            value={quantity}
            onChange={handleQuantityChange}
          />
        </Form.Group>

        <Form.Group style={{ display: "none" }}>
          <Form.Label
            style={{ fontFamily: "Arial, sans-serif", display: "none" }}
          ></Form.Label>
          <Form.Control name="userId" type="text" value={userID} />
        </Form.Group>
        <Form.Group>
          <Form.Label style={{ fontFamily: "Arial, sans-serif" }}>
            Category:
          </Form.Label>
          <Form.Control
            type="text"
            name="type"
            placeholder="Enter category"
            value={type}
            onChange={handleTypeChange}
          />
        </Form.Group>
        <Form.Group>
          <Form.Label style={{ fontFamily: "Arial, sans-serif" }}>
            Description:
          </Form.Label>
          <Form.Control
            as="textarea"
            name="productDescription"
            rows={3}
            placeholder="Enter product description"
            value={productDescription}
            onChange={handleDescriptionChange}
          />
        </Form.Group>
        <Form.Group>
          <Form.Label style={{ fontFamily: "Arial, sans-serif" }}>
            Product Photos:
          </Form.Label>
          <Form.Control
            type="file"
            name="photos"
            onChange={handlePhotosChange}
          />
        </Form.Group>

        <div
          style={{
            display: "flex",
            justifyContent: "center",
            width: "100%",
          }}
        >
          <Button
            variant="primary"
            type="submit"
            style={{ fontSize: "20px", backgroundColor: "red" }}
          >
            Add Product
          </Button>
        </div>
      </Form>
    </div>
  );
};

export default AddProductForm;
