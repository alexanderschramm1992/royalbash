import {MOUSE_ON_CARD} from "./ActionTypes";
import {Action} from "redux";

interface MouseOnCardAction extends Action<MOUSE_ON_CARD>{

    readonly type: MOUSE_ON_CARD;
    readonly cardId: string;
}

export class MouseOnCardActionFactory{

    public static getInstance(cardId: string): MouseOnCardAction {

        return {
            type: MOUSE_ON_CARD,
            cardId: cardId
        }
    }
}

export default MouseOnCardAction;
