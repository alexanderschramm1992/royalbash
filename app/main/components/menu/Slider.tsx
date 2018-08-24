import * as React from "react";

import "./../common/common.css";
import "./Slider.css";

export interface SliderProps {

    readonly rangeMin: number;
    readonly rangeMax: number;
    readonly step: number;
    readonly startValue: number;
    readonly onValueChange: (value: number) => void;
    readonly label?: string;
}

export class Slider extends React.Component<SliderProps, {}> {

    constructor(props: SliderProps) {
        super(props);

        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event: React.FormEvent<HTMLInputElement>) {

        this.props.onValueChange(+event.currentTarget.value);
    }

    render(): any {

        return (
            <div className="slider">
                <div className="label">
                    {this.props.label &&
                        <div className="font-size-small">{this.props.label}</div>
                    }
                </div>
                <input
                    type="range"
                    className="input"
                    min={this.props.rangeMin}
                    max={this.props.rangeMax}
                    step={this.props.step}
                    value={this.props.startValue}
                    onChange={this.handleChange}
                />
            </div>
        );
    }
}

export default Slider;
