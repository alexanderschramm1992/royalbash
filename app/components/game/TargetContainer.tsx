import * as React from "react";

import "./TargetContainer.css";
import "../common/common.css";

import {Target} from "../../model/Game";
import SummoningMini from "./card/SummoningMini";

export interface TargetContainerProps {

    targets: Target[];
    handleDragOver: (event: any) => void,
    handleDrop: (event: any) => void
}

export class TargetContainer extends React.Component<TargetContainerProps, {}> {

    render(): any {

        let targetElements = [];
        for (let i = 0; i < this.props.targets.length; i++) {

            targetElements.push(
                <div
                    key={i}
                    data-target-id={this.props.targets[i].id}
                    className={"summoning-mini-placeholder summoning-mini-placeholder-" + i + " border-large"}
                    onDragOver={this.props.handleDragOver}
                    onDrop={this.props.handleDrop}
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
