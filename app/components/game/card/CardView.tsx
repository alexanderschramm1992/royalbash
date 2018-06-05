import * as React from "react";

import "./CardView.css";
import "../../common/common.css";

export interface CardViewProps {

    name: string;
    costRations: number,
    costMaterial: number,
    costBlessing: number,
    image: string,
    text: string,
    lore: string,
    type: string,
    subType?: string,
    strength?: number,
    health?: number
}

export class CardView extends React.Component<CardViewProps, {}> {

    constructor(props: CardViewProps) {

        super(props);
    }

    render(): any {

        return (
            <div
                draggable={true}
                className="card-view border-large border-radius"
            >
                <div className="head-wrapper">
                    <div className="name">
                        <div className="font-size-large">
                            {this.props.name}
                        </div>
                    </div>
                    <div className="cost-wrapper">
                        <div className="cost cost-rations">
                            <div className="font-size-extra-large center-text font-border">
                                {this.props.costRations}
                            </div>
                        </div>
                        <div className="cost cost-material">
                            <div className="font-size-extra-large center-text font-border">
                                {this.props.costMaterial}
                            </div>
                        </div>
                        <div className="cost cost-blessing">
                            <div className="font-size-extra-large center-text font-border">
                                {this.props.costBlessing}
                            </div>
                        </div>
                    </div>
                </div>
                <div className="image-wrapper border-small">
                    <img
                        className="image"
                        src={this.props.image}
                        alt={this.props.name}
                    />
                </div>
                <div className="text-wrapper border-small">
                    <div className="text">
                        <div className="font-size-medium" dangerouslySetInnerHTML={{__html: this.props.text}} />
                    </div>
                    <div className="lore">
                        <div className="font-size-medium" dangerouslySetInnerHTML={{__html: this.props.text}} />
                    </div>
                </div>
                <div className="foot-wrapper">
                    <div className="type">
                        <div className="font-size-medium">
                            {this.props.type}
                            {this.props.subType && " - " + this.props.subType}
                        </div>
                    </div>
                    {
                        this.props.strength && this.props.health &&
                        <div className="stats-wrapper">
                            <div className="strength">
                                <div className="font-size-extra-large center-text font-border">
                                    {this.props.strength}
                                </div>
                            </div>
                            <div className="health border">
                                <div className="font-size-extra-large center-text font-border">
                                    {this.props.health}
                                </div>
                            </div>
                        </div>
                    }
                </div>
            </div>
        );
    }
}

export default CardView;
