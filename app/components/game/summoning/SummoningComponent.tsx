import * as React from "react";

import "./SummoningComponent.css";
import "../../common/common.css";

import {Summoning} from "../../../model/Game";
import CardView from "../CardView";

export interface SummoningProps {

    summoning: Summoning;
}

export class SummoningComponent extends React.Component<SummoningProps, {}> {

    constructor(props: SummoningProps) {

        super(props);
    }

    render(): any {

        return (
            <CardView
                name={this.props.summoning.summoningCard.name}
                costRations={this.props.summoning.summoningCard.costRations}
                costMaterial={this.props.summoning.summoningCard.costMaterial}
                costBlessing={this.props.summoning.summoningCard.costBlessing}
                image={this.props.summoning.summoningCard.image}
                text={this.props.summoning.summoningCard.text}
                lore={this.props.summoning.summoningCard.lore}
                type={this.props.summoning.summoningCard.type}
                subType={this.props.summoning.summoningCard.subType}
                strength={this.props.summoning.currentStrength}
                health={this.props.summoning.currentHealth}
            />
        );
    }
}

export default SummoningComponent;
