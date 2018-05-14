import {
    LOAD_GAME_ISSUED,
    LOAD_GAME_ACCEPTED,
    LOAD_GAME_DECLINED
} from "../actions/ActionTypes";
import {AnyAction, Reducer} from "redux";
import { StateModel } from "../Store";
import LoadGameAcceptedAction from "../actions/LoadGameAcceptedAction";
import LoadGameDeclinedAction from "../actions/LoadGameDeclinedAction";
import LoadGameIssuedAction from "../actions/LoadGameIssuedAction";

const handleLoadGame: Reducer<StateModel, AnyAction> = (state, action): StateModel => {

    switch (action.type) {
        case LOAD_GAME_ISSUED:

            console.log("Load Game issued");
            let loadGameIssuedAction = action as LoadGameIssuedAction;
            return {
                ...state,
                loadGameIssued: true,
                gameId: loadGameIssuedAction.gameId
            };
        case LOAD_GAME_ACCEPTED:

            console.log("Load Game accepted");
            let loadGameAcceptedAction = action as LoadGameAcceptedAction;
            return {
                ...state,
                loadGameIssued: false,
                game: loadGameAcceptedAction.game
            };
        case LOAD_GAME_DECLINED:

            let drawCardDeclinedAction = action as LoadGameDeclinedAction;
            console.log("Load Game declined because: " + drawCardDeclinedAction.reason);
            return {
                ...state,
                loadGameIssued: false
            };
        default:
            return state;
    }
};

export default handleLoadGame;
