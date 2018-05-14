import { DRAW_CARD_ISSUED, DRAW_CARD_ACCEPTED, DRAW_CARD_DECLINED } from "../actions/ActionTypes";
import {AnyAction, Reducer} from "redux";
import { StateModel } from "../Store";
import DrawCardAcceptedAction from "../actions/DrawSummoningCardAcceptedAction";
import DrawCardDeclinedAction from "../actions/DrawSummoningCardDeclinedAction";

const handleDrawSummoningCard: Reducer<StateModel, AnyAction> = (state, action): StateModel => {

    switch (action.type) {
        case DRAW_CARD_ISSUED:

            console.log("Draw Summoning Card issued");
            return {
                ...state, 
                drawSummoningCardIssued: true
            };
        case DRAW_CARD_ACCEPTED:

            console.log("Draw Summoning Card accepted");
            let drawCardAcceptedAction = action as DrawCardAcceptedAction;
            return {
                ...state,
                drawSummoningCardIssued: false,
                game: drawCardAcceptedAction.game
            };
        case DRAW_CARD_DECLINED:

        let drawCardDeclinedAction = action as DrawCardDeclinedAction;
            console.log("Draw Summoning Card declined because: " + drawCardDeclinedAction.reason);
            return {
                ...state,
                drawSummoningCardIssued: false
            };
        default:
            return state;
    }
};

export default handleDrawSummoningCard;
