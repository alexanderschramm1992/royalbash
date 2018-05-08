import {SUMMONING_ACCEPTED} from "./ActionTypes";
import {Action} from "redux";

interface SummoningAcceptedAction extends Action<SUMMONING_ACCEPTED>{

    readonly type: SUMMONING_ACCEPTED;
    readonly summoningId: string;
    readonly targetId: string,
}

export class SummoningAcceptedActionFactory {

    public static getInstance(summoningId: string, targetId: string): SummoningAcceptedAction {

        return {
            type: SUMMONING_ACCEPTED,
            summoningId: summoningId,
            targetId: targetId
        }
    }
}

export default SummoningAcceptedAction;
