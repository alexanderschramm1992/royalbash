import * as React from "react";
import CardContainer from "../card/CardContainer";
import EventBus from "../../../events/EventBus";
import MouseOnCardEvent from "../../../events/MouseOnCardEvent";
import {CardModel} from "../card/Card";
import CardDrawnEvent from "../../../events/CardDrawnEvent";

export interface HandProps {

    readonly cardDrawnEventBus: EventBus<CardDrawnEvent>;
    readonly mouseOnCardEventBus: EventBus<MouseOnCardEvent>;
}

export interface HandState {

    cards: CardModel[];
}

export class Deck extends React.Component<HandProps, HandState> {

    constructor(props: HandProps) {
        super(props);

        this.state = {
            cards: []
        };
    }

    render(): any {

        return (
            <div className="hand">
                <CardContainer cards={this.state.cards} eventBus={this.props.mouseOnCardEventBus}/>
            </div>
        );
    }
}

export default Deck;
