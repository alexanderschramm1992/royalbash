import * as React from "react";

import "./CardMini.css";
import "../common/common.css";
import {CardProps} from "./CardComponent";
import store from "../../Store";
import {MouseOnCardActionFactory} from "../../actions/MouseOnCardAction";
import {MouseOffCardActionFactory} from "../../actions/MouseOffCardAction";
import {CardDraggedActionFactory} from "../../actions/CardDraggedAction";

export class CardMini extends React.Component<CardProps, {}> {

    constructor(props: CardProps) {

        super(props);
        this.handleMouseOver = this.handleMouseOver.bind(this);
        this.handleMouseOut = this.handleMouseOut.bind(this);
        this.handleDragStart = this.handleDragStart.bind(this);
    }

    handleMouseOver(): void {

        let summoningCardId = this.props.summoningCard.id;
        if(summoningCardId) {
            store.dispatch(MouseOnCardActionFactory.getInstance(summoningCardId));
        }
    }

    handleMouseOut(): void {

        store.dispatch(MouseOffCardActionFactory.getInstance());
    }

    handleDragStart(event: any): void {

        console.dir(event);
        store.dispatch(CardDraggedActionFactory.getInstance(this.props.summoningCard.id));
    }

    render(): any {

        return (
            <div
                draggable={true}
                onDragStart={this.handleDragStart}
                className="card-mini border-large"
                onMouseOver={this.handleMouseOver}
                onMouseOut={this.handleMouseOut}
            >
                <div className="head-wrapper">
                    <div className="name">
                        <div className="font-size-large">
                            {this.props.summoningCard.name}
                        </div>
                    </div>
                    <div className="cost-wrapper border-small">
                        <div className="cost cost-rations border-small">
                            <div className="font-size-extra-large center-text">
                                {this.props.summoningCard.costRations? this.props.summoningCard.costRations : 99}
                            </div>
                        </div>
                        <div className="cost cost-material border-small">
                            <div className="font-size-extra-large center-text">
                                {this.props.summoningCard.costMaterial? this.props.summoningCard.costMaterial : 99}
                            </div>
                        </div>
                        <div className="cost cost-blessing border-small">
                            <div className="font-size-extra-large center-text">
                                {this.props.summoningCard.costBlessing? this.props.summoningCard.costBlessing : 99}
                            </div>
                        </div>
                    </div>
                </div>
                <div className="image-wrapper border-small">
                    <img 
                        className="image" 
                        draggable={false}
                        src={this.props.summoningCard.image} 
                        alt={this.props.summoningCard.name}
                    />
                </div>
                <div className="foot-wrapper">
                    <div className="stats-wrapper border-small">
                        <div className="strength border-small">
                            <div className="font-size-large center-text">
                                {this.props.summoningCard.strength}
                            </div>
                        </div>
                        <div className="health border-small">
                            <div className="font-size-large center-text">
                                {this.props.summoningCard.health}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default CardMini;
