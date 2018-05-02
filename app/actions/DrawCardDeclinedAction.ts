import { DRAW_CARD_DECLINED } from "./ActionTypes";
import { Action } from "redux";

class DrawCardDeclinedAction implements Action<DRAW_CARD_DECLINED>{

    readonly type: DRAW_CARD_DECLINED;

    constructor(
        readonly reason: string
    ) {}
}

export default DrawCardDeclinedAction;
