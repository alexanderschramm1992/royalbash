import {CARD_DRAGGED} from "./ActionTypes";
import {Action} from "redux";

interface CardDraggedAction extends Action<CARD_DRAGGED>{

    readonly type: CARD_DRAGGED;
    readonly cardId: string;
}

export class CardDraggedActionFactory {

    public static getInstance(cardId: string): CardDraggedAction {

        return {
            type: CARD_DRAGGED,
            cardId: cardId
        }
    }
}

export default CardDraggedAction;
