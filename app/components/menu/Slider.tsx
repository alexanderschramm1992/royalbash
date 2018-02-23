import * as React from "react";

import "./../common.css";
import "./Slider.css";

export interface SliderProps {

    readonly rangeMin: number;
    readonly rangeMax: number;
    readonly startValue: number;
}

export class Slider extends React.Component<SliderProps, {}> {

    render(): any {

        return (
            <div className="slider">
            </div>
        );
    }
}

export default Slider;
