import * as React from "react";

import "../common/common.css";
import "./ResourcesDeckComponent.css";

import store from "../../Store";
import {DrawResourcesCardIssuedActionFactory} from "../../actions/DrawResourcesCardIssuedAction";

interface DeckState {

    showSpinner: boolean;
}

export class ResourcesDeckComponent extends React.Component<{}, DeckState> {

    constructor(props: any) {
        super(props);

        this.state = {
            showSpinner: false
        };

        store.subscribe((): void => {

            this.setState({
                showSpinner: store.getState().drawResourcesCardIssued
            });
        });

        this.handleCardDraw = this.handleCardDraw.bind(this);
    }

    handleCardDraw(): void {

        store.dispatch(
            DrawResourcesCardIssuedActionFactory.getInstance(
                store.getState().playerId
            )
        );
    }

    render(): any {

        let style: React.CSSProperties = {
            visibility: this.state.showSpinner? "visible" : "hidden"
        };

        return (
            <div className="resources-deck">
                <div
                    className="stack border-large border-radius"
                    onClick={this.handleCardDraw}
                >
                    <p className="font-size-large">Resources Deck</p>
                    <i className="spinner fa fa-cog fa-spin" style={style}/>
                </div>
            </div>
        );
    }
}

export default ResourcesDeckComponent;
