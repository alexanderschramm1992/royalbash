import * as React from "react";

import "./SummoningMiniContainer.css";
import "./../../common/common.css";

import {SummoningModel} from "./Summoning";
import SummoningMini from "./SummoningMini";

export interface SummoningMiniContainerProps {

    size: number;
    summonings: SummoningModel[];
}

export class SummoningMiniContainer extends React.Component<SummoningMiniContainerProps, {}> {

    constructor(props: SummoningMiniContainerProps) {
        super(props);

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
                    {this.props.summonings[i] &&
                    <SummoningMini summoningModel = {this.props.summonings[i]} />
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
