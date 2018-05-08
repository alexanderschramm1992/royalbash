import * as React from "react";

import "./SummoningMiniContainer.css";
import "./../../common/common.css";

import {Summoning} from "../../../model/Game";
import SummoningMini from "./SummoningMini";

export interface SummoningMiniContainerProps {

    readonly size: number;
}

interface SummoningMiniContainerState {

    summonings: Summoning[];
}

export class SummoningMiniContainer extends React.Component<SummoningMiniContainerProps, SummoningMiniContainerState> {

    constructor(props: SummoningMiniContainerProps) {
        super(props);

        this.state = {
            summonings: []
        }
    }

    handleDrop(event: any): any {

        console.log("We are handeling the drop, bitches!");
        event.preventDefault();
    }

    handleDragOver(event: any): void {

        console.log("Watch out, something is coming from above!");
        event.preventDefault();
    }

    render(): any {

        let summoningPlaceholders = [];
        for (let i = 0; i < this.props.size; i++) {

            summoningPlaceholders.push(
                <div
                    key={i}
                    className={"summoning-mini-placeholder summoning-mini-placeholder-" + i + " border-large"}
                    onDragOver={this.handleDragOver}
                    onDrop={this.handleDrop}
                >
                    {this.state.summonings[i] &&
                    <SummoningMini summoning = {this.state.summonings[i]} />
                    }
                </div>
            );
        }

        return (
            <div className="summoning-mini-container border-large">
                {summoningPlaceholders}
            </div>
        );
    }
}

export default SummoningMiniContainer;
