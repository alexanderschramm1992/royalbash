import * as React from "react";

import "../common/common.css";
import "./HandComponent.css";
import store, {getPlayer} from "../../Store";
import CardMiniContainer from "./CardMiniContainer";
import {Card} from "../../model/Game";

export interface HandState {

    cards: Array<Card>;
}

export class HandComponent extends React.Component<{}, HandState> {

    constructor(props: any) {
        super(props);

        this.state = {
            cards: []
        };

        store.subscribe((): void => {

            this.setState({
                cards: getPlayer().hand.cards
            });
        });
    }

    render(): any {

        return (
            <div className="hand">
                <CardMiniContainer
                    size={5}
                    cards={this.state.cards}
                />
            </div>
        );
    }
}

export default HandComponent;
