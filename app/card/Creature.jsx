import React from "react";

import "./Creature.css";

class Creature extends React.Component {

    constructor(props) {

        super(props);
        this.state = {
            id: "Creature Card",
            name: "Lorem Ipsum Lorem Ipsum Lorem Ipsum",
            image: "",
            type: "Creature - Lorem Ipsum Lorem",
            cost: "99",
            strength: "99",
            health: "99"
        };
    }

    render() {

        return (
            <div className="creature">
                <div className="head-wrapper">
                    <div className="name">{this.state.name}</div>
                    <div className="cost">{this.state.cost}</div>
                </div>
                <div className="image-wrapper">
                    <img className="image" src={this.state.image} alt={this.state.name}/>
                </div>
                <div className="type">{this.state.type}</div>
                <div className="text-wrapper">
                    <div className="text">N/A</div>
                    <div className="lore">N/A</div>
                </div>
                <div className="foot-wrapper">
                    <div className="strength">{this.state.strength}</div>
                    <div className="health">{this.state.health}</div>
                </div>
            </div>
        );
    }
}

export default Creature;
