import * as React from "react";
import "../common/common.css";
import "./EndTurnButtonComponent.css";
import {
    default as EndingTurnIssuedAction,
    EndingTurnIssuedActionFactory
} from "../../actions/endingturn/EndingTurnIssuedAction";

export interface EndTurnButtonComponentProps {
    store: {
        getState: () => {
            endingTurnIssued: boolean,
            ownTurn: boolean,
            gameId: string,
            playerId: string
        },
        dispatch: (action: EndingTurnIssuedAction) => void
    }
}

export class EndTurnButtonComponent extends React.Component<EndTurnButtonComponentProps, {}> {

    constructor(props: EndTurnButtonComponentProps) {
        super(props);

        this.state = {};

        this.endTurn = this.endTurn.bind(this);
    }

    endTurn(): void {

        let store = this.props.store;

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
                    disabled={!this.props.store.getState().ownTurn}
                    className="button"
                >
                    <div className="font-size-extra-large">End Turn</div>
                </button>
            </div>
        );
    }
}

export default EndTurnButtonComponent;
