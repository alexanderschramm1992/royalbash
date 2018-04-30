import * as React from "react";

import "./../../common/common.css";
import {default as Creature, CardModel} from "../card/Card";
import MouseOnCardEvent from "../../../events/MouseOnCardEvent";
import EventBus from "../../../events/EventBus";
import Observer from "../../../events/Observer";

export interface CardPreviewProps {

    eventBus: EventBus<MouseOnCardEvent>
    scale: number;
}

interface CardPreviewState {

    creatureModel?: CardModel;
}

export class CardPreview
    extends React.Component<CardPreviewProps, CardPreviewState>
    implements Observer<MouseOnCardEvent>
{

    constructor(props: CardPreviewProps) {

        super(props);
        props.eventBus.subscribe(this);
    }

    notify(event: MouseOnCardEvent): void {

        this.setState({
            creatureModel: event.creatureModel
        })
    }

    render(): any {

        let style = {
            fontSize: this.props.scale + "px"
        };

        return (
            <div className="card-preview" style={style}>
                {this.state &&
                    <Creature
                        cardModel={this.state.creatureModel}
                        eventBus={this.props.eventBus}
                    />
                }
            </div>
        );
    }
}

export default CardPreview;
