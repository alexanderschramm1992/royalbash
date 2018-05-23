import * as React from "react";

import "./CardMini.css";
import "../common/common.css";
import store from "../../Store";
import {MouseOnCardActionFactory} from "../../actions/MouseOnCardAction";
import {MouseOffCardActionFactory} from "../../actions/MouseOffCardAction";
import {CardDraggedActionFactory} from "../../actions/CardDraggedAction";
import {Card, SummoningCard} from "../../model/Game";

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

        console.dir(event);
        store.dispatch(CardDraggedActionFactory.getInstance(this.props.card.id));
    }

    isSummoningCard(): boolean {

        let card = this.props.card as SummoningCard;
        return card.strength != null && card.health != null;
    }

    render(): any {

        return (
            <div
                draggable={true}
                onDragStart={this.handleDragStart}
                className="card-mini border-large"
                onMouseOver={this.handleMouseOver}
                onMouseOut={this.handleMouseOut}
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
                        draggable={false}
                        src={this.props.card.image}
                        alt={this.props.card.name}
                    />
                </div>
                <div className="foot-wrapper">
                    {
                        this.isSummoningCard() &&
                        <div className="stats-wrapper">
                            <div className="strength">
                                <div className="font-size-large center-text font-border">
                                    {(this.props.card as SummoningCard).strength}
                                </div>
                            </div>
                            <div className="health">
                                <div className="font-size-large center-text font-border">
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

export default CardMini;
