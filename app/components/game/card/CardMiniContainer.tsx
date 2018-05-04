import * as React from "react";

import "./CardMiniContainer.css";
import "./../../common/common.css";

import CardMini from "./CardMini";
import {CardModel} from "./Card";

export interface CardMiniContainerProps {

    size: number;
    cards: CardModel[];
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
        let cardModel = event.dataTransfer.getData("cardModel") as CardModel;
        console.dir(event);
        console.dir(cardModel);
        console.dir(event.dataTransfer.getData("test"));
        this.props.cards.push(cardModel);
        console.dir(this.props.cards);
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
                    className={"card-placeholder card-placeholder-" + i + " border-large"}
                    onDragOver={this.handleDragOver}
                    onDrop={this.handleDrop}
                >
                    {this.props.cards[i] &&
                    <CardMini cardModel= {this.props.cards[i]} />
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
