import * as React from "react";

import "../common/common.css";
import "./EndTurnButtonComponent.css";

export class EndTurnButtonComponent extends React.Component<{}, {}> {

    constructor(props: any) {
        super(props);

        this.state = {
        };
    }

    render(): any {

        return (
            <div className="end-turn-button">
                <button className="button">
                    <div className="font-size-extra-large">End Turn</div>
                </button>
            </div>
        );
    }
}

export default EndTurnButtonComponent;
