import * as React from "react";

import "./CardContainer.css";
import "./../../common/common.css";

import {Card, CardModel} from "./Card";
import EventBus from "../../../events/EventBus";
import MouseOnCardEvent from "../../../events/MouseOnCardEvent";

export interface CardContainerProps {

    cards: CardModel[];
    readonly eventBus: EventBus<MouseOnCardEvent>;
}

export class CardContainer extends React.Component<CardContainerProps, {}> {

    render(): any {

        return (
            <div className="card-container border-large">
                <div className="card-placeholder card-placeholder-0 border-large">
                    {this.props.cards[0] &&
                        <Card
                            cardModel= {this.props.cards[0]}
                            eventBus={this.props.eventBus}
                        />
                    }
                </div>
                <div className="card-placeholder card-placeholder-1 border-large">
                    {this.props.cards[1] &&
                        <Card
                            cardModel= {this.props.cards[1]}
                            eventBus={this.props.eventBus}
                        />
                    }
                </div>
                <div className="card-placeholder card-placeholder-2 border-large">
                    {this.props.cards[2] &&
                        <Card
                            cardModel= {this.props.cards[2]}
                            eventBus={this.props.eventBus}
                        />
                    }
                </div>
                <div className="card-placeholder card-placeholder-3 border-large">
                    {this.props.cards[3] &&
                        <Card
                            cardModel= {this.props.cards[3]}
                            eventBus={this.props.eventBus}
                        />
                    }
                </div>
                <div className="card-placeholder card-placeholder-4 border-large">
                    {this.props.cards[4] &&
                        <Card
                            cardModel= {this.props.cards[4]}
                            eventBus={this.props.eventBus}
                        />
                    }
                </div>
            </div>
        );
    }
}

export default CardContainer;
