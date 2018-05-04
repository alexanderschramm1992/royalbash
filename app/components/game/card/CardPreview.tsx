import * as React from "react";

import "./../../common/common.css";
import {default as Creature, CardModel} from "../card/Card";
import store, {findCardModelById} from "../../../Store";

export interface CardPreviewProps {

    scale: number;
}

interface CardPreviewState {

    cardOnPreview?: CardModel;
}

export class CardPreview extends React.Component<CardPreviewProps, CardPreviewState>{

    constructor(props: CardPreviewProps) {

        super(props);

        this.state = {
            cardOnPreview: null
        };

        store.subscribe((): void => {

            let cardOnPreview =
                store.getState().cardOnPreview ? findCardModelById(store.getState().cardOnPreview) : null;

            console.log(cardOnPreview);

            this.setState({
                cardOnPreview: cardOnPreview
            });
        });
    }

    render(): any {

        let style = {
            fontSize: this.props.scale + "px"
        };

        return (
            <div className="card-preview" style={style}>
                {this.state.cardOnPreview &&
                    <Creature
                        cardModel={this.state.cardOnPreview}
                    />
                }
            </div>
        );
    }
}

export default CardPreview;
