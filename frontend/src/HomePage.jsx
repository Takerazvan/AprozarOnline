import { useState, useRef, useEffect } from "react";

  import "./style.css";
import { Link } from "react-router-dom";
import ShoppingCart from "./ShoppingCart";


  function HomePage() {
    const [mouseDownAt, setMouseDownAt] = useState(0);
    const [prevPercentage, setPrevPercentage] = useState(0);
   
    const trackRef = useRef(null);
   
    const handleOnDown = (e) => setMouseDownAt(e.clientX);

    const handleOnUp = () => {
      setMouseDownAt(0);
      setPrevPercentage(trackRef.current.dataset.percentage);
    };

    const handleOnMove = (e) => {
      if (mouseDownAt === 0) return;

      const mouseDelta = parseFloat(mouseDownAt) - e.clientX;
      const maxDelta = window.innerWidth / 2;

      const percentage = (mouseDelta / maxDelta) * -100;
      const nextPercentageUnconstrained = parseFloat(prevPercentage) + percentage;
      const nextPercentage = Math.max(
        Math.min(nextPercentageUnconstrained, 0),
        -40
      );

      trackRef.current.dataset.percentage = nextPercentage;

      trackRef.current.animate(
        {
          transform: `translate(${nextPercentage}%, -50%)`,
        },
        { duration: 1200, fill: "forwards" }
      );

      const images = trackRef.current.getElementsByClassName("image");
      for (const image of images) {
        image.animate(
          {
            objectPosition: `${100 + nextPercentage}% center`,
          },
          { duration: 1200, fill: "forwards" }
        );
      }
    };

    useEffect(() => {
      window.addEventListener("mousedown", handleOnDown);
      window.addEventListener("touchstart", handleOnDown);
      window.addEventListener("mouseup", handleOnUp);
      window.addEventListener("touchend", handleOnUp);
      window.addEventListener("mousemove", handleOnMove);
      window.addEventListener("touchmove", handleOnMove);

      
    
      return () => {
        window.removeEventListener("mousedown", handleOnDown);
        window.removeEventListener("touchstart", handleOnDown);
        window.removeEventListener("mouseup", handleOnUp);
        window.removeEventListener("touchend", handleOnUp);
        window.removeEventListener("mousemove", handleOnMove);
        window.removeEventListener("touchmove", handleOnMove);
      };
    });
    
    return (
      <>
        <div
          id="image-track"
          ref={trackRef}
          data-mouse-down-at={mouseDownAt}
          data-prev-percentage={prevPercentage}
        >
          <div className="category">Vegetables</div>

          <img
            className="image"
            src="https://media.istockphoto.com/id/1203599923/photo/food-background-with-assortment-of-fresh-organic-vegetables.jpg?b=1&s=170667a&w=0&k=20&c=fRNCED4dyey-i6K2RHTPaIm_HFLUr3hnj4J6WblHaXc="
            draggable="false"
            data-category="Vegetables"
          />

          <div className="category">Fruits</div>
          <img
            className="image"
            src="https://foodboxhq.com/wp-content/uploads/2018/07/fruit-of-the-month-club.webp"
            draggable="false"
          />
          <Link to="/add">
            <div className="category">Dairy</div>
          </Link>

          <img
            className="image"
            src="https://media.istockphoto.com/id/1194287257/photo/dairy-products-on-rustic-wooden-table.jpg?s=612x612&w=0&k=20&c=WFbC5ZtHzp7En_Zmpa19pfHlP9z7Xs3aMrT33rT537c="
            draggable="false"
          />
          <div className="category">Meat</div>
          <img
            className="image"
            src="https://media.istockphoto.com/id/1310910433/photo/selection-of-assorted-raw-meat-food-for-zero-carb-carnivore-diet-uncooked-beef-steak-ground.jpg?b=1&s=170667a&w=0&k=20&c=kHDw07LONz2akPa8pPe_rhUXhoc_aCryBdgI9G2QG3g="
            draggable="false"
          />
          <div className="category">Wines</div>
          <img
            className="image"
            src="https://www.chefspencil.com/wp-content/uploads/ft-img2.jpg"
            draggable="false"
          />
          <div className="category">Honey</div>
          <img
            className="image"
            src="https://images5.alphacoders.com/402/thumb-1920-402703.jpg"
            draggable="false"
          />
        </div>
        <div id="cent">
          <Link to="/login">
            <a id="source-link" className="meta-link" href="" target="_blank">
              <i className="fa-solid fa-link"></i>
              <span style={{ fontSize: "2.8rem" }}>LOGIN</span>
            </a>
          </Link>
          <Link to="/register">
            <a id="yt-link" className="meta-link" href="" target="_blank">
              <i className="fa-brands fa-youtube"></i>
              <span style={{ fontSize: "2.8rem" }}>REGISTER</span>
            </a>
          </Link>
        </div>
      </>
    );
  }
  export default HomePage;
