import {SUMMONING_CARD_DRAGGED} from "../ActionTypes";
import {Action} from "redux";

interface SummoningCardDraggedAction extends Action<SUMMONING_CARD_DRAGGED>{

    readonly type: SUMMONING_CARD_DRAGGED;
    readonly cardId: string;
}

export class SummoningCardDraggedActionFactory {

    public static getInstance(cardId: string): SummoningCardDraggedAction {

        return {
            type: SUMMONING_CARD_DRAGGED,
            cardId: cardId
        }
    }
}

export default SummoningCardDraggedAction;
