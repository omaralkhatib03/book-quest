import axios from "axios";
// import { useState } from "react";
import { useNavigate } from "react-router";
import NiceButton from "../components/Button";
import "../assets/styles/BookView.css";
import Title from "../components/Title";
import BookCard from "../components/BookCard";
import InputTextBox from "../components/TextInput";
import ApiContext from "../assets/api/ApiContext";
import { useEffect, useState } from "react";

export default function BookView() {
  const navigate = useNavigate();
  const [err, setError] = useState(false);
  const [books, setBooks] = useState([]);
  const [filter, setFilter] = useState("/books");
  const [filterAuthor, setFilterAuthor] = useState("");
  const [filterTitle, setFilterTitle] = useState("");

  const config = {
    headers: {
      Authorization: "Bearer " + window.sessionStorage.getItem("token"),
    },
  };

  const handleSearchAuthor = (e) => {
    setFilterAuthor(e.target.value);
    axios
      .post(
        ApiContext.getUrl() + "/books/author",
        {
          author: e.target.value,
        },
        config
      )
      .then((res) => {
        setBooks(res.data);
      })
      .catch((err) => {
        setError(true);
        alert("Error getting books\n" + err);
        // handleLogout();
        // navigate("/");
      });
  };

  const handleSearchTitle = (e) => {
    setFilterTitle(e.target.value);
    axios
      .post(
        ApiContext.getUrl() + "/books/title",
        {
          title: e.target.value,
        },
        config
      )
      .then((res) => {
        setBooks(res.data);
      })
      .catch((err) => {
        setError(true);
        alert("Error getting books\n" + err);
        // handleLogout();
        // navigate("/");
      });
  };

  const handleLogout = async () => {
    axios
      .delete(ApiContext.getUrl() + "/user/logout", config)
      .then((res) => {
        if (res.data === "Logged out successfully") navigate("/");
      })
      .catch((err) => {
        alert("Error logging out\n" + err);
        navigate("/");
      });
  };

  const getBooks = async () => {
    return await axios
      .get(ApiContext.getUrl() + filter, config)
      .then(async (res) => {
        return res.data;
      })
      .then((data) => {
        setBooks(data);
      })
      .catch((err) => {
        setError(true);
        alert("Error getting books\n" + err);
        // handleLogout();
        // navigate("/");
      });
  };

  useEffect(() => {
    getBooks();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [filter]);

  return (
    <div className="row-flex">
      <div className="navbar">
        <NiceButton text={"Home"} func={() => navigate("/books")} />
        <NiceButton text={"Profile"} func={() => navigate("/profile")} />
        <NiceButton text={"Log out"} func={handleLogout} />
      </div>
      <div className="column-flex">
        <Title text="Book View" style={{ fontSize: "40px", padding: "40px" }} />
        <div className="row-flex">
          <div className="column-flex">
            <InputTextBox
              text="Filter by Author"
              onChange={handleSearchAuthor}
              value={filterAuthor}
            />
          </div>
          <div className="column-flex">
            <InputTextBox
              text="Filter by Title"
              onChange={handleSearchTitle}
              value={filterTitle}
            />
          </div>
        </div>
        <div id="filterbuttons" className="row-flex">
          <NiceButton
            style={{ marginRight: "10px" }}
            text="All Books"
            func={() => {
              setFilter("/books");
            }}
          />
          <NiceButton
            style={{ marginRight: "10px" }}
            text="Periodicals Books"
            func={() => {
              setFilter("/books/Periodicals");
            }}
          />
          <NiceButton
            style={{ marginRight: "10px" }}
            text="NonFiction Books"
            func={() => {
              setFilter("/books/NonFiction");
            }}
          />
          <NiceButton
            style={{ marginRight: "10px" }}
            text="Fiction Books"
            func={() => {
              setFilter("/books/Fiction");
            }}
          />
        </div>
        {err ? <span className="errorlabel">Error Getting Books</span> : <></>}
        {books.length <= 0 ? (
          <></>
        ) : (
          books.map((book, index) => {
            return (
              <BookCard
                key={index}
                title={book.title}
                author={book.author}
                isbn={book.isbn}
              />
            );
          })
        )}
      </div>
    </div>
  );
}
