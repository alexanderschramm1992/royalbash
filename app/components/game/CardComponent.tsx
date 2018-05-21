import * as React from "react";

import "./CardComponent.css";
import "../common/common.css";

import {SummoningCard} from "../../model/Game";

export interface CardProps {

    summoningCard: SummoningCard;
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
                            {this.props.summoningCard.name}
                        </div>
                    </div>
                    <div className="cost-wrapper border-small">
                        <div className="cost cost-rations border-small">
                            <div className="font-size-extra-large center-text">
                                {this.props.summoningCard.costRations}
                            </div>
                        </div>
                        <div className="cost cost-material border-small">
                            <div className="font-size-extra-large center-text">
                                {this.props.summoningCard.costMaterial}
                            </div>
                        </div>
                        <div className="cost cost-blessing border-small">
                            <div className="font-size-extra-large center-text">
                                {this.props.summoningCard.costBlessing}
                            </div>
                        </div>
                    </div>
                </div>
                <div className="image-wrapper border-small">
                    <img className="image" src={this.props.summoningCard.image} alt={this.props.summoningCard.name}/>
                </div>
                <div className="type">
                    <div className="font-size-medium">
                        {this.props.summoningCard.type}
                        {this.props.summoningCard.subType && " - " + this.props.summoningCard.subType}
                    </div>
                </div>
                <div className="text-wrapper border-small">
                    <div className="text">
                        <div className="font-size-medium">
                            {this.props.summoningCard.text}
                        </div>
                    </div>
                    <div className="lore">
                        <div className="font-size-medium">
                            {this.props.summoningCard.lore && this.props.summoningCard.lore}
                        </div>
                    </div>
                </div>
                <div className="foot-wrapper">
                    <div className="stats-wrapper border-small">
                        <div className="strength border-small">
                            <div className="font-size-large center-text">
                                {this.props.summoningCard.strength}
                            </div>
                        </div>
                        <div className="health border-small">
                            <div className="font-size-large center-text">
                                {this.props.summoningCard.health}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default CardComponent;
