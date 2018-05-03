import { DRAW_CARD_DECLINED } from "./ActionTypes";
import { Action } from "redux";

interface DrawCardDeclinedAction extends Action<DRAW_CARD_DECLINED>{

    readonly type: DRAW_CARD_DECLINED;
    readonly reason: string;
}

export class DrawCardDeclinedActionFactory{

    public static getInstance(reason: string): DrawCardDeclinedAction {

        return {
            type: DRAW_CARD_DECLINED,
            reason: reason
        }
    }
}

export default DrawCardDeclinedAction;
