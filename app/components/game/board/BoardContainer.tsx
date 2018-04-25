import * as React from "react";

import "./../../common/common.css";
import "./BoardContainer.css";

import EventBus from "../../../events/EventBus";
import MouseOnCardEvent from "../../../events/MouseOnCardEvent";
import Board from "./Board";

export class BoardContainer extends React.Component<{}, {}> {

    constructor(props: any) {
        super(props);

        this.state = {
            mouseOnCardEventBus: new EventBus<MouseOnCardEvent>()
        };
    }

    render(): any {

        return (
            <div className="board-container">
                <Board/>
                <div id="modal" title="Start Game"/>
            </div>
        );
    }
}

export default BoardContainer;