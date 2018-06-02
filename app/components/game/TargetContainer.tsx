import * as React from "react";

import "./TargetContainer.css";
import "../common/common.css";

import {Target} from "../../model/Game";
import SummoningMini from "./summoning/SummoningMini";
import store from "../../Store";
import {SummoningIssuedActionFactory} from "../../actions/SummoningIssuedAction";

export interface TargetContainerProps {

    targets: Target[];
}

export class TargetContainer extends React.Component<TargetContainerProps, {}> {

    handleDrop(event: any): any {

        console.log("We are handeling the drop, bitches!");
        event.preventDefault();
        if (!store.getState().summonCardIssued) {
            store.dispatch(
                SummoningIssuedActionFactory.getInstance(
                    store.getState().cardToBeSummoned,
                    event.target.attributes["data-target-id"].nodeValue
                )
            );
        }
    }

    handleDragOver(event: any): void {

        console.log("Watch out, something is coming from above!");
        event.preventDefault();
    }

    render(): any {

        let targetElements = [];
        for (let i = 0; i < this.props.targets.length; i++) {

            targetElements.push(
                <div
                    key={i}
                    data-target-id={this.props.targets[i].id}
                    className={"summoning-mini-placeholder summoning-mini-placeholder-" + i + " border-large"}
                    onDragOver={this.handleDragOver}
                    onDrop={this.handleDrop}
                >
                    {this.props.targets[i].summoning &&
                    <SummoningMini summoning = {this.props.targets[i].summoning} />
                    }
                </div>
            );
        }

        return (
            <div className="summoning-mini-container border-large">
                {targetElements}
            </div>
        );
    }
}

export default TargetContainer;
