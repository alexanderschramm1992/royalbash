import * as React from "react";

import "./Card.css";
import "./../../common/common.css";

export interface CardProps {

    cardModel: CardModel;
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

export class Card extends React.Component<CardProps, {}> {

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
                            {this.props.cardModel.name}
                        </div>
                    </div>
                    <div className="cost-wrapper border-small">
                        <div className="cost cost-rations border-small">
                            <div className="font-size-extra-large center-text">
                                {this.props.cardModel.costRations? this.props.cardModel.costRations : 99}
                            </div>
                        </div>
                        <div className="cost cost-material border-small">
                            <div className="font-size-extra-large center-text">
                                {this.props.cardModel.costMaterial? this.props.cardModel.costMaterial : 99}
                            </div>
                        </div>
                        <div className="cost cost-blessing border-small">
                            <div className="font-size-extra-large center-text">
                                {this.props.cardModel.costBlessing? this.props.cardModel.costBlessing : 99}
                            </div>
                        </div>
                    </div>
                </div>
                <div className="image-wrapper border-small">
                    <img className="image" src={this.props.cardModel.image} alt={this.props.cardModel.name}/>
                </div>
                <div className="type">
                    <div className="font-size-medium">
                        {this.props.cardModel.type}
                        {this.props.cardModel.subType && " - " + this.props.cardModel.subType}
                    </div>
                </div>
                <div className="text-wrapper border-small">
                    <div className="text">
                        <div className="font-size-medium">
                            {this.props.cardModel.text}
                        </div>
                    </div>
                    <div className="lore">
                        <div className="font-size-medium">
                            {this.props.cardModel.lore && this.props.cardModel.lore}
                        </div>
                    </div>
                </div>
                <div className="foot-wrapper">
                    <div className="stats-wrapper border-small">
                        <div className="strength border-small">
                            <div className="font-size-large center-text">
                                {this.props.cardModel.strength}
                            </div>
                        </div>
                        <div className="health border-small">
                            <div className="font-size-large center-text">
                                {this.props.cardModel.health}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Card;
