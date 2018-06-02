import * as React from "react";

import "../../common/common.css";
import "./SummoningDeckComponent.css";

import store from "../../../Store";
import { DrawSummoningCardIssuedActionFactory } from "../../../actions/DrawSummoningCardIssuedAction";

interface DeckState {

    showSpinner: boolean;
}

export class SummoningDeckComponent extends React.Component<{}, DeckState> {

    constructor(props: any) {
        super(props);

        this.state = {
            showSpinner: false
        };

        store.subscribe((): void => {

            this.setState({
                showSpinner: store.getState().drawSummoningCardIssued
            });
        });

        this.handleCardDraw = this.handleCardDraw.bind(this);
    }

    handleCardDraw(): void {

        store.dispatch(
            DrawSummoningCardIssuedActionFactory.getInstance(
                store.getState().playerId
            )
        );
    }

    render(): any {

        let style: React.CSSProperties = {
            visibility: this.state.showSpinner? "visible" : "hidden"
        };

        return (
            <div className="summoning-deck">
                <div
                    className="stack border-large border-radius"
                    onClick={this.handleCardDraw}
                >
                    <p className="font-size-large">Summoning Deck</p>
                    <i className="spinner fa fa-cog fa-spin" style={style}/>
                </div>
            </div>
        );
    }
}

export default SummoningDeckComponent;
