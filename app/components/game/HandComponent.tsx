import * as React from "react";

import "../common/common.css";
import "./HandComponent.css";
import store, {getPlayer} from "../../Store";
import CardMiniContainer from "./CardMiniContainer";
import {SummoningCard} from "../../model/Game";

export interface HandState {

    summoningCards: SummoningCard[];
}

export class HandComponent extends React.Component<{}, HandState> {

    constructor(props: any) {
        super(props);

        this.state = {
            summoningCards: []
        };

        store.subscribe((): void => {

            this.setState({
                summoningCards: getPlayer().hand.summoningCards
            });
        });
    }

    render(): any {

        return (
            <div className="hand">
                <CardMiniContainer
                    size={5}
                    summonongCards={this.state.summoningCards}
                />
            </div>
        );
    }
}

export default HandComponent;
