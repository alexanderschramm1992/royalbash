import * as React from "react";

import "./Creature.css";

export interface CreatureModel {

    id: string;
    name: string;
    image: string;
    type: string;
    subType: string;
    text: string;
    lore: string;
    cost: number;
    strength: number;
    health: number
}

export class Creature extends React.Component<CreatureModel, {}> {

    render(): any {

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
