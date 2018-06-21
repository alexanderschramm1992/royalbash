import {
    DRAW_RESOURCES_CARD_ISSUED,
    DRAW_RESOURCES_CARD_ACCEPTED,
    DRAW_RESOURCES_CARD_DECLINED
} from "../actions/ActionTypes";
import {AnyAction, Reducer} from "redux";
import { StateModel } from "../Store";
import DrawCardAcceptedAction from "../actions/drawingsummoningcard/DrawSummoningCardAcceptedAction";
import DrawCardDeclinedAction from "../actions/drawingsummoningcard/DrawSummoningCardDeclinedAction";

const handleDrawResourcesCard: Reducer<StateModel, AnyAction> = (state, action): StateModel => {

    switch (action.type) {
        case DRAW_RESOURCES_CARD_ISSUED:

            console.log("Draw Resources Card issued");
            return {
                ...state,
                drawResourcesCardIssued: true
            };
        case DRAW_RESOURCES_CARD_ACCEPTED:

            console.log("Draw Resources Card accepted");
            let drawResourcesCardAcceptedAction = action as DrawCardAcceptedAction;
            return {
                ...state,
                drawResourcesCardIssued: false,
                game: drawResourcesCardAcceptedAction.game
            };
        case DRAW_RESOURCES_CARD_DECLINED:

            let drawResourcesCardDeclinedAction = action as DrawCardDeclinedAction;
            console.log("Draw Resources Card declined because: " + drawResourcesCardDeclinedAction.reason);
            return {
                ...state,
                drawResourcesCardIssued: false
            };
        default:
            return state;
    }
};

export default handleDrawResourcesCard;
