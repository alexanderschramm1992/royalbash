import {SUMMONING_DECLINED} from "../ActionTypes";
import {Action} from "redux";

interface SummoningDeclinedAction extends Action<SUMMONING_DECLINED>{

    readonly type: SUMMONING_DECLINED;
    readonly reason: string;
}

export class SummoningDeclinedActionFactory {

    public static getInstance(reason: string): SummoningDeclinedAction {

        return {
            type: SUMMONING_DECLINED,
            reason: reason
        }
    }
}

export default SummoningDeclinedAction;
