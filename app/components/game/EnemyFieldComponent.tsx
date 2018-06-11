import * as React from "react";

import "../common/common.css";
import "./FieldComponent.css";
import store, {getEnemyPlayer} from "../../Store";
import {Target} from "../../model/Game";
import TargetContainer from "./TargetContainer";
import {AttackingIssuedActionFactory} from "../../actions/AttackingIssuedAction";

interface EnemyFieldComponentState {

    targets: Target[];
}

export class EnemyFieldComponent extends React.Component<{}, EnemyFieldComponentState> {

    constructor(props: any) {
        super(props);

        this.state = {
            targets: []
        };

        store.subscribe((): void => {

            this.setState({
                targets: getEnemyPlayer().field.targets
            });
        });

        this.handleDragOver = this.handleDragOver.bind(this);
        this.handleDrop = this.handleDrop.bind(this);
    }

    handleDragOver(event: any): void {

        console.log("Watch out, something is coming from above!");
        event.preventDefault();
    }

    handleDrop(event: any): void {

        console.log("We are handeling the drop, bitches!");
        event.preventDefault();
        if(!store.getState().attackingSummoningIssued) {
            store.dispatch(
                AttackingIssuedActionFactory.getInstance(
                    store.getState().attackingSummoning,
                    event.target.attributes["data-target-id"].nodeValue
                )
            )
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

export default EnemyFieldComponent;
