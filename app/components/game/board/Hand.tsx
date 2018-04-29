import * as React from "react";
import CardContainer from "../card/CardContainer";
import EventBus from "../../../events/EventBus";
import Observer from "../../../events/Observer";
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

export class Deck
  extends React.Component<HandProps, HandState>
  implements Observer<CardDrawnEvent> {

    constructor(props: HandProps) {
        super(props);

        this.state = {
            cards: []
        };

        props.cardDrawnEventBus.subscribe(this);
    }

    notify(event: CardDrawnEvent): void {

      this.addCard(event.cardModel);
    }

    private addCard(card: CardModel): void {

      let cards = this.state.cards;
      cards.push(card);
      this.setState({
        cards: cards
      });
    }

    render(): any {

        return (
            <div className="hand">
                <CardContainer
                  cards={this.state.cards}
                  eventBus={this.props.mouseOnCardEventBus}
                />
            </div>
        );
    }
}

export default Deck;
