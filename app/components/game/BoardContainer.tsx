import * as React from "react";

import "../common/common.css";
import "./BoardContainer.css";

import BoardComponent from "./BoardComponent";
import LoadingConstantsCall from "../../rest/LoadingConstantsCall";
import LoadGameCall from "../../rest/LoadGameCall";
import AttackingTargetCall from "../../rest/AttackingTargetCall";
import SummoningCardCall from "../../rest/SummoningCardCall";
import GatheringResourcesCall from "../../rest/GatheringResourcesCall";
import DrawSummoningCardCall from "../../rest/DrawSummoningCardCall";
import DrawResourcesCardCall from "../../rest/DrawResourcesCardCall";
import store from "../../Store";
import {LoadGameIssuedActionFactory} from "../../actions/loadinggame/LoadGameIssuedAction";
import {LoadingConstantsIssuedActionFactory} from "../../actions/loadingconstants/LoadingConstantsIssuedAction";
import EndingTurnCall from "../../rest/EndingTurnCall";

interface BoardContainerState {
    readonly loadingConstantsCall: LoadingConstantsCall;
    readonly loadingGameCall: LoadGameCall;
    readonly endingTurnCall: EndingTurnCall;
    readonly drawingSummoningCardCall: DrawSummoningCardCall;
    readonly drawingResourcesCardCall: DrawResourcesCardCall;
    readonly summoningCardCall: SummoningCardCall;
    readonly attackingTargetCall: AttackingTargetCall;
    readonly gatheringResourcesCall: GatheringResourcesCall;

    ready: boolean;
}

export interface BoardContainerProps {

    readonly gameId: string;
    readonly playerId: string;
}

export class BoardContainer extends React.Component<BoardContainerProps, BoardContainerState> {

    constructor(props: BoardContainerProps) {
        super(props);

        this.state = {
            loadingConstantsCall: new LoadingConstantsCall(),
            loadingGameCall: new LoadGameCall(),
            endingTurnCall: new EndingTurnCall(),
            drawingSummoningCardCall: new DrawSummoningCardCall(),
            drawingResourcesCardCall: new DrawResourcesCardCall(),
            summoningCardCall: new SummoningCardCall(),
            attackingTargetCall: new AttackingTargetCall(),
            gatheringResourcesCall: new GatheringResourcesCall(),

            ready: false
        };

        store.subscribe(() => {
            if(store.getState().constants && store.getState().game) {
                this.setState({ready: true});
            }
        });

        console.log("Loading costants...");
        store.dispatch(LoadingConstantsIssuedActionFactory.getInstance());
        console.log("Loading game...");
        store.dispatch(LoadGameIssuedActionFactory.getInstance(
            props.gameId,
            props.playerId
        ));
    }

    render(): any {

        return (
            <div className="board-container">
                {store.getState().constants && store.getState().game &&
                    <BoardComponent/>
                }
                {(!store.getState().constants || !store.getState().game) &&
                <div className="loading-symbol font-size-extra-large">Loading...</div>
                }
                <div id="modal" title="Start Game"/>
            </div>
        );
    }
}

export default BoardContainer;