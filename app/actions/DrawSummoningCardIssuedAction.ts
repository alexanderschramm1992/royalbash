import { DRAW_SUMMONING_CARD_ISSUED } from "./ActionTypes";
import { Action } from "redux";

interface DrawSummoningCardIssuedAction extends Action<DRAW_SUMMONING_CARD_ISSUED>{

    readonly type: DRAW_SUMMONING_CARD_ISSUED;
    readonly playerId: string;
}

export class DrawSummoningCardIssuedActionFactory{

    public static getInstance(playerId: string): DrawSummoningCardIssuedAction {

        return {
            type: DRAW_SUMMONING_CARD_ISSUED,
            playerId: playerId
        }
    }
}

export default DrawSummoningCardIssuedAction;
