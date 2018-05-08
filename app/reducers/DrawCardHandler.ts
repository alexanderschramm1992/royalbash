import { DRAW_CARD_ISSUED, DRAW_CARD_ACCEPTED, DRAW_CARD_DECLINED } from "../actions/ActionTypes";
import {AnyAction, Reducer} from "redux";
import { StateModel } from "../Store";
import DrawCardAcceptedAction from "../actions/DrawCardAcceptedAction";
import DrawCardDeclinedAction from "../actions/DrawCardDeclinedAction";

const handleDrawCard: Reducer<StateModel, AnyAction> = (state, action): StateModel => {

    switch (action.type) {
        case DRAW_CARD_ISSUED:

            console.log("Draw Card issued");
            return {
                ...state, 
                drawCardIssued: true
            };
        case DRAW_CARD_ACCEPTED:

            console.log("Draw Card accepted");
            let drawCardAcceptedAction = action as DrawCardAcceptedAction;
            return {
                ...state,
                drawCardIssued: false,
                game: drawCardAcceptedAction.game
            };
        case DRAW_CARD_DECLINED:

        let drawCardDeclinedAction = action as DrawCardDeclinedAction;
            console.log("Draw Card declined because: " + drawCardDeclinedAction.reason);
            return {
                ...state,
                drawCardIssued: false
            };
        default:
            return state;
    }
};

export default handleDrawCard;
