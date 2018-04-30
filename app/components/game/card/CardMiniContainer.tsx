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

    render(): any {

        return (
            <div className="card-container border-large">
                <div className="card-placeholder card-placeholder-0 border-large">
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
