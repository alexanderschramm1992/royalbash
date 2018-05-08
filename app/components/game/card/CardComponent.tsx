import * as React from "react";

import "./CardComponent.css";
import "./../../common/common.css";

import {Card} from "../../../model/Game";

export interface CardProps {

    card: Card;
}

export interface CardModel {

    readonly id: string;
    readonly name: string;
    readonly image: string;
    readonly type: string;
    readonly subType?: string;
    readonly text: string;
    readonly lore?: string;
    readonly cost: number;
    readonly costRations?: number;
    readonly costMaterial?: number;
    readonly costBlessing?: number;
    strength: number;
    health: number;
}

export class CardComponent extends React.Component<CardProps, {}> {

    constructor(props: CardProps) {

        super(props);
    }

    render(): any {

        return (
            <div
                draggable={true}
                className="card border-large border-radius"
            >
                <div className="head-wrapper">
                    <div className="name">
                        <div className="font-size-large">
                            {this.props.card.name}
                        </div>
                    </div>
                    <div className="cost-wrapper border-small">
                        <div className="cost cost-rations border-small">
                            <div className="font-size-extra-large center-text">
                                {this.props.card.costRations? this.props.card.costRations : 99}
                            </div>
                        </div>
                        <div className="cost cost-material border-small">
                            <div className="font-size-extra-large center-text">
                                {this.props.card.costMaterial? this.props.card.costMaterial : 99}
                            </div>
                        </div>
                        <div className="cost cost-blessing border-small">
                            <div className="font-size-extra-large center-text">
                                {this.props.card.costBlessing? this.props.card.costBlessing : 99}
                            </div>
                        </div>
                    </div>
                </div>
                <div className="image-wrapper border-small">
                    <img className="image" src={this.props.card.image} alt={this.props.card.name}/>
                </div>
                <div className="type">
                    <div className="font-size-medium">
                        {this.props.card.type}
                        {this.props.card.subType && " - " + this.props.card.subType}
                    </div>
                </div>
                <div className="text-wrapper border-small">
                    <div className="text">
                        <div className="font-size-medium">
                            {this.props.card.text}
                        </div>
                    </div>
                    <div className="lore">
                        <div className="font-size-medium">
                            {this.props.card.lore && this.props.card.lore}
                        </div>
                    </div>
                </div>
                <div className="foot-wrapper">
                    <div className="stats-wrapper border-small">
                        <div className="strength border-small">
                            <div className="font-size-large center-text">
                                {this.props.card.strength}
                            </div>
                        </div>
                        <div className="health border-small">
                            <div className="font-size-large center-text">
                                {this.props.card.health}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default CardComponent;
