import * as React from "react";

import "./Board.css";
import CardContainer from "./CardContainer";
import {CreatureModel} from "../card/Creature";

const card: CreatureModel = {
    id: "1",
    name: "Lorem Ipsum",
    image: "N/A",
    type: "Creature",
    text: "N/A",
    cost: 99,
    strength: 99,
    health: 99
};

class Board extends React.Component<{}, {}> {

    constructor(props: any) {

        super(props);
        this.state = {};
    }

    render(): any {

        return (
            <div className="board">
                <div className="board-side board-side-top">
                    <div className="avatar-area">
                    </div>
                    <div className="hand-area">
                        <CardContainer cards = {[card]} />
                    </div>
                    <div className="deck-area">
                    </div>
                    <div className="play-area">
                        <div className="upper-row">
                            <CardContainer cards = {[card]} />
                        </div>
                        <div className="lower-row">
                            <CardContainer cards = {[]} />
                        </div>
                    </div>
                    <div className="graveyard-area">
                    </div>
                </div>
                <div className="board-side board-side-bottom">
                    <div className="avatar-area">
                    </div>
                    <div className="hand-area">
                        <CardContainer cards = {[]} />
                    </div>
                    <div className="deck-area">
                    </div>
                    <div className="play-area">
                    </div>
                    <div className="graveyard-area">
                    </div>
                </div>
            </div>
        );
    }
}

export default Board;
