import {AnyAction, Reducer} from "redux";
import {StateModel} from "../Store";
import handleDrawSummoningCard from "./DrawSummoningCardHandler";
import handleMouseOnCard from "./MouseOnCardHandler";
import handleMouseOnSummoning from "./MouseOnSummoningHandler";
import handleSummoning from "./SummoningHandler";
import handleLoadingConstants from "./LoadingConstantsHandler";
import handleLoadGame from "./LoadGameHandler";
import handleDrawResourcesCard from "./DrawResourcesCardHandler";
import handleAttackingTarget from "./AttackingTargetHandler";
import handleGatheringResources from "./GatheringResourcesHandler";
import handleEndingTurn from "./EndingTurnHandler";

const initialState: StateModel =  {

    ownTurn: true,
    endingTurnIssued: false,
    pollingGame: false,

    playerId: "8dbc6953-e25e-49f0-a298-7a0ea721de6c",
    enemyId: "736cb270-c73c-4257-b49f-d71d9b4cb59b",

    loadGameIssued: false,
    loadingConstantsIssued: false,
    drawSummoningCardIssued: false,
    drawResourcesCardIssued: false,
    summonCardIssued: false,
    gatheringResourceIssued: false,
    attackingTargetIssued: false,
    attackingTargetProcessing: false,
};

export const combinedReducers: Reducer<StateModel, AnyAction> =
    (state = initialState, action): StateModel => {

        state = handleLoadingConstants(state, action);
        state = handleLoadGame(state, action);
        state = handleEndingTurn(state, action);
        state = handleDrawSummoningCard(state, action);
        state = handleDrawResourcesCard(state, action);
        state = handleMouseOnCard(state, action);
        state = handleMouseOnSummoning(state, action);
        state = handleSummoning(state, action);
        state = handleAttackingTarget(state, action);
        state = handleGatheringResources(state, action);
        return state;
    };

export default combinedReducers;
