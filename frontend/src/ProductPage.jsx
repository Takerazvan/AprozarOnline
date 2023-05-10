import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

function ProductPage() {
  const [product, setProduct] = useState(null);
  const { productId } = useParams();

  useEffect(() => {
    const getProduct = async () => {
      const response = await fetch(
        `http://localhost:8080/products/${productId}`
      );
      const data = await response.json();
      setProduct(data);
    };
    getProduct();
  }, [productId]);

  if (!product) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <h2>{product.name}</h2>
      <p>{product.description}</p>
      <p>Price: ${product.price}</p>
      <img src={product.image} alt={product.name} />
    </div>
  );
}

export default ProductPage;
