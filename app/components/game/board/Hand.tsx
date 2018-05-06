import * as React from "react";
import store, {findCardModelById} from "../../../Store";
import CardContainer from "../card/CardMiniContainer";
import {CardModel} from "../card/Card";

export interface HandState {

    cards: CardModel[];
}

export class Deck extends React.Component<{}, HandState> {

    constructor() {
        super({});

        this.state = {
            cards: []
        };

        store.subscribe((): void => {

            this.setState({
                cards: store.getState().hand.map(findCardModelById)
            });
        });
    }



    render(): any {

        return (
            <div className="hand">
                <CardContainer
                    size={5}
                    cards={this.state.cards}
                />
            </div>
        );
    }
}

export default Deck;
