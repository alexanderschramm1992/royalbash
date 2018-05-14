import * as React from "react";

import "./CardMiniContainer.css";
import "./../../common/common.css";

import CardMini from "./CardMini";
import {SummoningCard} from "../../../model/Game";

export interface CardMiniContainerProps {

    size: number;
    summonongCards: SummoningCard[];
}

export class CardMiniContainer extends React.Component<CardMiniContainerProps, {}> {

    constructor(props: CardMiniContainerProps) {
        super(props);

        this.handleDrop = this.handleDrop.bind(this);
        this.handleDragOver = this.handleDragOver.bind(this);
    }

    handleDrop(event: any): any {

        console.log("We are handeling the drop, bitches!");
        event.preventDefault();
        let summoningCard = event.dataTransfer.getData("cardModel") as SummoningCard;
        this.props.summonongCards.push(summoningCard);
    }

    handleDragOver(event: any): void {

        console.log("Watch out, something is coming from above!");
        event.preventDefault();
    }

    render(): any {

        let cardPlaceholders = [];
        for (let i = 0; i < this.props.size; i++) {

            cardPlaceholders.push(
                <div
                    key={i}
                    className={"card-placeholder card-placeholder-" + i + " border-large"}
                    onDragOver={this.handleDragOver}
                    onDrop={this.handleDrop}
                >
                    {this.props.summonongCards[i] &&
                    <CardMini summoningCard= {this.props.summonongCards[i]} />
                    }
                </div>
            );
        }

        return (
            <div className="card-container border-large">
                {cardPlaceholders}
            </div>
        );
    }
}

export default CardMiniContainer;
