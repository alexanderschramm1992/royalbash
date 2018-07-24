import * as React from "react";

import "../common/common.css";
import "./EndTurnButtonComponent.css";
import store from "../../Store";
import {EndingTurnIssuedActionFactory} from "../../actions/endingturn/EndingTurnIssuedAction";

export class EndTurnButtonComponent extends React.Component<{}, {}> {

    constructor(props: any) {
        super(props);

        this.state = {
        };

        this.endTurn = this.endTurn.bind(this);
    }

    endTurn(): void {
        if(!store.getState().endingTurnIssued) {
            store.dispatch(EndingTurnIssuedActionFactory.getInstance(
                store.getState().gameId,
                store.getState().playerId
            ));
        }
    }

    render(): any {

        return (
            <div className="end-turn-button">
                <button
                    onClick={this.endTurn}
                    disabled={!store.getState().ownTurn}
                    className="button"
                >
                    <div className="font-size-extra-large">End Turn</div>
                </button>
            </div>
        );
    }
}

export default EndTurnButtonComponent;
