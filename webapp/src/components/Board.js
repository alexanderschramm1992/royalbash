import React from "react";
import {getSpots, getHandcards, getChosenPlayer} from "../storeutil";

class Board extends React.Component {

    constructor(props) {
        super();
        this.state = {store: props.store};
        props.store.subscribe(() => this.setState({game: props.store.getState().game}));
        this.handleDrawCardButton = this.handleDrawCardButton.bind(this);
        this.handleEndTurnButton = this.handleEndTurnButton.bind(this);
    }

    handleEndTurnButton() {
        this.state.store.dispatch({type: "END_TURN_REQUESTED", player: this.state.store.getState().chosenPlayer});
    }

    handleDrawCardButton() {
        this.state.store.dispatch({type: "DRAW_CARD_REQUESTED", player: this.state.store.getState().chosenPlayer});
    }

    createSpots() {
        let spots = getSpots(this.state.store);
        return spots.map((spot) => <div key={spot.id} className="Spot">
            {spot.creature &&
            <div key={spot.creature.id} className="Creature">
                <div className="name">{spot.creature.name}</div>
                <div className="cost">{spot.creature.cost}</div>
                <div className="attack">{spot.creature.attack}</div>
                <div className="hitpoints">{spot.creature.hitpoints}</div>
            </div>
            }
        </div> );
    }

    createHandcards() {
        let cardStyle = {
            border: "1px solid black"
        };
        let handcards = getHandcards(this.state.store);
        return handcards.map((card) => <div key={card.id} className="Card" style={cardStyle}>
            <div className="name">{card.name}</div>
            <div className="text">{card.text}</div>
            <div className="cost">{card.cost}</div>
        </div>);
    }

    render() {
        let player = getChosenPlayer(this.state.store);
        return (
            <div className="Board">
                <div className="Spots">
                    {this.createSpots()}
                </div>
                <div className="Handcards">
                    {this.createHandcards()}
                </div>
                <div className="Deckcards">
                    <button onClick={this.handleDrawCardButton}>
                        Draw
                    </button>
                </div>
                <div className="Hitpoints">
                    {player ? player.hitpoints : 0}
                </div>
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
