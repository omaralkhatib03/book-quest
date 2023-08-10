import '../assets/styles/Title.css';
export default function Title ({text, style}) { // only use styles to override when needed
    return <span className="Title" style={style}> {text} </span>;
}