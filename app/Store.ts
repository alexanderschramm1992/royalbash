import { combineReducers, createStore, Store } from "redux";
import handleDrawCard from "./reducers/DrawCardHandler";

export interface StateModel {

    playerId: string;
    drawCardIssued: boolean;
    hand: Array<string>;
}

export const store: Store<StateModel> = createStore(combineReducers({handleDrawCard}));

export const initialState: () => StateModel = (): StateModel => {

    return {
        playerId: "ada78200-db51-4c3f-b065-481d86299113",
        drawCardIssued: false,
        hand: []
    }
}

export default store;
