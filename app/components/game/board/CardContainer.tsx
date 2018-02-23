import * as React from "react";

import "./CardContainer.css";
import "./../common.css";

import {Creature, CreatureModel} from "../card/Creature";

export interface CardContainerProps {

    cards: CreatureModel[];
    readonly scale?: number;
}

export class CardContainer extends React.Component<CardContainerProps, {}> {

    render(): any {

        let style = {
            fontSize: this.props.scale ? (this.props.scale * 10) + "px" : null
        };

        return (
            <div className="card-container border-large" style={style}>
                <div className="card-placeholder card-placeholder-0 border-large">
                    {this.props.cards[0] &&
                        <Creature
                            creatureModel = {this.props.cards[0]}
                            scale = {this.props.scale}
                        />
                    }
                </div>
                <div className="card-placeholder card-placeholder-1 border-large">
                    {this.props.cards[1] &&
                        <Creature
                            creatureModel = {this.props.cards[1]}
                            scale = {this.props.scale}
                        />
                    }
                </div>
                <div className="card-placeholder card-placeholder-2 border-large">
                    {this.props.cards[2] &&
                        <Creature
                            creatureModel = {this.props.cards[2]}
                            scale = {this.props.scale}
                        />
                    }
                </div>
                <div className="card-placeholder card-placeholder-3 border-large">
                    {this.props.cards[3] &&
                        <Creature
                            creatureModel = {this.props.cards[3]}
                            scale = {this.props.scale}
                        />
                    }
                </div>
                <div className="card-placeholder card-placeholder-4 border-large">
                    {this.props.cards[4] &&
                        <Creature
                            creatureModel = {this.props.cards[4]}
                            scale = {this.props.scale}
                        />
                    }
                </div>
            </div>
        );
    }
}

export default CardContainer;
