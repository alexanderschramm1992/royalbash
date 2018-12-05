import React from "react";
import Card from "./Card";
import {getChosenPlayer} from "../storeutil";

class Handcards extends React.Component {

    createHandcards(handcards) {

        let style = {
            height: "100%",
            width: 100 / handcards.length + "%",
            display: "inline-block"
        };

        return handcards.map((handcard) =>
            <div className="Handcards-Wrapper" key={handcard.id} style={style}>
                <Card card={handcard} store={this.props.store}/>
            </div>);
    }

    render() {

        let player = getChosenPlayer(this.props.store);

        return <div className="Handcards">
            {player && player.handcards && this.createHandcards(player.handcards)}
        </div>
    }
}

export default Handcards;
