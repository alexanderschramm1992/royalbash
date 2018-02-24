import * as React from "react";

import "./../../common.css";
import {default as Creature, CreatureModel} from "../card/Creature";

export interface CardPreviewProps {

    creatureModel?: CreatureModel;
    scale: number;
}

export class CardPreview extends React.Component<CardPreviewProps, {}> {

    render(): any {

        return (
            <div className="card-preview">
                {this.props.creatureModel &&
                    <Creature
                        creatureModel={this.props.creatureModel}
                        scale={this.props.scale * 1.5}
                    />
                }
            </div>
        );
    }
}

export default CardPreview;
