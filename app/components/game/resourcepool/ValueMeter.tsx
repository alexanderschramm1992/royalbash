import * as React from "react";

import "../../common/common.css";
import "./ResourcePoolComponent.css";

export enum ValueMeterColor {
    GREEN, SILVER, VIOLET, RED
}

export interface ValueMeterProps {

    resource: number;
    readonly maxResource: number;
    readonly color: ValueMeterColor;
}

export class ValueMeter extends React.Component<ValueMeterProps, {}> {

    constructor(props: ValueMeterProps) {
        super(props);
    }

    render(): any {

        let style = {
            height: this.props.resource / this.props.maxResource
        };

        switch (this.props.color) {

        }

        return (
            <div className="resource-meter">
                <div className={"resource-meter-filling " + colorClass} style={style}/>
            </div>
        )
    }
}