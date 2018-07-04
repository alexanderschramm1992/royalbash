import {
    ENDING_TURN_ISSUED,
    ENDING_TURN_ACCEPTED,
    ENDING_TURN_DECLINED
} from "../actions/ActionTypes";
import {AnyAction, Reducer} from "redux";
import { StateModel } from "../Store";
import EndingTurnAcceptedAction from "../actions/endingturn/EndingTurnAcceptedAction";
import EndingTurnDeclinedAction from "../actions/endingturn/EndingTurnDeclinedAction";

const handleEndingTurn: Reducer<StateModel, AnyAction> = (state, action): StateModel => {

    switch (action.type) {
        case ENDING_TURN_ISSUED:

            console.log("Ending Turn issued");
            return {
                ...state,
                endingTurnIssued: true
            };
        case ENDING_TURN_ACCEPTED:

            console.log("Ending Turn accepted");
            let endingTurnAcceptedAction = action as EndingTurnAcceptedAction;
            return {
                ...state,
                endingTurnIssued: false,
                ownTurn: false,
                game: endingTurnAcceptedAction.game
            };
        case ENDING_TURN_DECLINED:

            let endingTurnDeclinedAction = action as EndingTurnDeclinedAction;
            console.log("Ending Turn declined because: " + endingTurnDeclinedAction.reason);
            return {
                ...state,
                endingTurnIssued: false
            };
        default:
            return state;
    }
};

export default handleEndingTurn;
