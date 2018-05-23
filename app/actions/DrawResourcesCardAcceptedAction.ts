import {DRAW_RESOURCES_CARD_ACCEPTED} from "./ActionTypes";
import { Action } from "redux";
import { Game } from "../model/Game";

interface DrawResourcesCardAcceptedAction extends Action<DRAW_RESOURCES_CARD_ACCEPTED> {

    readonly type: DRAW_RESOURCES_CARD_ACCEPTED;
    readonly game: Game;
}

export class DrawResourcesCardAcceptedActionFactory{

    public static getInstance(game: Game): DrawResourcesCardAcceptedAction {

        return {
            type: DRAW_RESOURCES_CARD_ACCEPTED,
            game: game
        }
    }
}

export default DrawResourcesCardAcceptedAction;
