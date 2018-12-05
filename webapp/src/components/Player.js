import React from "react";
import {getChosenPlayer} from "../storeutil";

class Player extends React.Component {

    constructor(props) {
        super(props);
        this.handleDrop = this.handleDrop.bind(this);
        this.allowDrop = this.allowDrop.bind(this);
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
                console.log("Something weird has been dropped onto " + getChosenPlayer(this.props.store).name);
        }
    }

    allowDrop(event) {
        event.preventDefault();
    }

    render() {

        let style = {
            border: "1px solid black"
        };

        let player = getChosenPlayer(this.props.store);

        if(player) {

            return <div className="Player" onDrop={this.handleDrop} onDragOver={this.allowDrop} style={style}>
                <div className="Name">player.name</div>
                <img src={player.image} alt={player.name}/>
                <div className="Hitpoints">player.hitpoints</div>
            </div>
        } else {
            return <div/>;
        }
    }
}

export default Player;
