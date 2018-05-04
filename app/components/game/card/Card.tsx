import * as React from "react";

import "./Card.css";
import "./../../common/common.css";

export interface CardProps {

    cardModel: CardModel;
}

export interface CardModel {

    readonly id: string;
    readonly name: string;
    readonly image: string;
    readonly type: string;
    readonly subType?: string;
    readonly text: string;
    readonly lore?: string;
    readonly cost: number;
    strength: number;
    health: number;
}

export class Card extends React.Component<CardProps, {}> {

    constructor(props: CardProps) {

        super(props);
        this.handleMouseOver = this.handleMouseOver.bind(this);
    }

    private handleMouseOver(): void {

        //this.props.eventBus.fireEvent(new MouseOnCardEvent(this.props.cardModel));
    }

    render(): any {

        return (
            <div
                draggable={true}
                className="card border-large border-radius"
                onMouseEnter={this.handleMouseOver}
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
                <div className="type">
                    <div className="font-size-medium">
                        {this.props.cardModel.type}
                        {this.props.cardModel.subType && " - " + this.props.cardModel.subType}
                    </div>
                </div>
                <div className="text-wrapper border-small">
                    <div className="text">
                        <div className="font-size-medium">
                            {this.props.cardModel.text}
                        </div>
                    </div>
                    <div className="lore">
                        <div className="font-size-medium">
                            {this.props.cardModel.lore && this.props.cardModel.lore}
                        </div>
                    </div>
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

export default Card;
