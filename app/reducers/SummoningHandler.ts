import {
    CARD_DRAGGED,
    SUMMONING_ISSUED,
    SUMMONING_ACCEPTED,
    SUMMONING_DECLINED
} from "../actions/ActionTypes";
import {AnyAction, Reducer} from "redux";
import {StateModel} from "../Store";
import CardDraggedAction from "../actions/CardDraggedAction";
import SummoningIssuedAction from "../actions/SummoningIssuedAction";
import SummoningAcceptedAction from "../actions/SummoningAcceptedAction";
import SummoningDeclinedAction from "../actions/SummoningDeclinedAction";

const handleSummoning: Reducer<StateModel, AnyAction> = (state, action): StateModel => {

    switch (action.type) {
        case CARD_DRAGGED:

            let cardDraggedAction = action as CardDraggedAction;
            console.log("Card " + cardDraggedAction.cardId + " is dragged");
            return {
                ...state,
                cardToBeSummoned: cardDraggedAction.cardId
            };
        case SUMMONING_ISSUED:

            let summoningIssuedAction = action as SummoningIssuedAction;
            console.log("Summoning card " + summoningIssuedAction.cardId + " issued on target " + summoningIssuedAction.targetId);
            return {
                ...state,
                summonCardIssued: true
            };
        case SUMMONING_ACCEPTED:

            let summoningAcceptedAction = action as SummoningAcceptedAction;
            console.log("Summoning " + summoningAcceptedAction.summoningId + " accepted on target " + summoningAcceptedAction.targetId);
            return {
                ...state,
                // ToDo: Add state
            };
        case SUMMONING_DECLINED:

            let summoningDeclinedAction = action as SummoningDeclinedAction;
            console.log("Summoning declined because: " + summoningDeclinedAction.reason);
            return {
                ...state,
                // ToDo: Add state
            };
        default:
            return state;
    }
};

export default handleSummoning;
