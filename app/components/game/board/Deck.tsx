import * as React from "react";

import "./../../common/common.css";
import "./Deck.css";

import DrawCardCall from "./../../../rest/DrawCardCall";
import store from "./../../../Store";
import DrawCardIssuedAction from "../../../actions/DrawCardIssuedAction";

export interface DeckProps {

    readonly drawCardCall: DrawCardCall;
}

export interface DeckState {

    readonly drawCardCall: DrawCardCall;
}

export class Deck extends React.Component<DeckProps, DeckState> {


    constructor(props: DeckProps) {
        super(props);

        this.state = {
            drawCardCall: props.drawCardCall
        };

        this.handleCardDraw = this.handleCardDraw.bind(this);
    }

    handleCardDraw(): void {

        store.dispatch(
            new DrawCardIssuedAction(
                store.getState().playerId
            )
        );
    }

    render(): any {

        return (
            <div className="deck">
                <div className="stack" onClick={this.handleCardDraw}>

                </div>
            </div>
        );
    }
}
export default Deck;
