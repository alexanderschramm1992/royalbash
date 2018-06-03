import * as React from "react";

import "./CardComponent.css";
import "../../common/common.css";

import {Card, SummoningCard} from "../../../model/Game";
import CardView from "../CardView";

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
            <CardView
                name={this.props.card.name}
                costRations={this.props.card.costRations}
                costMaterial={this.props.card.costMaterial}
                costBlessing={this.props.card.costBlessing}
                image={this.props.card.image}
                text={this.props.card.text}
                lore={this.props.card.lore}
                type={this.props.card.type}
                subType={this.props.card.subType}
                strength={(this.props.card as SummoningCard).strength}
                health={(this.props.card as SummoningCard).health}
            />
        );
    }
}

export default CardComponent;
