import React from "react";

import "./Creature.css";

class Creature extends React.Component {

    constructor(props) {

        super(props);
        this.state = {
            id: props.id,
            name: props.name,
            image: props.image,
            type: props.type,
            subType: props.subType,
            cost: props.cost,
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
