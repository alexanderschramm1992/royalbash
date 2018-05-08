import * as React from "react";

import "./../../common/common.css";
import "./HandComponent.css";
import store, {getPlayer} from "../../../Store";
import CardContainer from "../card/CardMiniContainer";
import {Card} from "../../../model/Game";

export interface HandState {

    cards: Card[];
}

export class HandComponent extends React.Component<{}, HandState> {

    constructor(props: any) {
        super(props);

        this.state = {
            cards: []
        };

        store.subscribe((): void => {

            this.setState({
                cards: getPlayer().cards
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

export default HandComponent;
