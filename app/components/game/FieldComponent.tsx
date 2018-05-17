import * as React from "react";

import "../common/common.css";
import "./FieldComponent.css";
import store, {getPlayer} from "../../Store";
import {Target} from "../../model/Game";
import TargetContainer from "./TargetContainer";

export interface FieldComponentState {

    targets: Target[];
}

export class FieldComponent extends React.Component<{}, FieldComponentState> {

    constructor(props: any) {
        super(props);

        this.state = {
            targets: []
        };

        store.subscribe((): void => {

            this.setState({
                targets: getPlayer().field.targets
            });
        });
    }

    render(): any {

        return (
            <div className="field">
                <TargetContainer
                    targets={this.state.targets}
                />
            </div>
        );
    }
}

export default FieldComponent;
