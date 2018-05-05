import * as React from "react";

import "./../../common/common.css";
import "./Board.css";

import CardMiniContainer from "./../card/CardMiniContainer";
import {CardModel} from "../card/Card";
import Slider from "../../menu/Slider";
import EventBus from "../../../events/EventBus";
import MouseOnCardEvent from "../../../events/MouseOnCardEvent";
import DetailView from "./../card/DetailView";
import CardDrawnEvent from "../../../events/CardDrawnEvent";
import Deck from "./Deck";
import Hand from "./Hand";
import DrawCardCall from "../../../rest/DrawCardCall";

const card: CardModel = {
    id: "5d10c3a2-78e5-4463-85e0-57e279cac82c",
    name: "Veteran Knight",
    image: "N/A",
    type: "Knight",
    text: "N/A",
    cost: 4,
    strength: 3,
    health: 4
};

interface BoardState {

    readonly scale: number;
    readonly mouseOnCardEventBus: EventBus<MouseOnCardEvent>;
    readonly cardDrawnEventBus: EventBus<CardDrawnEvent>;
    readonly drawCardCall: DrawCardCall;
}

export class Board extends React.Component<{}, BoardState> {

    constructor(props: any) {
        super(props);

        let mouseOnCardEventBus = new EventBus<MouseOnCardEvent>();
        let cardDrawnEventBus = new EventBus<CardDrawnEvent>();

        this.state = {
            scale: 5,
            mouseOnCardEventBus: mouseOnCardEventBus,
            cardDrawnEventBus: cardDrawnEventBus,
            drawCardCall: new DrawCardCall()
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
            <div className="board border-large border-radius" style={style}>
                <div className="north border-large border-radius">
                    <Slider
                        rangeMin={3}
                        rangeMax={8}
                        step={0.5}
                        startValue={this.state.scale}
                        onValueChange={this.changeScale}
                        label="Scaling"
                    />
                </div>
                <div className="inline-wrapper">
                    <div className="west"></div>
                    <div className="center">
                        <div className="player-area player-area-remote border-large border-radius">
                            <div className="graveyard-area graveyard-area-remote"></div>
                            <div className="summoning-deck-area deck-area-remote"></div>
                            <div className="hand-area hand-area-remote"></div>
                        </div>
                        <div className="play-area border-large border-radius">
                            <div className="remote-summoning-area">
                                <CardMiniContainer
                                    size={7}
                                    cards={[card]}
                                />
                            </div>
                            <div className="summoning-area">
                                <CardMiniContainer
                                    size={7}
                                    cards={[card]}
                                />
                            </div>
                        </div>
                        <div className="player-area border-large border-radius">
                            <div className="graveyard-area">
                                <Deck/>
                            </div>
                            <div className="summoning-deck-area">
                                <Deck/>
                            </div>
                            <div className="resources-deck-area">
                                <Deck/>
                            </div>
                            <div className="hand-area">
                                <Hand/>
                            </div>
                        </div>
                    </div>
                    <div className="east border-large border-radius">
                        <div className="card-preview-area">
                            <DetailView scale={this.state.scale * 1.5}/>
                        </div>
                        <div className="log-area"></div>
                    </div>
                </div>
                <div className="south border-large border-radius"></div>
            </div>
        );
    }
}

export default Board;
