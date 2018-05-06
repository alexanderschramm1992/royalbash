import * as React from "react";

import "./Summoning.css";
import "./../../common/common.css";

import { CardModel } from "./Card";

export interface SummoningProps {

    summoningModel: SummoningModel;
}

export interface SummoningModel {

    readonly id: string;
    readonly card: CardModel;
    currentStreangth: number;
    currentHealth: number;
}

export class Card extends React.Component<SummoningProps, {}> {

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
                        <div className="fon
                        t-size-large">
                            {this.props.summoningModel.card.name}
                        </div>
                    </div>
                    <div className="cost-wrapper border-small">
                        <div className="cost cost-rations border-small">
                            <div className="font-size-extra-large center-text">
                                {this.props.summoningModel.card.costRations? this.props.summoningModel.card.costRations : 99}
                            </div>
                        </div>
                        <div className="cost cost-material border-small">
                            <div className="font-size-extra-large center-text">
                                {this.props.summoningModel.card.costMaterial? this.props.summoningModel.card.costMaterial : 99}
                            </div>
                        </div>
                        <div className="cost cost-blessing border-small">
                            <div className="font-size-extra-large center-text">
                                {this.props.summoningModel.card.costBlessing? this.props.summoningModel.card.costBlessing : 99}
                            </div>
                        </div>
                    </div>
                </div>
                <div className="image-wrapper border-small">
                    <img className="image" src={this.props.summoningModel.card.image} alt={this.props.summoningModel.card.name}/>
                </div>
                <div className="type">
                    <div className="font-size-medium">
                        {this.props.summoningModel.card.type}
                        {this.props.summoningModel.card.subType && " - " + this.props.summoningModel.card.subType}
                    </div>
                </div>
                <div className="text-wrapper border-small">
                    <div className="text">
                        <div className="font-size-medium">
                            {this.props.summoningModel.card.text}
                        </div>
                    </div>
                    <div className="lore">
                        <div className="font-size-medium">
                            {this.props.summoningModel.card.lore && this.props.summoningModel.card.lore}
                        </div>
                    </div>
                </div>
                <div className="foot-wrapper">
                    <div className="stats-wrapper border-small">
                        <div className="strength border-small">
                            <div className="font-size-large center-text">
                                {this.props.summoningModel.card.strength}
                            </div>
                        </div>
                        <div className="health border-small">
                            <div className="font-size-large center-text">
                                {this.props.summoningModel.card.health}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Card;
