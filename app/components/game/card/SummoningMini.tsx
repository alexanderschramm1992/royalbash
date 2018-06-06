import * as React from "react";

import "./SummoningMini.css";
import "../../common/common.css";

import {SummoningProps} from "./SummoningComponent";
import store from "../../../Store";
import {MouseOnSummoningActionFactory} from "../../../actions/MouseOnSummoningAction";
import {MouseOffSummoningActionFactory} from "../../../actions/MouseOffSummoningAction";
import CardMiniView from "./CardMiniView";
import {SummoningDraggedActionFactory} from "../../../actions/SummoningDraggedAction";

export class SummoningMini extends React.Component<SummoningProps, {}> {

    constructor(props: SummoningProps) {

        super(props);
        this.handleMouseOver = this.handleMouseOver.bind(this);
        this.handleMouseOut = this.handleMouseOut.bind(this);
        this.handleDragStart = this.handleDragStart.bind(this);
    }

    handleMouseOver(): void {

        let summoningId = this.props.summoning.id;
        if (summoningId) {
            store.dispatch(MouseOnSummoningActionFactory.getInstance(summoningId));
        }
    }

    handleMouseOut(): void {
        store.dispatch(MouseOffSummoningActionFactory.getInstance());
    }

    handleDragStart(event: any): void {
        store.dispatch(SummoningDraggedActionFactory.getInstance(this.props.summoning.id));
    }

    render(): any {

        return (
            <CardMiniView
                name={this.props.summoning.summoningCard.name}
                costRations={this.props.summoning.summoningCard.costRations}
                costMaterial={this.props.summoning.summoningCard.costMaterial}
                costBlessing={this.props.summoning.summoningCard.costBlessing}
                image={this.props.summoning.summoningCard.image}
                strength={this.props.summoning.currentStrength}
                health={this.props.summoning.currentHealth}
                handleMouseOver={this.handleMouseOver}
                handleMouseOut={this.handleMouseOut}
                handleDragStart={this.handleDragStart}
            />
        );
    }
}

export default SummoningMini;
