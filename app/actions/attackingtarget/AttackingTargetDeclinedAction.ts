import {ATTACKING_TARGET_DECLINED} from "../ActionTypes";
import {Action} from "redux";

interface AttackingTargetDeclinedAction extends Action<ATTACKING_TARGET_DECLINED>{

    readonly type: ATTACKING_TARGET_DECLINED;
    readonly reason: string;
}

export class AttackingDeclinedActionFactory {

    public static getInstance(reason: string): AttackingTargetDeclinedAction {

        return {
            type: ATTACKING_TARGET_DECLINED,
            reason: reason
        }
    }
}

export default AttackingTargetDeclinedAction;
