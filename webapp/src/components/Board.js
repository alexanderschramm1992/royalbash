import React from "react";
import Player from "./Player";
import Handcards from "./Handcards";
import Spots from "./Spots";

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

    render() {

        let style = {
            height: "800px",
            width: "1280px",
            display: "inline-block"
        };

        return (
            <div className="Board" style={style}>
                <Spots store={this.props.store}/>
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
