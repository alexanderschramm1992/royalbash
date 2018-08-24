import {SUMMONING_ISSUED} from "../ActionTypes";
import {Action} from "redux";

interface SummoningIssuedAction extends Action<SUMMONING_ISSUED>{

    readonly type: SUMMONING_ISSUED;
    readonly cardId: string;
    readonly targetId: string,
}

export class SummoningIssuedActionFactory {

    public static getInstance(cardId: string, targetId: string): SummoningIssuedAction {

        return {
            type: SUMMONING_ISSUED,
            cardId: cardId,
            targetId: targetId
        }
    }
}

export default SummoningIssuedAction;
