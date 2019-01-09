import React from "react";

class Card extends React.Component {

    constructor(props) {
        super(props);
        this.handleDragStart = this.handleDragStart.bind(this);
    }

    handleDragStart(event) {
        event.dataTransfer.setData("type", "card");
        event.dataTransfer.setData("payload", this.props.card);
        console.log(this.props.card.name + " is being dragged");
    }

    render() {

        const card = this.props.card;

        const style = {
            width: "100%",
            height: "100%",
            background: "lightgray"
        };

        return (
            <div className="Card"
                 draggable={this.props.store.getState().onTurn}
                 style={style}
                 onDragStart={this.handleDragStart}>
                <div className="name">{card.name}</div>
                <div className="text">{card.text}</div>
                <div className="cost">{card.cost}</div>
                <div className="image"><img src={card.image} alt={card.name}/></div>
            </div>
        );
    }
}

export default Card;