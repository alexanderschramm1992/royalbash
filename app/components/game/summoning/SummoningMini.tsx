import * as React from "react";

import "./SummoningMini.css";
import "../../common/common.css";

import {SummoningProps} from "./SummoningComponent";
import store from "../../../Store";
import {MouseOnSummoningActionFactory} from "../../../actions/MouseOnSummoningAction";
import {MouseOffSummoningActionFactory} from "../../../actions/MouseOffSummoningAction";

export class SummoningMini extends React.Component<SummoningProps, {}> {

    constructor(props: SummoningProps) {

        super(props);
        this.handleMouseOver = this.handleMouseOver.bind(this);
        this.handleMouseOut = this.handleMouseOut.bind(this);
        this.handleDrag = this.handleDrag.bind(this);
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

    handleDrag(event: any): void {

        console.dir(event);
    }

    render(): any {

        return (
            <div
                className="summoning-mini border-large"
                draggable={true}
                onDragStart={this.handleDrag}
                onMouseOver={this.handleMouseOver}
                onMouseOut={this.handleMouseOut}
            >
                <div className="head-wrapper">
                    <div className="name">
                        <div className="font-size-large">
                            {this.props.summoning.summoningCard.name}
                        </div>
                    </div>
                    <div className="cost-wrapper">
                        <div className="cost cost-rations">
                            <div className="font-size-extra-large center-text font-border">
                                {this.props.summoning.summoningCard.costRations}
                            </div>
                        </div>
                        <div className="cost cost-material">
                            <div className="font-size-extra-large center-text font-border">
                                {this.props.summoning.summoningCard.costMaterial}
                            </div>
                        </div>
                        <div className="cost cost-blessing">
                            <div className="font-size-extra-large center-text font-border">
                                {this.props.summoning.summoningCard.costBlessing}
                            </div>
                        </div>
                    </div>
                </div>
                <div className="image-wrapper border-small">
                    <img 
                        className="image" 
                        draggable={false}
                        src={this.props.summoning.summoningCard.image} 
                        alt={this.props.summoning.summoningCard.name}
                    />
                </div>
                <div className="foot-wrapper">
                    <div className="stats-wrapper">
                        <div className="strength">
                            <div className="font-size-large center-text font-border">
                                {this.props.summoning.currentStrength}
                            </div>
                        </div>
                        <div className="health">
                            <div className="font-size-large center-text font-border">
                                {this.props.summoning.currentHealth}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default SummoningMini;
