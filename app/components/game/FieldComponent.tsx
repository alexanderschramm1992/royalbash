import * as React from "react";

import "../common/common.css";
import "./FieldComponent.css";
import store, {getPlayer} from "../../Store";
import {Summoning} from "../../model/Game";
import SummoningMiniContainer from "./SummoningMiniContainer";

export interface FieldComponentState {

    summonings: Summoning[];
}

export class FieldComponent extends React.Component<{}, FieldComponentState> {

    constructor(props: any) {
        super(props);

        this.state = {
            summonings: []
        };

        store.subscribe((): void => {

            this.setState({
                summonings: getPlayer().field.targets.map(target => target.summoning)
            });
        });
    }

    render(): any {

        return (
            <div className="hand">
                <SummoningMiniContainer
                    size={5}
                    summonings={this.state.summonings}
                />
            </div>
        );
    }
}

export default FieldComponent;
