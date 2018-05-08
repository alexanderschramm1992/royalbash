import {
    CARD_DRAGGED,
    CARD_DROP_ISSUED,
    CARD_DROP_ACCEPTED,
    CARD_DROP_DECLINED
} from "../actions/ActionTypes";
import {AnyAction, Reducer} from "redux";
import { StateModel } from "../Store";
import CardDraggedAction from "../actions/CardDraggedAction";
import CardDropIssuedAction from "../actions/CardDropIssuedAction";

const handleDrawCard: Reducer<StateModel, AnyAction> = (state, action): StateModel => {

    switch (action.type) {
        case CARD_DRAGGED:

            let cardDraggedAction = action as CardDraggedAction;
            console.log("Card " + cardDraggedAction.cardId + " is dragged");
            return {
                ...state,
                cardDragged: cardDraggedAction.cardId
            };
        case CARD_DROP_ISSUED:

            let cardDropIssuedAction = action as CardDropIssuedAction;
            console.log("Drop card " + cardDropIssuedAction.cardId + " issued on target " + cardDropIssuedAction.targetId);
            return {
                ...state,
                dropCardIssued: true
            };
        default:
            return state;
    }
};

export default handleDrawCard;
