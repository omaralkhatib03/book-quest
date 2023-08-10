import Title from "./Title";
import "../assets/styles/BookCard.css";


export default function BookCard({title, author, isbn}) {
  return (
    <div className="book-row">
      <img
        src={require("../assets/images/book.png")}
        alt=""
        className="placeholder"
      />
      <div className="info-column">
        <Title text={title} style={{ fontSize: "28px"}} />
        <div className="info-row"> 
        <p className="info">Author: {author}</p>
        <p className="info">isbn: {isbn} </p>
        </div>
        {/* TODO: List book info and details */}
      </div>
    </div>
  );
}
