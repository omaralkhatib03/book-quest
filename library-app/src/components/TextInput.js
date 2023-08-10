import "../assets/styles/TextInput.css";

export default function InputTextBox({ text, isPassword, onChange, value, readOnly}) {
  if (isPassword == null) isPassword = false;
  if (readOnly == null) readOnly = false;
  return (
    <div className="form__group field">
      <input className="form__field" type={isPassword ? "password" : ""} onChange={onChange} value={value} placeholder={text} required readOnly={readOnly}/>
      <label className="form__label">{text}</label>
    </div>  
  );
}

