import * as React from "react";

import "./../common.css";
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

export interface BoardProps {

    readonly scale?: number;
}

export class Board extends React.Component<BoardProps, {}> {

    render(): any {

        let style = {
            fontSize: this.props.scale ? (this.props.scale * 10) + "px" : null
        };

        return (
            <div className="board" style = {style}>
                <div className="board-side board-side-top">
                    <div className="avatar-area">
                    </div>
                    <div className="hand-area">
                        <CardContainer cards = {[card]} scale = {this.props.scale}/>
                    </div>
                    <div className="deck-area">
                    </div>
                    <div className="play-area">
                        <div className="upper-row">
                            <CardContainer cards = {[card]} scale = {this.props.scale}/>
                        </div>
                        <div className="lower-row">
                            <CardContainer cards = {[]} scale = {this.props.scale}/>
                        </div>
                    </div>
                    <div className="graveyard-area">
                    </div>
                </div>
                <div className="board-side board-side-bottom">
                    <div className="avatar-area">
                    </div>
                    <div className="hand-area">
                        <CardContainer cards = {[]} scale = {this.props.scale}/>
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
