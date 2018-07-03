import * as React from "react";

import "../common/common.css";
import "./HandComponent.css";
import store, {getEnemyPlayer, getPlayer} from "../../Store";
import CardMiniContainer, {default as EnemyCardMiniContainer} from "./card/EnemyCardMiniContainer";
import {Card} from "../../model/Game";

export interface EnemyHandState {

    cards: Array<Card>;
}

export class EnemyHandComponent extends React.Component<{}, EnemyHandState> {

    constructor(props: any) {
        super(props);

        this.state = {
            cards: []
        };

        store.subscribe((): void => {

            this.setState({
                cards: getEnemyPlayer().hand.cards
            });
        });
    }

    render(): any {

        return (
            <div className="hand">
                <EnemyCardMiniContainer
                    size={5}
                    cards={this.state.cards}
                />
            </div>
        );
    }
}

export default EnemyHandComponent;
