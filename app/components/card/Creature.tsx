import * as React from "react";

import "./Creature.css";

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

interface CreatureState {

    readonly scale: number;
}

export class Creature extends React.Component<CreatureProps, CreatureState> {

    constructor(props: CreatureProps) {
        super(props);

        this.state = {
            scale: props.scale ? props.scale : 1
        };
    }

    render(): any {

        let style = {
          fontSize: this.state.scale * 10
        };

        return (
            <div className="creature border-large" style={style}>
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
