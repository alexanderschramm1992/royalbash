import * as React from "react";

import "./TargetContainer.css";
import "../common/common.css";

import {Target} from "../../model/Game";
import TargetComponent from "./TargetComponent";

export interface TargetContainerProps {

    targets: Target[];
    handleDragOver: (event: any) => void,
    handleDrop: (target: Target, event: any) => void
}

export class TargetContainer extends React.Component<TargetContainerProps, {}> {

    render(): any {

        let targetElements = [];
        for (let i = 0; i < this.props.targets.length; i++) {

            targetElements.push(
                <div className="target-wrapper" key={i}>
                    <TargetComponent
                        target={this.props.targets[i]}
                        handleDragOver={this.props.handleDragOver}
                        handleDrop={this.props.handleDrop}
                    />
                </div>
            );
        }

        return (
            <div className="target-container border-large">
                {targetElements}
            </div>
        );
    }
}

export default TargetContainer;
