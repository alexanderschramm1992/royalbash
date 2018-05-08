import { DRAW_CARD_ACCEPTED } from "./ActionTypes";
import { Action } from "redux";
import { Game } from "../model/Game";

interface DrawCardAcceptedAction extends Action<DRAW_CARD_ACCEPTED> {

    readonly type: DRAW_CARD_ACCEPTED;
    readonly game: Game;
}

export class DrawCardAcceptedActionFactory{

    public static getInstance(game: Game): DrawCardAcceptedAction {

        return {
            type: DRAW_CARD_ACCEPTED,
            game: game
        }
    }
}

export default DrawCardAcceptedAction;
