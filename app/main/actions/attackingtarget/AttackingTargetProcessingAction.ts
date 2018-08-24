import {ATTACKING_TARGET_PROCESSING} from "../ActionTypes";
import {Action} from "redux";

interface AttackingTargetProcessingAction extends Action<ATTACKING_TARGET_PROCESSING>{

    readonly type: ATTACKING_TARGET_PROCESSING;
}

export class AttackingTargetProcessingActionFactory {

    public static getInstance(): AttackingTargetProcessingAction {

        return {
            type: ATTACKING_TARGET_PROCESSING
        }
    }
}

export default AttackingTargetProcessingAction;
