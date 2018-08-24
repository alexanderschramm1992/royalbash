import * as React from "react";

import "../common/common.css";
import "./BoardComponent.css";

import Slider from "../menu/Slider";
import DetailView from "./DetailView";
import SummoningDeckComponent from "./stack/SummoningDeckComponent";
import HandComponent from "./HandComponent";
import GraveyardComponent from "./stack/GraveyardComponent";
import FieldComponent from "./FieldComponent";
import ResourcesDeckComponent from "./stack/ResourcesDeckComponent";
import {ResourcePoolComponent} from "./resourcepool/ResourcePoolComponent";
import EnemyFieldComponent from "./EnemyFieldComponent";
import EndTurnButtonComponent from "./EndTurnButtonComponent";
import EnemyHandComponent from "./EnemyHandComponent";
import store from "../../Store";

interface BoardComponentState {
    readonly scale: number;
}

export class BoardComponent extends React.Component<{}, BoardComponentState> {

    constructor(props: any) {
        super(props);

        this.state = {
            scale: 5
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
                            <div className="summoning-deck-area summoning-deck-area-remote"></div>
                            <div className="ressources-deck-area ressources-deck-area-remote"></div>
                            <div className="graveyard-area graveyard-area-remote"></div>
                            <div className="hand-area hand-area-remote">
                                <EnemyHandComponent/>
                            </div>
                        </div>
                        <div className="play-area border-large border-radius">
                            <div className="remote-summoning-area">
                                <EnemyFieldComponent/>
                            </div>
                            <div className="summoning-area">
                                <FieldComponent/>
                            </div>
                        </div>
                        <div className="player-area border-large border-radius">
                            <div className="hand-area">
                                <HandComponent/>
                            </div>
                            <div className="avatar-area border-large border-radius">
                                <ResourcePoolComponent/>
                            </div>
                            <div className="stack-area-wrapper">
                                <div className="summoning-deck-area">
                                    <SummoningDeckComponent/>
                                </div>
                                <div className="resources-deck-area">
                                    <ResourcesDeckComponent/>
                                </div>
                                <div className="graveyard-area">
                                    <GraveyardComponent/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="east border-large border-radius">
                        <div className="summoningCard-preview-area">
                            <DetailView scale={this.state.scale * 1.5}/>
                        </div>
                        <div className="log-area"></div>
                        <div className="next-turn-button-area">
                            <EndTurnButtonComponent store={store} />
                        </div>
                    </div>
                </div>
                <div className="south border-large border-radius"></div>
            </div>
        );
    }
}

export default BoardComponent;
