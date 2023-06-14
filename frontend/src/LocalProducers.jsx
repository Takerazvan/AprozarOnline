import React from "react";
import "./LocalPorducers.css";



export default function LocalProducers() {
  return (
    <>
      <section className="section-meals">
        <div className="container grid grid--3-cols margin-right-md">
          <div className="meal">
            <img
              src="https://static01.nyt.com/images/2020/01/29/dining/27burner-meat/27burner-meat-superJumbo.jpg"
              className="meal-img"
              alt="Vegetables"
              loading="lazy"
            />

            <div className="meal-content">
              <div className="meal-tags">
                <span className="tag tag--vegetarian">Grass-Fed Meats</span>
              </div>
              <p className="meal-title">Organic Greens</p>
              <ul className="meal-attributes">
                <li className="meal-attribute">
                  <ion-icon
                    className="meal-icon"
                    name="flame-outline"
                  ></ion-icon>
                  <span>{/* <strong>650</strong> calories */}</span>
                </li>
                <li className="meal-attribute">
                  <ion-icon
                    className="meal-icon"
                    name="restaurant-outline"
                  ></ion-icon>
                  {/* <span>
                    NutriScore &reg; <strong>74</strong>
                  </span> */}
                </li>
                <li className="meal-attribute">
                  <ion-icon
                    className="meal-icon"
                    name="star-outline"
                  ></ion-icon>
                  <span>
                    <strong>4.9</strong> rating (537)
                  </span>
                </li>
              </ul>
            </div>
          </div>

          <div className="meal">
            <img
              src="https://cdn.britannica.com/17/196817-050-6A15DAC3/vegetables.jpg"
              className="meal-img"
              alt="Dairy"
              loading="lazy"
            />
            <div className="meal-content">
              <div className="meal-tags">
                <span className="tag tag--vegan">Vegan Shop</span>
              </div>
              <p className="meal-title">Farm Fresh Dairy</p>
              <ul className="meal-attributes">
                <li className="meal-attribute">
                  <ion-icon
                    className="meal-icon"
                    name="flame-outline"
                  ></ion-icon>
                  <span></span>
                </li>
                <li className="meal-attribute">
                  <ion-icon
                    className="meal-icon"
                    name="restaurant-outline"
                  ></ion-icon>
                </li>
                <li className="meal-attribute">
                  <ion-icon
                    className="meal-icon"
                    name="star-outline"
                  ></ion-icon>
                  <span>
                    <strong>4.8</strong> rating (441)
                  </span>
                </li>
              </ul>
            </div>
          </div>

          <div className="meal">
            <img
              src="https://static.vecteezy.com/system/resources/previews/000/520/853/original/dairy-farm-set-vector.jpg"
              className="meal-img"
              alt="Meat"
            
            />
            <div className="meal-content">
              <div className="meal-tags">
                <span className="tag tag--vegan">Friendly Farm</span>
                <strong></strong>
              </div>
              <p className="meal-title">Grass-Fed Meats</p>
              <ul className="meal-attributes">
                <li className="meal-attribute">
                  <ion-icon
                    className="meal-icon"
                    name="flame-outline"
                  ></ion-icon>
                  <span></span>
                </li>
                <li className="meal-attribute">
                  <ion-icon
                    className="meal-icon"
                    name="restaurant-outline"
                  ></ion-icon>
                </li>
                <li className="meal-attribute">
                  <ion-icon
                    className="meal-icon"
                    name="star-outline"
                  ></ion-icon>
                  <span>
                    <strong>4.4</strong> rating (349)
                  </span>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </section>
    </>
  );
}
