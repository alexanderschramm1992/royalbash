import * as React from "react";

import "./../../common/common.css";
import "./Board.css";

import CardContainer from "./../card/CardContainer";
import {CardModel} from "../card/Card";
import Slider from "../../menu/Slider";
import EventBus from "../../../events/EventBus";
import MouseOnCardEvent from "../../../events/MouseOnCardEvent";
import CardPreview from "./../card/CardPreview";
import CardDrawnEvent from "../../../events/CardDrawnEvent";
import Deck from "./Deck";
import Hand from "./Hand";
import DrawCardCall from "../../../rest/DrawCardCall";

const card: CardModel = {
    id: "1",
    name: "Lorem Ipsum",
    image: "N/A",
    type: "Card",
    text: "N/A",
    cost: 99,
    strength: 99,
    health: 99
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
            drawCardCall: new DrawCardCall(
                {
                    playerId: "8dbc6953-e25e-49f0-a298-7a0ea721de6c"
                },
                cardDrawnEventBus)
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
            <div className="board" style={style}>
                <div className="board-north">
                    <Slider
                        rangeMin={3}
                        rangeMax={8}
                        step={0.5}
                        startValue={this.state.scale}
                        onValueChange={this.changeScale}
                        label="Scaling"
                    />
                </div>
                <div className="board-center">
                    <div className="board-side board-side-top">
                        <div className="avatar-area">
                        </div>
                        <div className="hand-area">
                        </div>
                        <div className="deck-area">
                        </div>
                        <div className="play-area">
                        </div>
                        <div className="graveyard-area">
                        </div>
                    </div>
                    <div className="board-side board-side-bottom">
                        <div className="avatar-area">
                        </div>
                        <div className="hand-area">
                            <Hand
                                cardDrawnEventBus={this.state.cardDrawnEventBus}
                                mouseOnCardEventBus={this.state.mouseOnCardEventBus}
                            />
                        </div>
                        <div className="deck-area">
                            <Deck
                                drawCardCall={this.state.drawCardCall}
                            />
                        </div>
                        <div className="play-area">
                            <CardContainer
                                cards={[card]}
                                eventBus={this.state.mouseOnCardEventBus}
                            />
                        </div>
                        <div className="graveyard-area">
                        </div>
                    </div>
                </div>
                <div className="board-east">
                    <div className="card-preview-area">
                        <CardPreview
                            eventBus={this.state.mouseOnCardEventBus}
                            scale={this.state.scale}
                        />
                    </div>
                    <div className="log-area">
                    </div>
                </div>
            </div>
        );
    }
}

export default Board;
