import React from "react";
import image from "../images/eager_cadet.png";
import './Card.css';

class Card extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {

        let card = this.props.card;

        let cardStyle = {
            border: "1px solid black"
        };

        return (
            <div className="Card" draggable="true" style={cardStyle}>
                <div className="name">{card.name}</div>
                <div className="text">{card.text}</div>
                <div className="cost">{card.cost}</div>
                <div className="image"><img src={card.image ? card.image : image} alt={card.name}/></div>
            </div>
        );
    }
}

export default Card;