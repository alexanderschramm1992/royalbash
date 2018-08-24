import * as React from "react";

import "./EnemyCardMiniContainer.css";
import "../../common/common.css";

import {Card} from "../../../model/Game";

export interface CardMiniContainerProps {

    size: number;
    cards: Array<Card>;
}

export class EnemyCardMiniContainer extends React.Component<CardMiniContainerProps, {}> {

    constructor(props: CardMiniContainerProps) {
        super(props);
    }

    render(): any {

        let cardPlaceholders = [];
        for (let i = 0; i < this.props.size; i++) {

            cardPlaceholders.push(
                <div
                    key={i}
                    className={"card-placeholder card-placeholder-" + i + " border-large"}
                >
                    {this.props.cards[i] &&
                    <div className="card-back"/>
                    }
                </div>
            );
        }

        return (
            <div className="enemy-card-container border-large">
                {cardPlaceholders}
            </div>
        );
    }
}

export default EnemyCardMiniContainer;
