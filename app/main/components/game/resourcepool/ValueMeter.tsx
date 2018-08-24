import * as React from "react";

import "../../common/common.css";
import "./ValueMeter.css";

export enum ValueMeterColor {
    green, silver, violet, red
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
            height: (100 * this.props.resource / this.props.maxResource) + "%"
        };

        return (
            <div className="value-meter">
                <div className={"value-meter-filling value-meter-filling-" + ValueMeterColor[this.props.color]} style={style}/>
            </div>
        )
    }
}
