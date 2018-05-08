import {CARD_DROP_ISSUED} from "./ActionTypes";
import {Action} from "redux";

interface CardDropIssuedAction extends Action<CARD_DROP_ISSUED>{

    readonly type: CARD_DROP_ISSUED;
    readonly cardId: string;
    readonly targetId: string,
}

export class CardDropIssuedActionFactory {

    public static getInstance(cardId: string, targetId: string): CardDropIssuedAction {

        return {
            type: CARD_DROP_ISSUED,
            cardId: cardId,
            targetId: targetId
        }
    }
}

export default CardDropIssuedAction;
