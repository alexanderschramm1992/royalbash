import * as React from "react";

import "./../../common.css";
import "./Board.css";

import CardContainer from "./CardContainer";
import {CreatureModel} from "../card/Creature";
import Slider from "../../menu/Slider";

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

interface BoardState {

    readonly scale: number;
}

export class Board extends React.Component<{}, BoardState> {

    constructor(props: any) {
        super(props);

        this.state = {
            scale: 1
        };

        this.changeScale = this.changeScale.bind(this);
    }

    private changeScale(value: number): void {

        this.setState({
            scale: value
        });
    }

    render(): any {

        let style = {
            fontSize: this.state.scale + "px"
        };

        return (
            <div className="board" style = {style}>
                <div className="board-head">
                    <Slider
                        rangeMin={0.1}
                        rangeMax={1.5}
                        step={0.1}
                        startValue={this.state.scale}
                        onValueChange={this.changeScale}
                        label="Scaling"
                    />
                </div>
                <div className="board-side board-side-top">
                    <div className="avatar-area">
                    </div>
                    <div className="hand-area">
                        <CardContainer cards = {[card]} scale = {this.state.scale}/>
                    </div>
                    <div className="deck-area">
                    </div>
                    <div className="play-area">
                        <CardContainer cards = {[card]} scale = {this.state.scale}/>
                    </div>
                    <div className="graveyard-area">
                    </div>
                </div>
                <div className="board-side board-side-bottom">
                    <div className="avatar-area">
                    </div>
                    <div className="hand-area">
                        <CardContainer cards = {[card]} scale = {this.state.scale}/>
                    </div>
                    <div className="deck-area">
                    </div>
                    <div className="play-area">
                        <CardContainer cards = {[card]} scale = {this.state.scale}/>
                    </div>
                    <div className="graveyard-area">
                    </div>
                </div>
            </div>
        );
    }
}

export default Board;
