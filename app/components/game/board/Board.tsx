import * as React from "react";

import "./../../common/common.css";
import "./Board.css";

import Slider from "../../menu/Slider";
import DetailView from "../DetailView";
import DeckComponent, {DeckComponentType} from "./DeckComponent";
import HandComponent from "./HandComponent";
import GraveyardComponent from "./GraveyardComponent";
import DrawCardCall from "../../../rest/DrawCardCall";
import { SummoningMiniContainer } from "../summoning/SummoningMiniContainer";
import store from "../../../Store";
import {LoadGameIssuedActionFactory} from "../../../actions/LoadGameIssuedAction";
import LoadGameCall from "../../../rest/LoadGameCall";

interface BoardState {

    readonly scale: number;
    readonly drawCardCall: DrawCardCall;
    readonly loadGameCall: LoadGameCall;
}

export class BoardComponent extends React.Component<{}, BoardState> {

    constructor(props: any) {
        super(props);

        this.state = {
            scale: 5,
            drawCardCall: new DrawCardCall(),
            loadGameCall: new LoadGameCall()
        };

        store.dispatch(LoadGameIssuedActionFactory.getInstance("6d5864f4-5fb1-4615-bf6a-07a1211ef6d3"));

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
                            <div className="summoning-deck-area summoning-deck-area-remote"></div>
                            <div className="ressources-deck-area ressources-deck-area-remote"></div>
                            <div className="graveyard-area graveyard-area-remote"></div>
                            <div className="hand-area hand-area-remote"></div>
                        </div>
                        <div className="play-area border-large border-radius">
                            <div className="remote-summoning-area">
                                <SummoningMiniContainer
                                    size={7}
                                />
                            </div>
                            <div className="summoning-area">
                                <SummoningMiniContainer
                                    size={7}
                                />
                            </div>
                        </div>
                        <div className="player-area border-large border-radius">
                            <div className="hand-area">
                                <HandComponent/>
                            </div>
                            <div className="stack-area-wrapper">
                                <div className="summoning-deck-area">
                                    <DeckComponent type={DeckComponentType.SUMMONING_DECK}/>
                                </div>
                                <div className="resources-deck-area">
                                    <DeckComponent type={DeckComponentType.RESOUCRES_DECK}/>
                                </div>
                                <div className="graveyard-area">
                                    <GraveyardComponent />
                                </div>
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

export default BoardComponent;
