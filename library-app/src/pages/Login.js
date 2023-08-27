import { useNavigate } from "react-router";
import NiceButton from "../components/Button";
import LogoTitle from "../components/ImageLogo";
import InputTextBox from "../components/TextInput";
import "../assets/styles/Login.css";
import axios from "axios";
import ApiContext from "../assets/api/ApiContext";
import { useState } from "react";
import { loginSchema } from "../models/LoginSchema";

function LoginPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(false); // TODO: Implement error handling
  const navigate = useNavigate();

  const validate = async () => {
    let values = {
      email: email,
      password: password,
    };

    return await loginSchema.isValid(values);
  };

  const handleEmail = (e) => {
    setEmail(e.target.value);
  };

  const handlePassword = (e) => {
    setPassword(e.target.value);
  };

  const navigateAdduser = () => {
    navigate("/addUser");
  };

  const handleLogin = async () => {
    let values = {
      email: email,
      password: password,
    };

    if (await validate()) {
      console.log(ApiContext.getUrl());
      axios
        .post(ApiContext.getUrl() + "/auth/user/login", values)
        .then((res) => {
          if (res.status !== 200) {
            setError(true);
            return;
          }
          let token = res.data.token;
          if (token != null) {
            window.sessionStorage.setItem("token", token);
            navigate("/books");
          } else {
            setError(true);
          }
        })
        .catch((err) => setError(true));
    } else {
      setError(true);
    }
  };

  return (
    <div className="column">
      <LogoTitle />
      {error ? (
        <p className="errorlabel">
          Please ensure you have filled all fields correctly
        </p>
      ) : (
        <></>
      )}
      <div className="inputarea">
        <InputTextBox onChange={handleEmail} value={email} text={"Email"} />
        <InputTextBox
          onChange={handlePassword}
          value={password}
          text={"Password"}
          isPassword={true}
        />
      </div>
      <div className="inputarea">
        <NiceButton text={"Log in"} func={handleLogin} />
        <NiceButton text={"Create Account"} func={navigateAdduser}></NiceButton>
      </div>
    </div>
  );
}

export default LoginPage;
