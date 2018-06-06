import {ATTACKING_DECLINED} from "./ActionTypes";
import {Action} from "redux";

interface AttackingDeclinedAction extends Action<ATTACKING_DECLINED>{

    readonly type: ATTACKING_DECLINED;
    readonly reason: string;
}

export class AttackingDeclinedActionFactory {

    public static getInstance(reason: string): AttackingDeclinedAction {

        return {
            type: ATTACKING_DECLINED,
            reason: reason
        }
    }
}

export default AttackingDeclinedAction;
