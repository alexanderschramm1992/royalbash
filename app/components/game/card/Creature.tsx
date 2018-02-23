import * as React from "react";

import "./Creature.css";
import "./../../common.css";

export interface CreatureProps {

    creatureModel: CreatureModel;
    readonly scale?: number;
}

export interface CreatureModel {

    readonly id: string;
    readonly name: string;
    readonly image: string;
    readonly type: string;
    readonly subType?: string;
    readonly text: string;
    readonly lore?: string;
    readonly cost: number;
    strength: number;
    health: number;
}

export class Creature extends React.Component<CreatureProps, {}> {

    render(): any {

        let style = {
            fontSize: this.props.scale ? (this.props.scale * 10) + "px" : null
        };

        return (
            <div className="creature border-large" style={this.props.scale ? style : null}>
                <div className="head-wrapper">
                    <div className="name">
                        <div className="font-size-large">
                            {this.props.creatureModel.name}
                        </div>
                    </div>
                    <div className="cost border-small">
                        <div className="font-size-extra-large center-text">
                            {this.props.creatureModel.cost}
                        </div>
                    </div>
                </div>
                <div className="image-wrapper border-small">
                    <img className="image" src={this.props.creatureModel.image} alt={this.props.creatureModel.name}/>
                </div>
                <div className="type">
                    <div className="font-size-medium">
                        {this.props.creatureModel.type}
                        {this.props.creatureModel.subType && " - " + this.props.creatureModel.subType}
                    </div>
                </div>
                <div className="text-wrapper border-small">
                    <div className="text">
                        <div className="font-size-medium">
                            {this.props.creatureModel.text}
                        </div>
                    </div>
                    <div className="lore">
                        <div className="font-size-medium">
                            {this.props.creatureModel.lore && this.props.creatureModel.lore}
                        </div>
                    </div>
                </div>
                <div className="foot-wrapper">
                    <div className="strength border-small">
                        <div className="font-size-large center-text">
                            {this.props.creatureModel.strength}
                        </div>
                    </div>
                    <div className="health border-small">
                        <div className="font-size-large center-text">
                            {this.props.creatureModel.health}
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Creature;
