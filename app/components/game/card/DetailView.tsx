import * as React from "react";

import "./../../common/common.css";
import "./DetailView.css";
import Card, {CardModel} from "../card/Card";
import store, {findCardModelById} from "../../../Store";

export interface DetailViewProps {

    scale: number;
}

interface DetailViewState {

    cardOnPreview?: CardModel;
}

export class DetailView extends React.Component<DetailViewProps, DetailViewState>{

    constructor(props: DetailViewProps) {

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
            <div className="detail-view-wrapper">
                <div className="detail-view border-large border-radius" style={style}>
                    {this.state.cardOnPreview &&
                        <Card
                            cardModel={this.state.cardOnPreview}
                        />
                    }
                </div>
            </div>
        );
    }
}

export default DetailView;
