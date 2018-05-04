import * as React from "react";

import "./CardMini.css";
import "./../../common/common.css";
import {CardProps} from "./Card";
import store from "../../../Store";
import {MouseOnCardActionFactory} from "../../../actions/MouseOnCardAction";

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

        //store.dispatch(MouseOffCardActionFactory.getInstance());
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
                    <div className="cost border-small">
                        <div className="font-size-extra-large center-text">
                            {this.props.cardModel.cost}
                        </div>
                    </div>
                </div>
                <div className="image-wrapper border-small">
                    <img className="image" src={this.props.cardModel.image} alt={this.props.cardModel.name}/>
                </div>
                <div className="foot-wrapper">
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
        );
    }
}

export default CardMini;
