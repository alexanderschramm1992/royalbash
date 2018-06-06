import {ATTACKING_ISSUED} from "./ActionTypes";
import {Action} from "redux";

interface AttackingIssuedAction extends Action<ATTACKING_ISSUED>{

    readonly type: ATTACKING_ISSUED;
    readonly attackingSummoningId: string;
    readonly attackedSummoningId: string,
}

export class AttackingIssuedActionFactory {

    public static getInstance(attackingSummoningId: string, attackedSummoningId: string): AttackingIssuedAction {

        return {
            type: ATTACKING_ISSUED,
            attackingSummoningId: attackingSummoningId,
            attackedSummoningId: attackedSummoningId
        }
    }
}

export default AttackingIssuedAction;
