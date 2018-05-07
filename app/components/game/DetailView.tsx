import * as React from "react";

import "./../common/common.css";
import "./DetailView.css";
import Card, {CardModel} from "./card/Card";
import Summoning, {SummoningModel} from "./summoning/Summoning";
import store, {findCardModelById, findSummoningModelById} from "../../Store";

export interface DetailViewProps {

    scale: number;
}

interface DetailViewState {

    cardOnPreview?: CardModel;
    summoningOnPreview?: SummoningModel;
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
            let summoningOnPreview = 
                store.getState().summoningOnPreview ? findSummoningModelById(store.getState().summoningOnPreview) : null;

            console.dir("Card on Preview")
            console.dir(cardOnPreview);
            console.dir(store.getState().cardOnPreview);
            console.dir("Summoning on Preview");
            console.dir(summoningOnPreview);
            console.dir(store.getState().summoningOnPreview);

            this.setState({
                cardOnPreview: cardOnPreview,
                summoningOnPreview: summoningOnPreview
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
                    {this.state.summoningOnPreview &&
                        <Summoning
                            summoningModel={this.state.summoningOnPreview}
                        />
                    }
                </div>
            </div>
        );
    }
}

export default DetailView;
