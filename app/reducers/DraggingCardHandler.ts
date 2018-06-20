import {CARD_DRAGGED} from "../actions/ActionTypes";
import {AnyAction, Reducer} from "redux";
import {StateModel} from "../Store";
import CardDraggedAction from "../actions/CardDraggedAction";

const handleDraggingCard: Reducer<StateModel, AnyAction> = (state, action): StateModel => {

    switch (action.type) {
        case CARD_DRAGGED:

            let cardDraggedAction = action as CardDraggedAction;
            console.log("Card " + cardDraggedAction.cardId + " is dragged");
            return {
                ...state,
                cardDragged: cardDraggedAction.cardId
            };
    default:
        return state;
    }
};

export default handleDraggingCard;
