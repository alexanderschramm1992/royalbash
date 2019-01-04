import React from "react";
import {getChosenPlayer} from "../storeutil";
import Spot from "./Spot";

class Spots extends React.Component {

    constructor(props) {
        super(props);
    }

    createSpots() {
        let spots = this.getSpots();
        return spots
            .filter((spot) => spot !== undefined)
            .map((spot) => <Spot key={spot.id} id={spot.id} store={this.props.store}/>);
    }

    getSpots() {
        let store = this.props.store;
        return store.getState().gameLoaded ? getChosenPlayer(store).spots : [];
    }

    render() {
        return <div className="Spots">
            {this.createSpots()}
        </div>
    }

}

export default Spots;
