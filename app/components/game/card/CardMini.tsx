import * as React from "react";

import "./CardMini.css";
import "./../../common/common.css";
import {CardProps} from "./Card";
import store from "../../../Store";
import {MouseOnCardActionFactory} from "../../../actions/MouseOnCardAction";
import {MouseOffCardActionFactory} from "../../../actions/MouseOffCardAction";

export class CardMini extends React.Component<CardProps, {}> {

    constructor(props: CardProps) {

        super(props);
        this.handleMouseOver = this.handleMouseOver.bind(this);
        this.handleMouseOut = this.handleMouseOut.bind(this);
        this.handleDrag = this.handleDrag.bind(this);
    }

    handleMouseOver(): void {

        store.dispatch(MouseOnCardActionFactory.getInstance(this.props.cardModel.id));
    }

    handleMouseOut(): void {

        store.dispatch(MouseOffCardActionFactory.getInstance());
    }

    handleDrag(event: any): void {

        event.dataTransfer.setData("boardId", this.props.cardModel);
        event.dataTransfer.setData("playerInstanceId", "test123");
        event.dataTransfer.setData("cardId", this.props.cardModel.id);
        console.dir(event);
    }

    render(): any {

        return (
            <div
                draggable={true}
                onDragStart={this.handleDrag}
                className="card-mini border-large"
                onMouseOver={this.handleMouseOver}
                onMouseOut={this.handleMouseOut}
            >
                <div className="head-wrapper">
                    <div className="name">
                        <div className="font-size-large">
                            {this.props.cardModel.name}
                        </div>
                    </div>
                    <div className="cost-wrapper border-small">
                        <div className="cost cost-rations border-small">
                            <div className="font-size-extra-large center-text">
                                {this.props.cardModel.costRations? this.props.cardModel.costRations : 99}
                            </div>
                        </div>
                        <div className="cost cost-material border-small">
                            <div className="font-size-extra-large center-text">
                                {this.props.cardModel.costMaterial? this.props.cardModel.costMaterial : 99}
                            </div>
                        </div>
                        <div className="cost cost-blessing border-small">
                            <div className="font-size-extra-large center-text">
                                {this.props.cardModel.costBlessing? this.props.cardModel.costBlessing : 99}
                            </div>
                        </div>
                    </div>
                </div>
                <div className="image-wrapper border-small">
                    <img className="image" src={this.props.cardModel.image} alt={this.props.cardModel.name}/>
                </div>
                <div className="foot-wrapper">
                    <div className="stats-wrapper border-small">
                        <div className="strength border-small">
                            <div className="font-size-large center-text">
                                {this.props.cardModel.strength}
                            </div>
                        </div>
                        <div className="health border-small">
                            <div className="font-size-large center-text">
                                {this.props.cardModel.health}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default CardMini;
