import { useEffect, useState } from "react";
import "./ProductPage.css";

import Card from "react-bootstrap/Card";

function ProductPage() {
  const [products, setProducts] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [selectedQuantities, setSelectedQuantities] = useState({});
  const [cartItems, setCartItems] = useState([]);

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

  const handleQuantityChange = (productId, quantity) => {
    if ((selectedQuantities[productId] || 0) + quantity >= 0) {
      setSelectedQuantities((prevQuantities) => ({
        ...prevQuantities,
        [productId]: (prevQuantities[productId] || 0) + quantity,
      }));
    }
  };

  const handleAddToCart = (product) => {
    const existingIndex = cartItems.findIndex(
      (item) => item.product.id === product.id
    );
    if (existingIndex !== -1) {
      const updatedCartItems = [...cartItems];
      updatedCartItems[existingIndex].quantity +=
        selectedQuantities[product.id] || 1;
      setCartItems(updatedCartItems);
    } else {
      setCartItems((prevItems) => [
        ...prevItems,
        {
          product,
          quantity: selectedQuantities[product.id] || 1,
        },
      ]);
    }
    setSelectedQuantities((prevQuantities) => ({
      ...prevQuantities,
      [product.id]: 0,
    }));
  };

  if (isLoading) {
    return <div>Loading...</div>;
  }
  return (
    <>
      <div id="razvan" >
        <section className="section-meals">
          <div className="container grid grid--3-cols margin-right-md" id="">
            {products.map((product) => (
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

export default ProductPage;
