import Title from "../components/Title";
import "../assets/styles/BookCard.css";
import { useNavigate } from "react-router";
import InputTextBox from "../components/TextInput";
import NiceButton from "../components/Button";
import {useState } from "react";
import axios from "axios";
import { useEffect } from "react";
import ApiContext from "../assets/api/ApiContext";
import '../assets/styles/Profile.css';


export default function ProfilePage() {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const config = {
    headers: {
      Authorization: "Bearer " + sessionStorage.getItem("token"),
    },
  };
  
  const handleUsername = (e) => {
    setUsername(e.target.value);
  };

  const handlePassword = (e) => {
    setPassword(e.target.value);
  };

  const getProfile = async () => {
    console.log(sessionStorage.getItem("token"));
    return await axios
      .get(ApiContext.getUrl() + "/user", config)
      .then((res) => {
        console.log(res.data);
        setEmail(res.data.email);
        setUsername(res.data.username);
      })
      .catch((err) => {
        console.log(err)
        alert("Error getting profile\n" + err);
        // navigate("/");
      });
  };

  useEffect(() => {
    getProfile();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const handleUpdate = async () => {
    axios
      .put(
        ApiContext.getUrl() + "/user",
        {
          email: email,
          username: username,
          password: password,
        },
        config
      )
      .then((res) => {
        if (res.status !== 200) {
          alert("Error updating profile");
        }
        navigate("/");
      })
      .catch((err) => {
        console.log(err);
        alert("Error updating profile");
      });
  };


  const handleDelete = async () => {
    axios
    .delete(
      ApiContext.getUrl() + "/user",
      config
    )
    .then((res) => {
      if (res.status !== 200) {
        alert("Error deleting profile");
      }
      navigate("/");
    })
    .catch((err) => {
      console.log(err);
      // alert("Error deleting profile");
    });
  }

  return (
    <div className="column">
      <Title text="Create Account" style={{ fontSize: "40px" }} />
      <div className="inputarea">
        <InputTextBox
          id="email"
          text="Email"
          value={email}
          readOnly={true}
        />
        <div className="errorlabel" >Cannot Change Email</div>
        <InputTextBox
          id="username"
          text="Name"
          value={username}
          onChange={handleUsername}
        />
        <InputTextBox
          id="password"
          text="New Password"
          isPassword={true}
          value={password}
          onChange={handlePassword}
        />
      </div>
      <div className="inputarea">
        <NiceButton text="Update Account !" func={() => {handleUpdate()}}/>
        <NiceButton text="Delete Account" func={() => (handleDelete())}/>
        <NiceButton text="Back" func={() => navigate("/books")} />
      </div>
    </div>
  );
}
