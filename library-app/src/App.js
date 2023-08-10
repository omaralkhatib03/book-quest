import "./assets/styles/App.css";
import AddUserPage from "./pages/AddUser";
import BookView from "./pages/BookView";
import LoginPage from "./pages/Login";
import ProfilePage from "./pages/Profile";
import { BrowserRouter, Route, Routes } from "react-router-dom";

function App() {
  return (
    <>
      <BrowserRouter>
        <div className="App">
          <Routes>
            <Route exact path="/" element={<LoginPage />} />
            <Route path="/addUser" element={<AddUserPage />} />
            <Route path="/books" element={<BookView />} />
            <Route path="/profile" element={<ProfilePage />} />
          </Routes>
        </div>
      </BrowserRouter>
    </>
  );
}

export default App;
