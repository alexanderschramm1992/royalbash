import {LOAD_GAME_ISSUED} from "../ActionTypes";
import { Action } from "redux";

interface LoadGameIssuedAction extends Action<LOAD_GAME_ISSUED>{

    readonly type: LOAD_GAME_ISSUED;
    readonly gameId: string;
}

export class LoadGameIssuedActionFactory{

    public static getInstance(gameId: string): LoadGameIssuedAction {

        return {
            type: LOAD_GAME_ISSUED,
            gameId: gameId
        }
    }
}

export default LoadGameIssuedAction;
