import React from "react";
import {getSpots, getHandcards} from "../storeutil";
import Card from "./Card";
import Player from "./Player";
import Spot from "./Spot";
import Handcards from "./Handcards";

class Board extends React.Component {

    constructor(props) {
        super(props);
        this.handleDrawCardButton = this.handleDrawCardButton.bind(this);
        this.handleEndTurnButton = this.handleEndTurnButton.bind(this);
    }

    handleEndTurnButton() {
        this.props.store.dispatch({type: "END_TURN_REQUESTED", player: this.props.store.getState().chosenPlayer});
    }

    handleDrawCardButton() {
        this.props.store.dispatch({type: "DRAW_CARD_REQUESTED", player: this.props.store.getState().chosenPlayer});
    }

    createSpots() {
        let spots = getSpots(this.props.store);
        return spots.map((spot) => <Spot key={spot.id} store={this.props.store}/>);
    }

    createHandcards() {

        let handcards = getHandcards(this.props.store);

        let cardStyle = {
            width: 100/handcards.length + "%",
            height: "100%",
            display: "inline-block"
        };

        return handcards.map((card) => <Card
            key={card.id}
            card={card}
            store={this.props.store}
            style={cardStyle}
        />);
    }

    render() {
        return (
            <div className="Board">
                <div className="Spots">
                    {this.createSpots()}
                </div>
                <Handcards store={this.props.store}/>
                <div className="Deckcards">
                    <button onClick={this.handleDrawCardButton}>
                        Draw
                    </button>
                </div>
                <Player store={this.props.store}/>
                <div className="EndTurn">
                    <button onClick={this.handleEndTurnButton}>
                        End Turn
                    </button>
                </div>
            </div>
        )
    }
}

export default Board;
