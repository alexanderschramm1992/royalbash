import {
    POLLING_GAME_ACTIVATED,
    POLLING_GAME_DEACTIVATED
} from "../actions/ActionTypes";
import {AnyAction, Reducer} from "redux";
import { StateModel } from "../Store";

const handlePollingGame: Reducer<StateModel, AnyAction> = (state, action): StateModel => {

    switch (action.type) {
        case POLLING_GAME_ACTIVATED:

            console.log("Polling Game activated");
            return {
                ...state,
                pollingGame: true
            };
        case POLLING_GAME_DEACTIVATED:

            console.log("Polling Game deactivated");
            return {
                ...state,
                pollingGame: false
            };
        default:
            return state;
    }
};

export default handlePollingGame;
