import * as React from "react";

import "../common/common.css";
import "./FieldComponent.css";
import store, {getPlayer} from "../../Store";
import {Target} from "../../model/Game";
import TargetContainer from "./TargetContainer";
import {SummoningIssuedActionFactory} from "../../actions/SummoningIssuedAction";

interface FieldComponentState {

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

        this.handleDragOver = this.handleDragOver.bind(this);
        this.handleDrop = this.handleDrop.bind(this);
    }

    handleDragOver(event: any): void {

        console.log("Watch out, something is coming from above!");
        event.preventDefault();
    }

    handleDrop(target: Target, event: any): void {

        event.preventDefault();
        if (!store.getState().summonCardIssued) {
            store.dispatch(
                SummoningIssuedActionFactory.getInstance(
                    store.getState().cardToBeSummoned,
                    target.id
                )
            );
        }
    }

    render(): any {

        return (
            <div className="field">
                <TargetContainer
                    targets={this.state.targets}
                    handleDragOver={this.handleDragOver}
                    handleDrop={this.handleDrop}
                />
            </div>
        );
    }
}

export default FieldComponent;
