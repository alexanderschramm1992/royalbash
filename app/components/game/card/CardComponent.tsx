import * as React from "react";

import "./CardComponent.css";
import "../../common/common.css";

import {Card, SummoningCard} from "../../../model/Game";

export interface CardProps {

    card: Card;
}

export class CardComponent extends React.Component<CardProps, {}> {

    constructor(props: CardProps) {

        super(props);
        this.isSummoningCard = this.isSummoningCard.bind(this)
    }

    isSummoningCard(): boolean {

        let card = this.props.card as SummoningCard;
        return card.strength != null && card.health != null;
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
                    <div className="cost-wrapper">
                        <div className="cost cost-rations">
                            <div className="font-size-extra-large center-text font-border">
                                {this.props.card.costRations}
                            </div>
                        </div>
                        <div className="cost cost-material">
                            <div className="font-size-extra-large center-text font-border">
                                {this.props.card.costMaterial}
                            </div>
                        </div>
                        <div className="cost cost-blessing">
                            <div className="font-size-extra-large center-text font-border">
                                {this.props.card.costBlessing}
                            </div>
                        </div>
                    </div>
                </div>
                <div className="image-wrapper border-small">
                    <img
                        className="image"
                        src={this.props.card.image}
                        alt={this.props.card.name}
                    />
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
                    <div className="type">
                        <div className="font-size-medium">
                            {this.props.card.type}
                            {this.props.card.subType && " - " + this.props.card.subType}
                        </div>
                    </div>
                    {
                        this.isSummoningCard() &&
                        <div className="stats-wrapper">
                            <div className="strength">
                                <div className="font-size-extra-large center-text font-border">
                                    {(this.props.card as SummoningCard).strength}
                                </div>
                            </div>
                            <div className="health border">
                                <div className="font-size-extra-large center-text font-border">
                                    {(this.props.card as SummoningCard).health}
                                </div>
                            </div>
                        </div>
                    }
                </div>
            </div>
        );
    }
}

export default CardComponent;
