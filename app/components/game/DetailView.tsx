import * as React from "react";

import "./../common/common.css";
import "./DetailView.css";
import CardComponent from "./card/CardComponent";
import SummoningComponent from "./card/SummoningComponent";
import {Summoning, Card} from "../../model/Game";
import store, {findCardById, findSummoningById} from "../../Store";

export interface DetailViewProps {

    scale: number;
}

interface DetailViewState {

    cardOnPreview?: Card;
    summoningOnPreview?: Summoning;
}

export class DetailView extends React.Component<DetailViewProps, DetailViewState>{

    constructor(props: DetailViewProps) {

        super(props);

        this.state = {
            cardOnPreview: null
        };

        store.subscribe((): void => {

            let cardOnPreview =
                store.getState().cardOnPreview ? findCardById(store.getState().cardOnPreview) : null;
            let summoningOnPreview = 
                store.getState().summoningOnPreview ? findSummoningById(store.getState().summoningOnPreview) : null;

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
                        <CardComponent
                            card = {this.state.cardOnPreview}
                        />
                    }
                    {this.state.summoningOnPreview &&
                        <SummoningComponent
                            summoning={this.state.summoningOnPreview}
                        />
                    }
                </div>
            </div>
        );
    }
}

export default DetailView;
