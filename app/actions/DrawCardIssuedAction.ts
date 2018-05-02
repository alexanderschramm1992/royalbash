import { DRAW_CARD_ISSUED } from "./ActionTypes";
import { Action } from "redux";

class DrawCardIssuedAction implements Action<DRAW_CARD_ISSUED>{

    readonly type: DRAW_CARD_ISSUED;

    constructor(
        readonly playerId: string
    ) {}
}

export default DrawCardIssuedAction;
