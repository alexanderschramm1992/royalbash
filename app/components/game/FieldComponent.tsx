import * as React from "react";

import "../common/common.css";
import "./FieldComponent.css";
import store, {getPlayer} from "../../Store";
import {Target} from "../../model/Game";
import TargetContainer from "./TargetContainer";
import {SummoningIssuedActionFactory} from "../../actions/SummoningIssuedAction";
import {GatheringResourcesIssuedActionFactory} from "../../actions/GatheringResourcesIssuedAction";

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
        this.handleDragOverForTargets = this.handleDragOverForTargets.bind(this);
        this.handleDropForTargets = this.handleDropForTargets.bind(this);
    }

    handleDragOver(event: any): void {
        console.log("Field: 'Watch out, something is coming from above!'");
        event.preventDefault();
    }

    handleDrop(event: any): void {

        event.preventDefault();
        if (!store.getState().gatheringResourceIssued && store.getState().resourcesCardDragged) {
            store.dispatch(
                GatheringResourcesIssuedActionFactory.getInstance(
                    store.getState().resourcesCardDragged,
                )
            );
        }
    }

    handleDragOverForTargets(event: any): void {
        console.log("Target: 'Watch out, something is coming from above!'");
        event.preventDefault();
    }

    handleDropForTargets(target: Target, event: any): void {

        event.preventDefault();
        if (!store.getState().summonCardIssued && store.getState().summoningCardDragged) {
            store.dispatch(
                SummoningIssuedActionFactory.getInstance(
                    store.getState().summoningCardDragged,
                    target.id
                )
            );
        }
    }

    render(): any {

        return (
            <div className="field"
                 onDragOver={this.handleDragOver}
                 onDrop={this.handleDrop}
            >
                <TargetContainer
                    targets={this.state.targets}
                    handleDragOver={this.handleDragOverForTargets}
                    handleDrop={this.handleDropForTargets}
                />
            </div>
        );
    }
}

export default FieldComponent;
