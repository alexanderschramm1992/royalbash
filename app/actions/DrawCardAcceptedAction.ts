import { DRAW_CARD_ACCEPTED } from "./ActionTypes";
import { Action } from "redux";

interface DrawCardAcceptedAction extends Action<DRAW_CARD_ACCEPTED> {

    readonly type: DRAW_CARD_ACCEPTED;
    readonly cardId: string;
}

export class DrawCardAcceptedActionFactory{

    public static getInstance(cardId: string): DrawCardAcceptedAction {

        return {
            type: DRAW_CARD_ACCEPTED,
            cardId: cardId
        }
    }
}

export default DrawCardAcceptedAction;
