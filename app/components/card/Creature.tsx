import * as React from "react";

import "./Creature.css";
import CreatureModel from "../../model/CreatureModel";

class Creature extends React.Component<CreatureModel, {}> {

    render() {

        return (
            <div className="creature">
                <div className="head-wrapper">
                    <div className="name">{this.props.name}</div>
                    <div className="cost">{this.props.cost}</div>
                </div>
                <div className="image-wrapper">
                    <img className="image" src={this.props.image} alt={this.props.name}/>
                </div>
                <div className="type">{this.props.type}</div>
                <div className="text-wrapper">
                    <div className="text">{this.props.text}</div>
                    <div className="lore">{this.props.lore}</div>
                </div>
                <div className="foot-wrapper">
                    <div className="strength">{this.props.strength}</div>
                    <div className="health">{this.props.health}</div>
                </div>
            </div>
        );
    }
}

export default Creature;
