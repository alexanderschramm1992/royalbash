import { DRAW_SUMMONING_CARD_ACCEPTED } from "./ActionTypes";
import { Action } from "redux";
import { Game } from "../model/Game";

interface DrawSummoningCardAcceptedAction extends Action<DRAW_SUMMONING_CARD_ACCEPTED> {

    readonly type: DRAW_SUMMONING_CARD_ACCEPTED;
    readonly game: Game;
}

export class DrawSummoningCardAcceptedActionFactory{

    public static getInstance(game: Game): DrawSummoningCardAcceptedAction {

        return {
            type: DRAW_SUMMONING_CARD_ACCEPTED,
            game: game
        }
    }
}

export default DrawSummoningCardAcceptedAction;
