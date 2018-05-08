import * as React from "react";

import "./../../common/common.css";
import "./Deck.css";

import store from "./../../../Store";
import { DrawCardIssuedActionFactory } from "../../../actions/DrawCardIssuedAction";

interface DeckState {

    showSpinner: boolean;
}

export class Deck extends React.Component<{}, DeckState> {

    constructor(props: any) {
        super(props);

        this.state = {
            showSpinner: false
        };

        store.subscribe((): void => {

            this.setState({
                showSpinner: store.getState().drawCardIssued
            });
        });

        this.handleCardDraw = this.handleCardDraw.bind(this);
    }

    handleCardDraw(): void {

        store.dispatch(
            DrawCardIssuedActionFactory.getInstance(
                store.getState().playerId
            )
        );
    }

    render(): any {

        let style: React.CSSProperties = {
            visibility: this.state.showSpinner? "block" : "hidden"
        };

        return (
            <div className="deck">
                <div className="stack" onClick={this.handleCardDraw}>
                    <i className="spinner fa fa-cog fa-spin" style={{style}}></i>
                </div>
            </div>
        );
    }
}

export default Deck;
