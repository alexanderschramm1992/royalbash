import * as React from "react";

import "../common/common.css";
import "./BoardContainer.css";

import BoardComponent from "./BoardComponent";

export class BoardContainer extends React.Component<{}, {}> {

    constructor(props: any) {
        super(props);

    }

    render(): any {

        return (
            <div className="board-container">
                <BoardComponent/>
                <div id="modal" title="Start Game"/>
            </div>
        );
    }
}

export default BoardContainer;