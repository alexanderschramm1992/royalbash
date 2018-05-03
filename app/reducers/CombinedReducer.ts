import { AnyAction, Reducer } from "redux";
import { StateModel } from "../Store";
import handleDrawCard from "./DrawCardHandler";

const initialState: StateModel =  {
    playerId: "ada78200-db51-4c3f-b065-481d86299113",
    drawCardIssued: false,
    hand: []
};

export const combinedReducers: Reducer<StateModel, AnyAction> = (state = initialState, action): StateModel => {

    state = handleDrawCard(state, action);
    return state;
};

export default combinedReducers;
