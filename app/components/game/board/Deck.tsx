import * as React from "react";

import "./../../common/common.css";
import "./Deck.css";
import DrawCardCall from "../../../rest/DrawCardCall";

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

        this.state.drawCardCall.call();
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
