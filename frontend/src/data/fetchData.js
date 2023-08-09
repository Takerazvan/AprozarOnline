     




        export async function fetchSellerData(productId) {
        try {
          const response = await fetch(`http://localhost:8080/api/user/${productId}`);
          const sellerData = await response.json();
          return sellerData.email;
        } catch (error) {
          console.error(error);
          return null;
        }
      }

      export async function fetchProducts(productId) {
        try {
          const response = await fetch(
            `http://localhost:8080/seller/product/${productId}`,
            {
              method: "GET",
              headers: {
                "Content-Type": "application/json",
              },
            }
          );
          const data = await response.json();
          console.log(data)
          return data;
        } catch (error) {
          console.error(error);
          return [];
        }
      }
