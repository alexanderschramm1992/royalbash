import React from "react";
import {getChosenPlayer} from "../storeutil";

class Player extends React.Component {

    constructor(props) {
        super(props);
        this.handleDrop = this.handleDrop.bind(this);
    }

    handleDrop(event) {
        switch(event.dataTransfer.getData("type")) {
            case "card":
                this.props.store.dispatch({
                    type: "PLAY_CARD_ON_PLAYER_REQUESTED",
                    cardId: event.dataTransfer.getData("payload").id,
                    ownerId: getChosenPlayer(this.props.store).id,
                    playerId: this.props.id
                });
                break;
            default:
                console.log("Something weird was dropped on player: " + event);
        }
    }

    render() {
        return <div className="Player" onDrop={this.handleDrop}/>
    }
}

export default Player;
