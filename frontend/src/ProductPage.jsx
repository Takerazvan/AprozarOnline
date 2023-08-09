import React, { useEffect, useState } from "react";
import { useAtom } from "jotai";
import { cartItemsAtom, selectedQuantitiesAtom } from "./Atom"; // Import Jotai atoms
import "./ProductPage.css";
import { fetchProducts,fetchSellerData } from "./data/fetchData";
function ProductPage() {
  const [products, setProducts] = useState([]);
  const [sellerEmail, setSellerEmail] = useState("");

  const [isLoading, setIsLoading] = useState(true);
  const [cartItems, setCartItems] = useAtom(cartItemsAtom);
  const [selectedQuantities, setSelectedQuantities] = useAtom(
    selectedQuantitiesAtom
  ); // Use Jotai atom for selected quantities
  const [searchQuery, setSearchQuery] = useState("");

   const urlParams = new URLSearchParams(window.location.search);
  const productId = urlParams.get("sellerId");

 
  useEffect(() => {
    const fetchData = async () => {
      const sellerData = await fetchSellerData(productId);
      setSellerEmail(sellerData);
    };

    fetchData();
  }, [productId]);

  useEffect(() => {
    const fetchData = async () => {
      const data = await fetchProducts(productId);
      setProducts(data);
      setIsLoading(false);
    };

    fetchData();
  }, [productId]);


  const handleQuantityChange = (productId, quantity) => {
    if ((selectedQuantities[productId] || 0) + quantity >= 0) {
      setSelectedQuantities((prevQuantities) => ({
        ...prevQuantities,
        [productId]: (prevQuantities[productId] || 0) + quantity,
      }));
    }
  };
  useEffect(() => {
    // Save cart items to local storage whenever it changes
    localStorage.setItem("cartItems", JSON.stringify(cartItems));
  }, [cartItems]);
  const handleAddToCart = (product) => {
    const selectedQuantity = selectedQuantities[product.id] || 0;
    if (selectedQuantity > 0) {
      const existingIndex = cartItems.findIndex(
        (item) => item.product.id === product.id
      );
      if (existingIndex !== -1) {
        const updatedCartItems = [...cartItems];
        updatedCartItems[existingIndex].quantity += selectedQuantity;
        setCartItems(updatedCartItems);
      } else {
        setCartItems((prevItems) => [
          ...prevItems,
          {
            product,
            quantity: selectedQuantity,
          },
        ]);
      }
      setSelectedQuantities((prevQuantities) => ({
        ...prevQuantities,
        [product.id]: 0,
      }));
    }
  };

  const filteredProducts = products.filter((product) =>
    product.name.toLowerCase().includes(searchQuery.toLowerCase())
  );
  if (isLoading) {
    return <div style={{ fontSize: "50px", color: "green" }}>Loading...</div>;
  } else {
    return (
      <>
        <div>
          <section
            className="section-meals"
            style={{
              display: "flex",
              flexDirection: "column",
              marginTop: "90px",
              width: "600px",
             
            }}
          >
            <img
              src="https://cdn.discordapp.com/attachments/1105018241799180358/1125757727214420028/8f7150318500e790966c129ed11a742f-1063x560-00-86.png"
              alt=""
              style={{ width: "600px", height: "auto" }}
            />
            <div
              style={{
                border: "1px solid black",
                borderRadius: "10px",
              }}
            >
              <h2
                style={{
                  color: "white",
                  marginBottom: "10px",
              
                  textShadow: "2px 2px 4px rgba(0, 0, 0, 0.9)",
                  fontFamily: "Arial, sans-serif",
                  fontSize: "30px",
                  fontWeight: "bold",
                  textAlign: "center",
                }}
              >
                {sellerEmail}
              </h2>

              <p
                style={{
                  color: "white",
                  marginBottom: "10px",
                  textShadow: "2px 2px 4px rgba(0, 0, 0, 0.9)",
                  fontFamily: "Arial, sans-serif",
                  fontSize: "18px",
                  fontWeight: "bold",
                  textAlign: "center",
                }}
              >
                Welcome to { sellerEmail }! We are a specialty store offering a
                wide range of traditional and homemade products.
              </p>
              <p
                style={{
                  color: "white",
                  marginBottom: "10px",
                  textShadow: "2px 2px 4px rgba(0, 0, 0, 0.9)",
                  fontFamily: "Arial, sans-serif",
                  fontSize: "18px",
                  fontWeight: "bold",
                  textAlign: "center",
                }}
              >
                Our store is dedicated to preserving the flavors and traditions
                of our ancestors.
              </p>
            </div>
            <br />
            <div
              style={{
                padding: "20px",
                display: "flex",
                borderRadius: "10px",

                flexDirection: "column",
                alignItems: "center",
                border: "1px solid black",
                backgroundColor: "#D5DC37",
              }}
            >
              <h2
                style={{
                  color: "white",
                  marginBottom: "10px",
                  textShadow: "2px 2px 4px rgba(0, 0, 0, 0.9)",
                  fontFamily: "Arial, sans-serif",
                  fontSize: "30px",
                  fontWeight: "bold",
                }}
              >
                Rediscover the Taste of Home
              </h2>

              <p style={{ color: "white", marginBottom: "20px" }}></p>
              <div style={{ display: "flex", marginBottom: "10px" }}>
                <button
                  style={{
                    background: "none",
                    color: "white",
                    textShadow: "2px 2px 4px rgba(0, 0, 0, 0.95)",
                    border: "none",
                    fontSize: "20px",
                    cursor: "pointer",
                    padding: "10px 20px",
                    borderRadius: "5px",
                    transition: "background-color 0.3s ease",
                  }}
                >
                  FRUITS
                </button>
                <button
                  style={{
                    background: "none",
                    color: "white",
                    textShadow: "2px 2px 4px rgba(0, 0, 0, 0.95)",
                    border: "none",
                    fontSize: "20px",
                    cursor: "pointer",
                    padding: "10px 20px",
                    borderRadius: "5px",
                    transition: "background-color 0.3s ease",
                  }}
                >
                  VEGETABLES
                </button>
                <button
                  style={{
                    background: "none",
                    color: "white",
                    textShadow: "2px 2px 4px rgba(0, 0, 0, 0.95)",
                    border: "none",
                    fontSize: "20px",
                    cursor: "pointer",
                    padding: "10px 20px",
                    borderRadius: "5px",
                    transition: "background-color 0.3s ease",
                  }}
                >
                  DAIRY
                </button>
              </div>
              <input
                type="text"
                placeholder="Search products..."
                style={{ padding: "8px", width: "100%", marginBottom: "10px" }}
                value={searchQuery}
                onChange={(e) => setSearchQuery(e.target.value)}
              />
            </div>
            <div className="container grid grid--3-cols margin-right-md" id="">
              {filteredProducts.map((product) => (
                <div className="meal" key={product.id}>
                  <img
                    src={product.photoUrl}
                    className="meal-img"
                    alt={product.name}
                  />
                  <div
                    className="meal-content"
                    style={{ backgroundColor: "#D5DC37" }}
                  >
                    <div className="meal-tags"></div>
                    <p
                      className="meal-title"
                      style={{
                        color: "white",
                        marginBottom: "10px",
                        textShadow: "2px 2px 4px rgba(0, 0, 0, 0.9)",
                        fontFamily: "Arial, sans-serif",
                        fontSize: "20px",
                        fontWeight: "bold",
                        textAlign: "center",
                      }}
                    >
                      {product.name}
                    </p>
                    <ul className="meal-attributes">
                      <li className="meal-attribute">
                        <ion-icon
                          className="meal-icon"
                          name="flame-outline"
                        ></ion-icon>
                        <span>
                          <strong style={{ fontSize: "26px" }}>
                            {product.price}
                          </strong>{" "}
                          RON
                        </span>
                      </li>
                      <li className="meal-attribute">
                        <ion-icon
                          className="meal-icon"
                          name="restaurant-outline"
                        ></ion-icon>
                        <span style={{ fontSize: "20px" }}>
                          <strong>{product.productType}</strong>
                        </span>
                      </li>
                      <li className="meal-attribute">
                        <ion-icon
                          className="meal-icon"
                          name="star-outline"
                        ></ion-icon>
                        <button
                          className="button-33"
                          role="button"
                          onClick={() => handleAddToCart(product)}
                        >
                          🛒
                          <span className="quantity-badge">
                            {selectedQuantities[product.id] || 0}
                          </span>
                        </button>
                      </li>
                      <li className="meal-attribute quantity-controls">
                        <button
                          className="quantity-button"
                          onClick={() => handleQuantityChange(product.id, -1)}
                        >
                          -
                        </button>
                        <span className="quantity-value"></span>
                        <button
                          className="quantity-button"
                          onClick={() => handleQuantityChange(product.id, 1)}
                        >
                          +
                        </button>
                      </li>
                    </ul>
                  </div>
                </div>
              ))}
            </div>
          </section>
        </div>
      </>
    );
  }
}

export default ProductPage;
