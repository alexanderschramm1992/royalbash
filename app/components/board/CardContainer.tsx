import * as React from "react";

import "./CardContainer.css";
import {Creature, CreatureModel} from "../card/Creature";

export interface CardContainerModel {

    cards: CreatureModel[]
}

export class CardContainer extends React.Component<CardContainerModel, {}> {

    render(): any {

        return (
            <div className="card-container">
                <div className="card-placeholder card-placeholder-0">
                    {this.props.cards[0] &&
                        <Creature creatureModel = {this.props.cards[0]} />
                    }
                </div>
                <div className="card-placeholder card-placeholder-1">
                    {this.props.cards[1] &&
                        <Creature creatureModel = {this.props.cards[1]} />
                    }
                </div>
                <div className="card-placeholder card-placeholder-2">
                    {this.props.cards[2] &&
                        <Creature creatureModel = {this.props.cards[2]} />
                    }
                </div>
                <div className="card-placeholder card-placeholder-3">
                    {this.props.cards[3] &&
                        <Creature creatureModel = {this.props.cards[3]} />
                    }
                </div>
                <div className="card-placeholder card-placeholder-4">
                    {this.props.cards[4] &&
                        <Creature creatureModel = {this.props.cards[4]} />
                    }
                </div>
            </div>
        );
    }
}

export default CardContainer;
