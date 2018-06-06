import {
    SUMMONING_DRAGGED,
    ATTACKING_ISSUED,
    ATTACKING_ACCEPTED,
    ATTACKING_DECLINED
} from "../actions/ActionTypes";
import {AnyAction, Reducer} from "redux";
import {StateModel} from "../Store";
import SummoningDraggedAction from "../actions/SummoningDraggedAction";
import AttackingIssuedAction from "../actions/AttackingIssuedAction";
import AttackingDeclinedAction from "../actions/AttackingDeclinedAction";
import Game from "../model/Game";

const handleAttacking: Reducer<StateModel, AnyAction> = (state, action): StateModel => {

    switch (action.type) {
        case SUMMONING_DRAGGED:

            let summoningDraggedAction = action as SummoningDraggedAction;
            console.log("Summoning " + summoningDraggedAction.summoningId + " is dragged");
            return {
                ...state,
                summoningToAttack: summoningDraggedAction.summoningId
            };
        case ATTACKING_ISSUED:

            let attackingIssuedAction = action as AttackingIssuedAction;
            console.log("Attacking issued");
            return {
                ...state,
                attackingIssued: true,
                attackingSummoning: attackingIssuedAction.attackingSummoningId,
                attackedSummoning: attackingIssuedAction.attackedSummoningId
            };
        case ATTACKING_ACCEPTED:

            let game = action.game.game as Game;
            console.log("Attacking accepted");
            return {
                ...state,
                game: game,
                attackingIssued: false,
                attackingSummoning: null,
                attackedSummoning: null
            };
        case ATTACKING_DECLINED:

            let attackingDeclinedAction = action as AttackingDeclinedAction;
            console.log("Attacking declined because: " + attackingDeclinedAction.reason);
            return {
                ...state,
                attackingIssued: false,
                attackingSummoning: null,
                attackedSummoning: null
            };
        default:
            return state;
    }
};

export default handleAttacking;
