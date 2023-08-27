import { useState } from "react";
import NiceButton from "../components/Button";
import InputTextBox from "../components/TextInput";
import Title from "../components/Title.js";
import "../assets/styles/Login.css";
import { registrationSchema } from "../models/RegistrationSchema";
import axios from "axios";
import ApiContext from "../assets/api/ApiContext";
import { useNavigate } from "react-router";

function AddUserPage() {
  const navigate = useNavigate();
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");
  const [username, setUsername] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [emailInUse, setEmailInUse] = useState(false);
  const [error, setError] = useState(false);

  const handleEmail = (e) => {
    setEmail(e.target.value);
  };

  const handleUsername = (e) => {
    setUsername(e.target.value);
  };

  const handlePassword = (e) => {
    setPassword(e.target.value);
  };

  const handleConfirmPassword = (e) => {
    setConfirmPassword(e.target.value);
  };

  const validate = async () => {
    let values = {
      email: email,
      username: username,
      password: password,
      confirmPassword: confirmPassword,
    };
    return await registrationSchema.isValid(values);
  };

  const handleSubmit = async () => {
    let values = {
      email: email,
      username: username,
      password: password,
      confirmPassword: confirmPassword,
    };

    if (await validate()) {
      axios
        .post(ApiContext.getUrl() + "/auth/user/register", values)
        .then((res) => {
          if (res.data.error === "Email already exists")
          {
            setEmailInUse(true);
            return;
          }
          
          let token = res.data.token;
          window.sessionStorage.setItem("token", token);
          navigate("/");
        })
        .catch((err) => {
          console.log(err);
          try {
            if (err.response.data.error === "Email already exists")
            setEmailInUse(true);
          } catch (error) {
            // console.log(error);
          }

        });
    } else {
      setError(true);
    }
  };

  return (
    <div className="column">
      <Title text="Create Account" style={{ fontSize: "40px" }} />
      {error ? (
        <p className="errorlabel">
          Please ensure you have filled all fields correctly
        </p>
      ) : (
        <></>
      )}
      <div className="inputarea">
        <InputTextBox
          onChange={handleEmail}
          value={email}
          id="email"
          text="Email"
        />
        {emailInUse ? (
          <label className="errorlabel">Email already in use</label>
        ) : (
          <></>
        )}
        <InputTextBox
          onChange={handleUsername}
          value={username}
          id="username"
          text="Name"
        />
        <InputTextBox
          onChange={handlePassword}
          value={password}
          id="password"
          text="Password"
          isPassword={true}
        />
        <InputTextBox
          onChange={handleConfirmPassword}
          value={confirmPassword}
          id="confirmedPassword"
          text="Confirm Password"
          isPassword={true}
        />
      </div>
      <div className="inputarea">
        <NiceButton text="Create Account !" func={handleSubmit} />
        <NiceButton text="Back" func={() => navigate("/")} />
      </div>
    </div>
  );
}

export default AddUserPage;
