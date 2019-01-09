import React from "react";
import {getChosenPlayer} from "../storeutil";
import Spot from "./Spot";

class Spots extends React.Component {

    createSpots() {
        const style = {
            height: "97%",
            width: "18%",
            padding: "1%",
            display: "inline-block"
        };

        const spots = this.getSpots();
        return spots
            .filter((spot) => spot !== undefined)
            .map((spot) => <div className="Spot-Wrapper" key={spot.id} style={style}>
                    <Spot id={spot.id} store={this.props.store}/>
                </div>);
    }

    getSpots() {
        const store = this.props.store;
        return store.getState().gameLoaded ? getChosenPlayer(store).spots : [];
    }

    render() {

        const style = {
            height: "20%",
            width: "100%"
        };

        return <div className="Spots" style={style}>
            {this.createSpots()}
        </div>
    }

}

export default Spots;
