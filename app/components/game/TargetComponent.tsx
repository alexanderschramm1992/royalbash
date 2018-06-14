import * as React from "react";

import "./TargetComponent.css";
import "../common/common.css";

import {Target} from "../../model/Game";
import SummoningMini from "./card/SummoningMini";

export interface TargetComponentProps {

    target: Target;
    handleDragOver: (event: any) => void,
    handleDrop: (target: Target, event: any) => void
}

export class TargetComponent extends React.Component<TargetComponentProps, {}> {

    constructor(props: TargetComponentProps) {

        super(props);
        this.handleDrop = this.handleDrop.bind(this);
    }

    handleDrop(event: any): void {
        this.props.handleDrop(this.props.target, event);
    }

    render(): any {

        return (
            <div
                className={"target border-large"}
                onDragOver={this.props.handleDragOver}
                onDrop={this.handleDrop}
            >
                {this.props.target.summoning &&
                <SummoningMini summoning = {this.props.target.summoning} />
                }
            </div>
        );
    }
}

export default TargetComponent;
