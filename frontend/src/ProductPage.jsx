import { useEffect, useState } from "react";
import "./ProductPage.css";

import Card from "react-bootstrap/Card";

function ProductPage() {
  const [products, setProducts] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [selectedQuantities, setSelectedQuantities] = useState({});

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/products", {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
        });
        const data = await response.json();
        setProducts(data);
        console.log(data);
        setIsLoading(false);
      } catch (error) {
        console.error(error);
      }
    };
    fetchData();
  }, []);

  const handleQuantityChange = (quantity) => {
    setSelectedQuantities({ quantity });
  };

  const handleAddToCart = (product) => {
    setProducts((prevProducts) =>
      prevProducts.map((prevProduct) =>
        prevProduct.id === product.id
          ? {
              ...prevProduct,
              buttonText:
                prevProduct.buttonText === "Add to Cart"
                  ? "Added to Cart"
                  : "Add to Cart",
            }
          : prevProduct
      )
    );
    // TODO Perform additional logic here, such as adding/removing the item from the cart
  };

  if (isLoading) {
    return <div>Loading...</div>;
  }
  return (
    <div class="products">
      <div class="products-container">
        <h1>Our Products</h1>
        <div class="product-list">
          {products.map((product) => (
            <Card style={{ width: "18rem" }} bg="red">
              <Card.Body>
                <Card.Title>{product.name}</Card.Title>
                <Card.Text>{product.price}</Card.Text>
                <Card.Text>{product.productType}</Card.Text>
                <div style={{ display: "flex", alignItems: "center" }}>
                  <label style={{ marginRight: "16px", fontSize: "18px" }}>
                    Quantity:
                  </label>
                  <input
                    type="number"
                    min="0"
                    onChange={(e) => handleQuantityChange(e.target.value)}
                    style={{
                      width: "40px",
                      height: "12px",
                      marginRight: "16px",
                      borderRadius: "4px",
                      border: "1px solid #ccc",
                      padding: "8px",
                      fontSize: "16px",
                      textAlign: "center",
                    }}
                  />
                  <button
                    class="button-62"
                    role="button"
                    onClick={() => handleAddToCart(product)}
                  >
                    {product.buttonText || "Add to Cart"}
                  </button>
                </div>
              </Card.Body>
            </Card>
          ))}
        </div>
      </div>
    </div>
  );
}

export default ProductPage;
