import { DRAW_CARD_DECLINED } from "./ActionTypes";
import { Action } from "redux";

interface DrawSummoningCardDeclinedAction extends Action<DRAW_CARD_DECLINED>{

    readonly type: DRAW_CARD_DECLINED;
    readonly reason: string;
}

export class DrawSummoningCardDeclinedActionFactory{

    public static getInstance(reason: string): DrawSummoningCardDeclinedAction {

        return {
            type: DRAW_CARD_DECLINED,
            reason: reason
        }
    }
}

export default DrawSummoningCardDeclinedAction;
