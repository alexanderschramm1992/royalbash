import {ATTACKING_TARGET_ISSUED} from "../ActionTypes";
import {Action} from "redux";

interface AttackingTargetIssuedAction extends Action<ATTACKING_TARGET_ISSUED>{

    readonly type: ATTACKING_TARGET_ISSUED;
    readonly attackingSummoningId: string;
    readonly attackedTargetId: string,
}

export class AttackingIssuedActionFactory {

    public static getInstance(attackingSummoningId: string, attackedTargetId: string): AttackingTargetIssuedAction {

        return {
            type: ATTACKING_TARGET_ISSUED,
            attackingSummoningId: attackingSummoningId,
            attackedTargetId: attackedTargetId
        }
    }
}

export default AttackingTargetIssuedAction;
