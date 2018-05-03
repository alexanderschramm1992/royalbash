import { createStore, Store } from "redux";
import combinedReducers from "./reducers/CombinedReducer";

export interface StateModel {

    playerId: string;
    drawCardIssued: boolean;
    hand: Array<string>;
}

export const initialState: StateModel =  {
    playerId: "ada78200-db51-4c3f-b065-481d86299113",
    drawCardIssued: false,
    hand: []
};

export const store: Store<StateModel> = createStore(
    combinedReducers
);

export default store;
