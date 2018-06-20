import * as React from "react";

import "../common/common.css";
import "./FieldComponent.css";
import store, {getEnemyPlayer} from "../../Store";
import {Target} from "../../model/Game";
import TargetContainer from "./TargetContainer";
import {AttackingIssuedActionFactory} from "../../actions/AttackingTargetIssuedAction";

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

    handleDrop(target: Target, event: any): void {

        event.preventDefault();
        if(!store.getState().attackingTargetIssued && store.getState().summoningDragged) {
            store.dispatch(
                AttackingIssuedActionFactory.getInstance(
                    store.getState().summoningDragged,
                    target.id
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
