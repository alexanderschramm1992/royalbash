import { DRAW_CARD_ACCEPTED } from "./ActionTypes";
import { Action } from "redux";

class DrawCardAcceptedAction implements Action<DRAW_CARD_ACCEPTED> {

    readonly type: DRAW_CARD_ACCEPTED;

    constructor(
        readonly cardId: string
    ) {}
}

export default DrawCardAcceptedAction;
