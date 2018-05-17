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
import SummoningDeclinedAction from "../actions/SummoningDeclinedAction";
import Game from "../model/Game";

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
            console.log("Summoning issued");
            return {
                ...state,
                summonCardIssued: true,
                cardToBeSummoned: summoningIssuedAction.cardId,
                summoningTarget: summoningIssuedAction.targetId
            };
        case SUMMONING_ACCEPTED:

            let game = action.game.game as Game;
            console.log("Summoning accepted");
            return {
                ...state,
                game: game,
                summonCardIssued: false,
                cardToBeSummoned: null,
                summoningTarget: null
            };
        case SUMMONING_DECLINED:

            let summoningDeclinedAction = action as SummoningDeclinedAction;
            console.log("Summoning declined because: " + summoningDeclinedAction.reason);
            return {
                ...state,
                summonCardIssued: false,
                cardToBeSummoned: null,
                summoningTarget: null
            };
        default:
            return state;
    }
};

export default handleSummoning;
