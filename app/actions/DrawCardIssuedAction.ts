import { DRAW_CARD_ISSUED } from "./ActionTypes";
import { Action } from "redux";

interface DrawCardIssuedAction extends Action<DRAW_CARD_ISSUED>{

    readonly type: DRAW_CARD_ISSUED;
    readonly playerId: string;
}

export class DrawCardIssuedActionFactory{

    public static getInstance(playerId: string): DrawCardIssuedAction {

        return {
            type: DRAW_CARD_ISSUED,
            playerId: playerId
        }
    }
}

export default DrawCardIssuedAction;
