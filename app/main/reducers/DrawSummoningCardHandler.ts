import { DRAW_SUMMONING_CARD_ISSUED, DRAW_SUMMONING_CARD_ACCEPTED, DRAW_SUMMONING_CARD_DECLINED } from "../actions/ActionTypes";
import {AnyAction, Reducer} from "redux";
import { StateModel } from "../Store";
import DrawCardAcceptedAction from "../actions/drawingsummoningcard/DrawSummoningCardAcceptedAction";
import DrawCardDeclinedAction from "../actions/drawingsummoningcard/DrawSummoningCardDeclinedAction";

const handleDrawSummoningCard: Reducer<StateModel, AnyAction> = (state, action): StateModel => {

    switch (action.type) {
        case DRAW_SUMMONING_CARD_ISSUED:

            console.log("Draw Summoning Card issued");
            return {
                ...state, 
                drawSummoningCardIssued: true
            };
        case DRAW_SUMMONING_CARD_ACCEPTED:

            console.log("Draw Summoning Card accepted");
            let drawSummoningCardAcceptedAction = action as DrawCardAcceptedAction;
            return {
                ...state,
                drawSummoningCardIssued: false,
                game: drawSummoningCardAcceptedAction.game
            };
        case DRAW_SUMMONING_CARD_DECLINED:

        let drawSummoningCardDeclinedAction = action as DrawCardDeclinedAction;
            console.log("Draw Summoning Card declined because: " + drawSummoningCardDeclinedAction.reason);
            return {
                ...state,
                drawSummoningCardIssued: false
            };
        default:
            return state;
    }
};

export default handleDrawSummoningCard;
