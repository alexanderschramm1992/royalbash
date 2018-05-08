import * as React from "react";

import "./SummoningComponent.css";
import "./../../common/common.css";

import {Card, Summoning} from "../../../model/Game";

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
                            {this.props.summoning.card.name}
                        </div>
                    </div>
                    <div className="cost-wrapper border-small">
                        <div className="cost cost-rations border-small">
                            <div className="font-size-extra-large center-text">
                                {this.props.summoning.card.costRations? this.props.summoning.card.costRations : 99}
                            </div>
                        </div>
                        <div className="cost cost-material border-small">
                            <div className="font-size-extra-large center-text">
                                {this.props.summoning.card.costMaterial? this.props.summoning.card.costMaterial : 99}
                            </div>
                        </div>
                        <div className="cost cost-blessing border-small">
                            <div className="font-size-extra-large center-text">
                                {this.props.summoning.card.costBlessing? this.props.summoning.card.costBlessing : 99}
                            </div>
                        </div>
                    </div>
                </div>
                <div className="image-wrapper border-small">
                    <img className="image" src={this.props.summoning.card.image} alt={this.props.summoning.card.name}/>
                </div>
                <div className="type">
                    <div className="font-size-medium">
                        {this.props.summoning.card.type}
                        {this.props.summoning.card.subType && " - " + this.props.summoning.card.subType}
                    </div>
                </div>
                <div className="text-wrapper border-small">
                    <div className="text">
                        <div className="font-size-medium">
                            {this.props.summoning.card.text}
                        </div>
                    </div>
                    <div className="lore">
                        <div className="font-size-medium">
                            {this.props.summoning.card.lore && this.props.summoning.card.lore}
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
