import * as React from "react";

import "./../../common/common.css";
import "./DeckComponent.css";

import store from "../../../Store";
import { DrawSummoningCardIssuedActionFactory } from "../../../actions/DrawSummoningCardIssuedAction";


export enum DeckComponentType {

    SUMMONING_DECK,
    RESOUCRES_DECK
}

export interface DeckComponentProps {

    readonly type: DeckComponentType;
}

interface DeckState {

    showSpinner: boolean;
}

export class DeckComponent extends React.Component<DeckComponentProps, DeckState> {

    constructor(props: DeckComponentProps) {
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

        let imageClass = this.props.type == DeckComponentType.SUMMONING_DECK? "summoning-deck" : "resource-deck";

        let style: React.CSSProperties = {
            visibility: this.state.showSpinner? "visible" : "hidden"
        };

        return (
            <div className="deck">
                <div
                    className={"stack border-large border-radius " + imageClass}
                    onClick={this.handleCardDraw}
                >
                    <i className="spinner fa fa-cog fa-spin" style={style}/>
                </div>
            </div>
        );
    }
}

export default DeckComponent;
