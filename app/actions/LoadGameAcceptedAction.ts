import {LOAD_GAME_ACCEPTED} from "./ActionTypes";
import { Action } from "redux";
import Game from "../model/Game";

interface LoadGameAcceptedAction extends Action<LOAD_GAME_ACCEPTED>{

    readonly type: LOAD_GAME_ACCEPTED;
    readonly game: Game;
}

export class LoadGameAcceptedActionFactory{

    public static getInstance(game: Game): LoadGameAcceptedAction {

        return {
            type: LOAD_GAME_ACCEPTED,
            game: game
        }
    }
}

export default LoadGameAcceptedAction;
