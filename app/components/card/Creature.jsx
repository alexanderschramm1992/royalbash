import React from "react";

import "./Creature.css";

class Creature extends React.Component {

    constructor(props) {

        super(props);
        this.state = {
            id: props.creatureModel.id,
            name: props.creatureModel.name,
            image: props.creatureModel.image,
            type: props.creatureModel.type,
            subType: props.creatureModel.subType,
            text: props.creatureModel.text,
            lore: props.creatureModel.lore,
            cost: props.creatureModel.cost,
            strength: props.screatureModel.trength,
            health: props.creatureModel.health
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
                    <div className="text">{this.state.text}</div>
                    <div className="lore">{this.state.lore}</div>
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
