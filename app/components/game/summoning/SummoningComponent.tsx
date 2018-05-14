import * as React from "react";

import "./SummoningComponent.css";
import "./../../common/common.css";

import {SummoningCard, Summoning} from "../../../model/Game";

export interface SummoningProps {

    summoning: Summoning;
}

export class SummoningComponent extends React.Component<SummoningProps, {}> {

    constructor(props: SummoningProps) {

        super(props);
    }

    render(): any {

        return (
            <div
                draggable={true}
                className="summoning border-large border-radius"
            >
                <div className="head-wrapper">
                    <div className="name">
                        <div className="font-size-large">
                            {this.props.summoning.summoningCard.name}
                        </div>
                    </div>
                    <div className="cost-wrapper border-small">
                        <div className="cost cost-rations border-small">
                            <div className="font-size-extra-large center-text">
                                {this.props.summoning.summoningCard.costRations? this.props.summoning.summoningCard.costRations : 99}
                            </div>
                        </div>
                        <div className="cost cost-material border-small">
                            <div className="font-size-extra-large center-text">
                                {this.props.summoning.summoningCard.costMaterial? this.props.summoning.summoningCard.costMaterial : 99}
                            </div>
                        </div>
                        <div className="cost cost-blessing border-small">
                            <div className="font-size-extra-large center-text">
                                {this.props.summoning.summoningCard.costBlessing? this.props.summoning.summoningCard.costBlessing : 99}
                            </div>
                        </div>
                    </div>
                </div>
                <div className="image-wrapper border-small">
                    <img className="image" src={this.props.summoning.summoningCard.image} alt={this.props.summoning.summoningCard.name}/>
                </div>
                <div className="type">
                    <div className="font-size-medium">
                        {this.props.summoning.summoningCard.type}
                        {this.props.summoning.summoningCard.subType && " - " + this.props.summoning.summoningCard.subType}
                    </div>
                </div>
                <div className="text-wrapper border-small">
                    <div className="text">
                        <div className="font-size-medium">
                            {this.props.summoning.summoningCard.text}
                        </div>
                    </div>
                    <div className="lore">
                        <div className="font-size-medium">
                            {this.props.summoning.summoningCard.lore && this.props.summoning.summoningCard.lore}
                        </div>
                    </div>
                </div>
                <div className="foot-wrapper">
                    <div className="stats-wrapper border-small">
                        <div className="strength border-small">
                            <div className="font-size-large center-text">
                                {this.props.summoning.currentStrength}
                            </div>
                        </div>
                        <div className="health border-small">
                            <div className="font-size-large center-text">
                                {this.props.summoning.currentHealth}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default SummoningComponent;
