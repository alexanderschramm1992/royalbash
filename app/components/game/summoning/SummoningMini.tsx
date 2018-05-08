import * as React from "react";

import "./SummoningMini.css";
import "./../../common/common.css";
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

        store.dispatch(MouseOnSummoningActionFactory.getInstance(this.props.summoning.id));
    }

    handleMouseOut(): void {

        store.dispatch(MouseOffSummoningActionFactory.getInstance());
    }

    handleDrag(event: any): void {

        //event.dataTransfer.setData("boardId", this.props.cardModel);
        //event.dataTransfer.setData("playerInstanceId", "test123");
        //event.dataTransfer.setData("cardId", this.props.cardModel.id);
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

export default SummoningMini;
