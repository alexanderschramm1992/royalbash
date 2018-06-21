import {
    SUMMONING_ISSUED,
    SUMMONING_ACCEPTED,
    SUMMONING_DECLINED,
    SUMMONING_CARD_DRAGGED
} from "../actions/ActionTypes";
import {AnyAction, Reducer} from "redux";
import {StateModel} from "../Store";
import SummoningIssuedAction from "../actions/SummoningIssuedAction";
import SummoningDeclinedAction from "../actions/SummoningDeclinedAction";
import Game from "../model/Game";
import SummoningCardDraggedAction from "../actions/SummoningCardDraggedAction";

const handleSummoning: Reducer<StateModel, AnyAction> = (state, action): StateModel => {

    switch (action.type) {
        case SUMMONING_CARD_DRAGGED:

            let cardDraggedAction = action as SummoningCardDraggedAction;
            console.log("Summoning card " + cardDraggedAction.cardId + " is dragged");
            return {
                ...state,
                summoningCardDragged: cardDraggedAction.cardId
            };
        case SUMMONING_ISSUED:

            let summoningIssuedAction = action as SummoningIssuedAction;
            console.log("Summoning issued");
            return {
                ...state,
                summonCardIssued: true,
                summoningCardDragged: summoningIssuedAction.cardId,
                summoningTarget: summoningIssuedAction.targetId
            };
        case SUMMONING_ACCEPTED:

            let game = action.game.game as Game;
            console.log("Summoning accepted");
            return {
                ...state,
                game: game,
                summonCardIssued: false,
                summoningCardDragged: null,
                summoningTarget: null
            };
        case SUMMONING_DECLINED:

            let summoningDeclinedAction = action as SummoningDeclinedAction;
            console.log("Summoning declined because: " + summoningDeclinedAction.reason);
            return {
                ...state,
                summonCardIssued: false,
                summoningCardDragged: null,
                summoningTarget: null
            };
        default:
            return state;
    }
};

export default handleSummoning;
