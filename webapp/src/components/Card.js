import React from "react";
import image from "../images/eager_cadet.png";
import './Card.css';

function Card(props){
    return (
        <div className="Card">
            <header className="Card-header">
                <img src={image} className="Card-image" alt="Eager Cadet"/>
                <h1 className="Card-name">{props.name}</h1>
            </header>
            <p className="Card-text">{props.text}</p>
        </div>
    );
}

export default Card;