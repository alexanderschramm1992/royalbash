import React from "react";
import {getSpots, getHandcards, getChosenPlayer} from "../storeutil";
import Card from "./Card";
import Player from "./Player";

class Board extends React.Component {

    constructor(props) {
        super();
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
        let handcards = getHandcards(this.props.store);
        return handcards.map((card) => <Card key={card.id} card={card} store={this.props.store}/>);
    }

    render() {
        let player = getChosenPlayer(this.props.store);
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
                <Player/>
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
