import {AnyAction, Reducer} from "redux";
import {StateModel} from "../Store";
import handleDrawSummoningCard from "./DrawSummoningCardHandler";
import handleMouseOnCard from "./MouseOnCardHandler";
import handleMouseOnSummoning from "./MouseOnSummoningHandler";
import handleSummoning from "./SummoningHandler";
import handleLoadGame from "./LoadGameHandler";

const initialState: StateModel =  {

    playerId: "8dbc6953-e25e-49f0-a298-7a0ea721de6c",

    loadGameIssued: false,

    drawSummoningCardIssued: false,

    summonCardIssued: false,
};

export const combinedReducers: Reducer<StateModel, AnyAction> =
    (state = initialState, action): StateModel => {

        state = handleDrawSummoningCard(state, action);
        state = handleMouseOnCard(state, action);
        state = handleMouseOnSummoning(state, action);
        state = handleSummoning(state, action);
        state = handleLoadGame(state, action);
        return state;
    };

export default combinedReducers;
