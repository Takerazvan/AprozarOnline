import React, { useEffect, useState } from "react";
import { useAtom } from "jotai";
import { cartItemsAtom, selectedQuantitiesAtom } from "./Atom"; // Import Jotai atoms
import "./ProductPage.css";


function ProductPage() {
  const [products, setProducts] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [cartItems, setCartItems] = useAtom(cartItemsAtom); 
  const [selectedQuantities, setSelectedQuantities] = useAtom(
    selectedQuantitiesAtom
  ); // Use Jotai atom for selected quantities
 const [searchQuery, setSearchQuery] = useState("");
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
      
        setIsLoading(false);
      } catch (error) {
        console.error(error);
      }
    };
    fetchData();
  }, []);

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
    return <div style={{fontSize:"50px", color:"green"}}>Loading...</div>;
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
            }}
          >
            <div
              style={{
                border: "1px solid white",
                backgroundColor: "green",
              }}
            >
              <h2
                style={{
                  color: "yellow",
                  textAlign: "center",
                  border: "1px solid yellow",
                }}
              >
                Camara Bunicii
              </h2>
              <img
                src="https://cdn.discordapp.com/attachments/1105018241799180358/1125757727214420028/8f7150318500e790966c129ed11a742f-1063x560-00-86.png"
                alt=""
                style={{ width: "700px", height: "auto" }}
              />

              <p style={{ color: "yellow" }}>
                Welcome to Camara Bunicii! We are a specialty store offering a
                wide range of traditional and homemade products.
              </p>
              <p style={{ color: "yellow" }}>
                Our store is dedicated to preserving the flavors and traditions
                of our ancestors.
              </p>
            </div>
            <br />
            <div
              style={{
                border: "1px solid black",
                backgroundColor: "green",
                padding: "20px",
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
              }}
            >
              <h2 style={{ color: "yellow", marginBottom: "10px" }}>
                Rediscover the Taste of Home
              </h2>
              <p style={{ color: "white", marginBottom: "20px" }}></p>
              <div style={{ display: "flex", marginBottom: "10px" }}>
                <button
                  style={{
                    marginRight: "10px",
                    background: "none",
                    color: "white",
                    fontSize: "20px",
                    border: "none",
                    cursor: "pointer",
                  }}
                >
                  FRUITS
                </button>
                <button
                  style={{
                    marginRight: "10px",
                    background: "none",
                    color: "white",
                    fontSize: "20px",
                    border: "none",
                    cursor: "pointer",
                  }}
                >
                  VEGETABLES
                </button>
                <button
                  style={{
                    background: "none",
                    color: "white",
                    border: "none",
                    fontSize: "20px",
                    cursor: "pointer",
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
                    src="https://cdn.romania-insider.com/sites/default/files/styles/article_large_image/public/2020-06/vegetables_in_a_bag_-_photo_julia_sudnitskaya_-_dreamstime.com_.jpg"
                    className="meal-img"
                    alt={product.name}
                  />
                  <div className="meal-content">
                    <div className="meal-tags"></div>
                    <p className="meal-title">{product.name}</p>
                    <ul className="meal-attributes">
                      <li className="meal-attribute">
                        <ion-icon
                          className="meal-icon"
                          name="flame-outline"
                        ></ion-icon>
                        <span>
                          <strong>{product.price}</strong> PRICE
                        </span>
                      </li>
                      <li className="meal-attribute">
                        <ion-icon
                          className="meal-icon"
                          name="restaurant-outline"
                        ></ion-icon>
                        <span>
                          CATEGORY <strong>{product.productType}</strong>
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
