import "./SearchBar.css";
export default function SearchBar() {
  return (
    <div className="search-box">
      <input
        type="text"
        className="search-txt"
        name=""
        placeholder="Search..."
      />
      <a href="#" className="search-btn">
        <ion-icon name="search-outline" style={{ color: "red" }}>&#128269;</ion-icon>
      </a>
    </div>
  );
}
