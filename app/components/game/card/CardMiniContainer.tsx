import * as React from "react";

import "./CardMiniContainer.css";
import "./../../common/common.css";

import CardMini from "./CardMini";
import {CardModel} from "./Card";
import EventBus from "../../../events/EventBus";
import MouseOnCardEvent from "../../../events/MouseOnCardEvent";

export interface CardMiniContainerProps {

    cards: CardModel[];
    readonly eventBus: EventBus<MouseOnCardEvent>;
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

        return (
            <div className="card-container border-large">
                <div 
                    className="card-placeholder card-placeholder-0 border-large" 
                    onDragOver={this.handleDragOver} 
                    onDrop={this.handleDrop}
                >
                    {this.props.cards[0] &&
                        <CardMini
                            cardModel= {this.props.cards[0]}
                            eventBus={this.props.eventBus}
                        />
                    }
                </div>
                <div className="card-placeholder card-placeholder-1 border-large">
                    {this.props.cards[1] &&
                        <CardMini
                            cardModel= {this.props.cards[1]}
                            eventBus={this.props.eventBus}
                        />
                    }
                </div>
                <div className="card-placeholder card-placeholder-2 border-large">
                    {this.props.cards[2] &&
                        <CardMini
                            cardModel= {this.props.cards[2]}
                            eventBus={this.props.eventBus}
                        />
                    }
                </div>
                <div className="card-placeholder card-placeholder-3 border-large">
                    {this.props.cards[3] &&
                        <CardMini
                            cardModel= {this.props.cards[3]}
                            eventBus={this.props.eventBus}
                        />
                    }
                </div>
                <div className="card-placeholder card-placeholder-4 border-large">
                    {this.props.cards[4] &&
                        <CardMini
                            cardModel= {this.props.cards[4]}
                            eventBus={this.props.eventBus}
                        />
                    }
                </div>
            </div>
        );
    }
}

export default CardMiniContainer;
