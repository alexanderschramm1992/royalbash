import * as React from "react";

import "./CardMiniView.css";
import "../../common/common.css";

export interface CardViewMiniProps {

    name: string;
    costRations: number,
    costMaterial: number,
    costBlessing: number,
    image: string,
    strength?: number,
    health?: number,
    handleMouseOver: () => void,
    handleMouseOut: () => void,
    handleDragStart: (event: any) => void
}

export class CardMini extends React.Component<CardViewMiniProps, {}> {

    render(): any {

        return (
            <div
                draggable={true}
                onDragStart={this.props.handleDragStart}
                className="card-mini-view border-large"
                onMouseOver={this.props.handleMouseOver}
                onMouseOut={this.props.handleMouseOut}
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
                        draggable={false}
                        src={this.props.image}
                        alt={this.props.name}
                    />
                </div>
                <div className="foot-wrapper">
                    {
                        this.props.strength && this.props.health &&
                        <div className="stats-wrapper">
                            <div className="strength">
                                <div className="font-size-large center-text font-border">
                                    {this.props.strength}
                                </div>
                            </div>
                            <div className="health">
                                <div className="font-size-large center-text font-border">
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

export default CardMini;
