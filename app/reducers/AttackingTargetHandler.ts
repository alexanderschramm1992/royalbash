import {
    SUMMONING_DRAGGED,
    ATTACKING_TARGET_ISSUED,
    ATTACKING_TARGET_ACCEPTED,
    ATTACKING_TARGET_DECLINED, ATTACKING_TARGET_PROCESSING
} from "../actions/ActionTypes";
import {AnyAction, Reducer} from "redux";
import {StateModel} from "../Store";
import SummoningDraggedAction from "../actions/SummoningDraggedAction";
import AttackingTargetIssuedAction from "../actions/AttackingTargetIssuedAction";
import AttackingTargetDeclinedAction from "../actions/AttackingTargetDeclinedAction";
import Game from "../model/Game";

const handleAttackingTarget: Reducer<StateModel, AnyAction> = (state, action): StateModel => {

    switch (action.type) {
        case SUMMONING_DRAGGED:

            let summoningDraggedAction = action as SummoningDraggedAction;
            console.log("Summoning " + summoningDraggedAction.summoningId + " is dragged");
            return {
                ...state,
                attackingSummoning: summoningDraggedAction.summoningId
            };
        case ATTACKING_TARGET_ISSUED:

            let attackingIssuedAction = action as AttackingTargetIssuedAction;
            console.log("Attacking target issued");
            return {
                ...state,
                attackingTargetIssued: true,
                attackingSummoning: attackingIssuedAction.attackingSummoningId,
                attackedTarget: attackingIssuedAction.attackedTargetId
            };
        case ATTACKING_TARGET_PROCESSING:

            console.log("Attacking target processing");
            return {
                ...state,
                attackingTargetProcessing: true
            };
        case ATTACKING_TARGET_ACCEPTED:

            let game = action.game.game as Game;
            console.log("Attacking target accepted");
            return {
                ...state,
                game: game,
                attackingTargetProcessing: false,
                attackingTargetIssued: false,
                attackingSummoning: null,
                attackedTarget: null
            };
        case ATTACKING_TARGET_DECLINED:

            let attackingDeclinedAction = action as AttackingTargetDeclinedAction;
            console.log("Attacking target declined because: " + attackingDeclinedAction.reason);
            return {
                ...state,
                attackingTargetProcessing: false,
                attackingTargetIssued: false,
                attackingSummoning: null,
                attackedTarget: null
            };
        default:
            return state;
    }
};

export default handleAttackingTarget;
