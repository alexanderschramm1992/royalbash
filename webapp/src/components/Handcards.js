import React from "react";
import Card from "./Card";
import {getChosenPlayer} from "../storeutil";

class Handcards extends React.Component {

    createHandcards(handcards) {

        let style = {
            height: "97%",
            width: "18%",
            padding: "1%",
            display: "inline-block"
        };

        return handcards.map((handcard) =>
            <div className="Handcard-Wrapper" key={handcard.id} style={style}>
                <Card card={handcard} store={this.props.store}/>
            </div>);
    }

    render() {

        let style = {
            height: "20%",
            width: "100%"
        };

        let player = getChosenPlayer(this.props.store);

        return <div className="Handcards" style={style}>
            {player && player.handcards && this.createHandcards(player.handcards)}
        </div>
    }
}

export default Handcards;
