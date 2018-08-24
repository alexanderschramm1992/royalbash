import * as React from "react";

import "./CardMini.css";
import "../../common/common.css";

import store from "../../../Store";
import {MouseOnCardActionFactory} from "../../../actions/hovering/MouseOnCardAction";
import {MouseOffCardActionFactory} from "../../../actions/hovering/MouseOffCardAction";
import {Card, SummoningCard} from "../../../model/Game";
import CardMiniView from "./CardMiniView";
import {SummoningCardDraggedActionFactory} from "../../../actions/summoning/SummoningCardDraggedAction";
import {ResourcesCardDraggedActionFactory} from "../../../actions/gatheringresources/ResourcesCardDraggedAction";

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

        if(this.isSummoningCard()) {
            store.dispatch(SummoningCardDraggedActionFactory.getInstance(this.props.card.id));
        } else if(this.isResourcesCard()) {
            store.dispatch(ResourcesCardDraggedActionFactory.getInstance(this.props.card.id));
        }
    }

    isSummoningCard(): boolean {
        return this.props.card.cardType == "Summoning";
    }

    isResourcesCard(): boolean {
        return this.props.card.cardType == "Resources";
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
                isDraggable={store.getState().ownTurn}
            />
        );
    }
}

export default CardMini;
