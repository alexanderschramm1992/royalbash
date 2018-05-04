import {AnyAction, Reducer} from "redux";
import {StateModel} from "../Store";
import {MOUSE_OFF_CARD, MOUSE_ON_CARD} from "../actions/ActionTypes";
import MouseOnCardAction from "../actions/MouseOnCardAction";

const handleMouseOnCard: Reducer<StateModel, AnyAction> = (state, action): StateModel => {

    switch(action.type) {
        case MOUSE_ON_CARD:

            let mouseOnCardAction = action as MouseOnCardAction;
            console.log("Mouse on card " + mouseOnCardAction.cardId);
            return {
                ...state,
                cardOnPreview: mouseOnCardAction.cardId
            };
        case MOUSE_OFF_CARD:

            console.log("Mouse off card");
            return {
                ...state,
                cardOnPreview: null
            };
        default:
            return state;
    }
};

export default handleMouseOnCard;