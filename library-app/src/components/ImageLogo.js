import "../assets/styles/ImageLogo.css";
import Title from "./Title";

function LogoTitle() {
  return (
    <>
      <img id="logo" src={require("../assets/images/logo.jpg")} alt="" />
      <Title text="Book Quest"/>
    </>
  );
}

export default LogoTitle;
