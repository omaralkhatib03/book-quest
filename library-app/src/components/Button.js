import "../assets/styles/Button.css";

function NiceButton({ text, func, style }) {
  return (
    <button className="button" onClick={func} style={style}>
      {text}
    </button>
  );
}

export default NiceButton;
