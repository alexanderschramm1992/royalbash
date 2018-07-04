import {ENDING_TURN_DECLINED} from "../ActionTypes";
import { Action } from "redux";

interface EndingTurnDeclinedAction extends Action<ENDING_TURN_DECLINED>{

    readonly type: ENDING_TURN_DECLINED;
    readonly reason: string;
}

export class EndingTurnDeclinedActionFactory{

    public static getInstance(reason: string): EndingTurnDeclinedAction {

        return {
            type: ENDING_TURN_DECLINED,
            reason: reason
        }
    }
}

export default EndingTurnDeclinedAction;
