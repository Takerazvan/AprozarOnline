import React from "react";

import "./about.css";
const AboutUs = () => {
  
  return (
    <div className="about-us">
     
      <div className="content-container">
        <div className="image-container">
          <img
            src="https://www.rebootwithjoe.com/wp-content/uploads/2014/04/Farmers-Markets-Local-Produce-Benefits1.jpg"
            alt="Local Farmer"
          />
        </div>
        <div className="text-container">
          <h3>Our Mission</h3>
          <p>
            Welcome to our Natural Food Online Store!We are
            passionate about providing you with the finest selection of natural
            products sourced directly from local producers. We believe in the
            power of nature and the importance of supporting local communities.
          </p>
        </div>
      </div>
      <div className="content-container">
        <div className="text-container">
          <h3>What Sets Us Apart</h3>
          <ol>
            <li>
              <strong>Locally Sourced:</strong> We prioritize working directly
              with local farmers and producers who share our values. By sourcing
              our products locally, we support the community and reduce our
              carbon footprint.
            </li>
            <li>
              <strong>Quality Assurance:</strong> We understand the importance
              of providing high-quality products to our customers. That's why we
              carefully select our suppliers and perform rigorous quality checks
              to ensure the freshness and purity of our products.
            </li>
          </ol>
        </div>
        <div className="image-container">
          <img
            src="https://www.datocms-assets.com/20941/1661169053-northern-ireland-beef-and-lamb-farm-quality-assurance-certification.png?auto=compress&fm=jpg&w=850"
            alt="Quality Assurance"
          />
        </div>
      </div>
    </div>
  );
};

export default AboutUs;
