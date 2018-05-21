import {AnyAction, Reducer} from "redux";
import {StateModel, store} from "../Store";
import {MOUSE_OFF_CARD, MOUSE_ON_CARD} from "../actions/ActionTypes";
import MouseOnCardAction from "../actions/MouseOnCardAction";

const handleMouseOnCard: Reducer<StateModel, AnyAction> = (state, action): StateModel => {

    switch(action.type) {
        case MOUSE_ON_CARD:

            let mouseOnCardAction = action as MouseOnCardAction;
            if (mouseOnCardAction.cardId && mouseOnCardAction.cardId != state.cardOnPreview) {
                console.log("Mouse on summoningCard " + mouseOnCardAction.cardId);
                return {
                    ...state,
                    cardOnPreview: mouseOnCardAction.cardId
                };
            }
        case MOUSE_OFF_CARD:

            return {
                ...state,
                cardOnPreview: null
            };
        default:
            return state;
    }
};

export default handleMouseOnCard;