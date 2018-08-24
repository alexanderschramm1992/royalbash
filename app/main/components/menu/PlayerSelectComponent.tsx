import * as React from "react";

import "../common/common.css";
import "./PlayerSelectComponent.css";
import BoardContainer from "../game/BoardContainer";

interface PlayerSelectState {

    readonly gameId: string,
    readonly playerBlueId: string,
    readonly playerRedId: string,

    selectedPlayerId: string
}

export class PlayerSelectComponent extends React.Component<{}, PlayerSelectState> {

    constructor(props: any) {
        super(props);

        this.state = {
            gameId: "6d5864f4-5fb1-4615-bf6a-07a1211ef6d3",
            playerBlueId: "8dbc6953-e25e-49f0-a298-7a0ea721de6c",
            playerRedId: "736cb270-c73c-4257-b49f-d71d9b4cb59b",
            selectedPlayerId: undefined
        };

        this.select = this.select.bind(this);
    }

    select(): void {
        let selectElement = (
            document
                .getElementsByClassName("player-select-selector")
                .item(0)
        ) as HTMLSelectElement;
        let selectedPlayerId = selectElement.options[selectElement.selectedIndex].text;
        this.setState({
            selectedPlayerId: selectedPlayerId
        });
    }

    render(): any {

        return (
            <div className="board-container">
                {!this.state.selectedPlayerId &&
                <div className="player-select">
                    <p className="player-select-label font-size-large">Select Player Id:</p>
                    <select className="player-select-selector">
                        <option>{this.state.playerBlueId}</option>
                        <option>{this.state.playerRedId}</option>
                    </select>
                    <button onClick={this.select} className="player-select-button">Done</button>
                </div>
                }
                {this.state.selectedPlayerId &&
                    <BoardContainer
                        gameId={this.state.gameId}
                        playerId={this.state.selectedPlayerId}
                    />
                }
                <div id="modal" title="Start Game"/>
            </div>
        );
    }
}

export default PlayerSelectComponent;