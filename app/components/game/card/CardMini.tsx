import * as React from "react";

import "./CardMini.css";
import "../../common/common.css";

import store from "../../../Store";
import {MouseOnCardActionFactory} from "../../../actions/MouseOnCardAction";
import {MouseOffCardActionFactory} from "../../../actions/MouseOffCardAction";
import {CardDraggedActionFactory} from "../../../actions/CardDraggedAction";
import {Card, SummoningCard} from "../../../model/Game";
import CardMiniView from "./CardMiniView";

export interface CardMiniProps {

    card: Card;
}

export class CardMini extends React.Component<CardMiniProps, {}> {

    constructor(props: CardMiniProps) {

        super(props);
        this.handleMouseOver = this.handleMouseOver.bind(this);
        this.handleMouseOut = this.handleMouseOut.bind(this);
        this.handleDragStart = this.handleDragStart.bind(this);
        this.isSummoningCard = this.isSummoningCard.bind(this);
    }

    handleMouseOver(): void {

        let summoningCardId = this.props.card.id;
        if(summoningCardId) {
            store.dispatch(MouseOnCardActionFactory.getInstance(summoningCardId));
        }
    }

    handleMouseOut(): void {
        store.dispatch(MouseOffCardActionFactory.getInstance());
    }

    handleDragStart(event: any): void {
        store.dispatch(CardDraggedActionFactory.getInstance(this.props.card.id));
    }

    isSummoningCard(): boolean {

        let card = this.props.card as SummoningCard;
        return card.strength != null && card.health != null;
    }

    render(): any {

        return (
            <CardMiniView
                name={this.props.card.name}
                costRations={this.props.card.costRations}
                costMaterial={this.props.card.costMaterial}
                costBlessing={this.props.card.costBlessing}
                image={this.props.card.image}
                strength={(this.props.card as SummoningCard).strength}
                health={(this.props.card as SummoningCard).health}
                handleMouseOver={this.handleMouseOver}
                handleMouseOut={this.handleMouseOut}
                handleDragStart={this.handleDragStart}
                isDraggable={true}
            />
        );
    }
}

export default CardMini;
